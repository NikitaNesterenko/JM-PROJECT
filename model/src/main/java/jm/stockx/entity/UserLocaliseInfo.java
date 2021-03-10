package jm.stockx.entity;

import jm.stockx.enums.UserCountry;
import jm.stockx.enums.UserCurrency;
import jm.stockx.enums.UserLanguage;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "localise_info")
public class UserLocaliseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_country")
    @Enumerated(EnumType.STRING)
    private UserCountry userCountry;

    @Column(name = "user_currency")
    @Enumerated(EnumType.STRING)
    private UserCurrency userCurrency;

    @Column(name = "user_language")
    @Enumerated(EnumType.STRING)
    private UserLanguage userLanguage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserLocaliseInfo(UserCountry userCountry, UserCurrency userCurrency, UserLanguage userLanguage, User user) {
        this.userCountry = userCountry;
        this.userCurrency = userCurrency;
        this.userLanguage = userLanguage;
        this.user = user;
    }
}
