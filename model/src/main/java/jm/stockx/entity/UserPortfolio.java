package jm.stockx.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="portfolio")
public class UserPortfolio {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Item> userItems;

    public UserPortfolio(User user) {
        this.user = user;
    }

}
