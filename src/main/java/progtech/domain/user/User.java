package progtech.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import progtech.domain.Episode;
import progtech.domain.Series;

import javax.persistence.*;
import java.util.Set;

/**
 * Stores the data of the user of the application.
 */
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Series> series;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Episode> watchedEpisodes;
    private boolean isAdmin;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
