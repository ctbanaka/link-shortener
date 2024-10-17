package in.rushlnk.linkshortner.service;

import in.rushlnk.linkshortner.exceptions.ResourceNotFoundException;
import in.rushlnk.linkshortner.models.Url;
import in.rushlnk.linkshortner.models.User;
import in.rushlnk.linkshortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = BASE62.length();

    @Autowired
    private UrlRepository urlRepository;

    public Url createShortUrl(String originalUrl, User user, LocalDateTime expirationDate) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setCreatedAt(LocalDateTime.now());
        url.setExpirationDate(expirationDate);
        url.setUser(user);

        Url savedUrl = urlRepository.save(url);

        String shortUrlKey = encodeBase62(savedUrl.getId());

        savedUrl.setShortUrlKey(shortUrlKey);

        return urlRepository.save(savedUrl);
    }




    public Url createShortUrl(String originalUrl, LocalDateTime expirationDate) {
        return createShortUrl(originalUrl, null, expirationDate);
    }

    @Override
    public String getLongUrl(String shortCode) {
     return urlRepository.findByShortUrlKey(shortCode)
             .map(Url::getOriginalUrl)
             .orElseThrow(()->new ResourceNotFoundException("Url not found"));
    }

    private static String encodeBase62(Long id) {
        if (id == 0) {
            return String.valueOf(BASE62.charAt(0));
        }

        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString(); // Removed print statement
    }

    public static Long decodeBase62(String shortUrlKey) {
        long id = 0;
        for (int i = 0; i < shortUrlKey.length(); i++) {
            id = id * BASE + BASE62.indexOf(shortUrlKey.charAt(i));
        }
        return id;
    }
}
