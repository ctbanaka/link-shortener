package in.rushlnk.linkshortner.controller;

import in.rushlnk.linkshortner.dto.UrlDto;
import in.rushlnk.linkshortner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import in.rushlnk.linkshortner.models.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Value("${linkshortner.custom.domain}")
    private String customProperty;

    @PostMapping("/api/url/shorten")
    public ResponseEntity<String> createShortUrl(@RequestBody UrlDto urlDto) {
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);


//        if (user != null) {
//            // Authenticated user
//            shortenedUrl = urlService.createShortUrl(originalUrl, user, expirationDate);
//        } else {
//            // Unauthenticated user
        String sanitizedUrl = urlDto.getOriginalUrl().trim().replaceAll("^\"|\"$", "");
        Url url = urlService.createShortUrl(sanitizedUrl, expirationDate);
//        }

        return new ResponseEntity<>(customProperty+"/"+url.getShortUrlKey(), HttpStatus.CREATED);
    }

    @GetMapping("/{shortCode}")
    public void redirectToLongUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        try {
            String longUrl = urlService.getLongUrl(shortCode);
            response.sendRedirect(longUrl);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short URL not found");
        }
    }
}
