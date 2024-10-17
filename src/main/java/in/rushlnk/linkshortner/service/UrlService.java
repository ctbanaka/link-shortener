package in.rushlnk.linkshortner.service;

import in.rushlnk.linkshortner.models.Url;

import java.time.LocalDateTime;

public interface UrlService {

    Url createShortUrl(String originalUrl, LocalDateTime expireAt);
    String getLongUrl(String shortCode);
}
