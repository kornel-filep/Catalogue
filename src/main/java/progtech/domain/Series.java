package progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Stores the Series information of the Series.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Series series = (Series) o;
        return id == series.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
