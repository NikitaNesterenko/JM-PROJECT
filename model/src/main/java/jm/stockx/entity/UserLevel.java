package jm.stockx.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "level")
public class UserLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level")
    private int level;

    @Column(name = "progress")
    private int levelProgress;

    public UserLevel(int level, int levelProgress) {
        this.level = level;
        this.levelProgress = levelProgress;
    }

    public UserLevel() {
    }
}
