package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "recovery_tokens")
public class TokenRecovery {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "hash")
    private String hash;

    @Column(name = "hash_email")
    private String hashEmail;

    @Column(name = "start_time")
    private Date startTime;

}
