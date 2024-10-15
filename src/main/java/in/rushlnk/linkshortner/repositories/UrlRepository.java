package in.rushlnk.linkshortner.repositories;

import in.rushlnk.linkshortner.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Optional<Url> findByShortUrlKey(String shortKey);
}
