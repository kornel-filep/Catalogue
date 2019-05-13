package progtech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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
        return id == episode.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
