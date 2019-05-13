package progtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int episodes;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Episode> episodeList;

    public Series(String name, int episodes, String description, Set<Episode> episodeList) {
        this.name = name;
        this.episodes = episodes;
        this.description = description;
        this.episodeList = episodeList;
    }
}
