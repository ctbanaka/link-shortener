package in.rushlnk.linkshortner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originalUrl;

    @Column(unique = true)
    private String shortUrlKey;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime expirationDate; // Optional for expiring URLs

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = true)
    private User user;
}
