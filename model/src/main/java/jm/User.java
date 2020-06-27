package jm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "sellerLevel")
    private Integer sellerLevel;
    @Column(name = "vacationMode")
    private boolean vacationMode;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_shoeSizes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shoeSize_id"))
    private Set<ShoeSize> shoeSizes;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_currencies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private Set<Currency> currencies;

    public User() {
    }

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Integer sellerLevel,
                boolean vacationMode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
    }

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Integer sellerLevel,
                boolean vacationMode,
                Set<ShoeSize> shoeSizes,
                Set<Currency> currencies) {
        this(firstName, lastName, email, username, password, sellerLevel, vacationMode);
        this.shoeSizes = shoeSizes;
        this.currencies = currencies;
    }
}
