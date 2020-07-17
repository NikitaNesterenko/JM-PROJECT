package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

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

    @Column(name = "image")
    @Lob
    private Blob image;

}
