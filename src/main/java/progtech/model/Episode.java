package progtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Episode {
    @Id
    private long id;

    //private Show show;
    private String name;
    private int episodeCount;
    private int season;
    private String description;
}
