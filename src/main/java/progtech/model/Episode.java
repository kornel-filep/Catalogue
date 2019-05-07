package progtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
}
