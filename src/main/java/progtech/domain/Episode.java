package progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Stores the episode information of the Episode.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Series series;
    private String name;
    private int episodeCount;
    private int season;
    private String description;

    /**
     * Constructor used to create the domain object.
     * @param series the Series object
     * @param name the name of the episode
     * @param episodeCount the episode count in the season of the episode
     * @param season season number of the episode
     * @param description the short description of the episode
     */
    public Episode(Series series, String name, int episodeCount, int season, String description) {
        this.series = series;
        this.name = name;
        this.episodeCount = episodeCount;
        this.season = season;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Episode episode = (Episode) o;
        return episodeCount == episode.episodeCount &&
                season == episode.season &&
                Objects.equals(name, episode.name) &&
                Objects.equals(description, episode.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, episodeCount, season, description);
    }
}
