package progtech.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import progtech.model.Series;

import javax.persistence.*;
import java.util.List;

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
    private List<Series> series;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
