package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "news")
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Lob
    private String text;

    @Column(name = "imageUrl")
    private String imageUrl;

    public News(
            String name,
            LocalDateTime time,
            String title,
            String description,
            String text) {
        this.name = name;
        this.time = time;
        this.title = title;
        this.description = description;
        this.text = text;
    }

    public News(Long id, String name, LocalDateTime time, String title, String description, String text) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.title = title;
        this.description = description;
        this.text = text;
    }
}
