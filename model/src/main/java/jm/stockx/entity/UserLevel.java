package jm.stockx.entity;

import jm.stockx.enums.LevelName;
import lombok.*;

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

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private LevelName levelName;

    @Column(name = "progress")
    private Long levelProgress;

    public UserLevel(LevelName levelName, Long levelProgress) {
        this.levelName = levelName;
        this.levelProgress = levelProgress;
    }

    public UserLevel() {
    }
}
