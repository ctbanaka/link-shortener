package in.rushlnk.linkshortner.controller;

import org.springframework.web.bind.annotation.RestController;
import in.rushlnk.linkshortner.models.Url;
import in.rushlnk.linkshortner.service.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlServiceImpl urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortUrl(@RequestBody String originalUrl) {
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);


//        if (user != null) {
//            // Authenticated user
//            shortenedUrl = urlService.createShortUrl(originalUrl, user, expirationDate);
//        } else {
//            // Unauthenticated user
           Url url = urlService.createShortUrl(originalUrl, expirationDate);
//        }

        return new ResponseEntity<>("https://rushlnk.in/"+url.getShortUrlKey(), HttpStatus.CREATED);
    }

    // ... other methods
}
