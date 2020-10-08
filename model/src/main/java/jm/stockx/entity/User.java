package jm.stockx.entity;

import jm.stockx.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "seller_level")
    private Byte sellerLevel;

    @Type(type = "true_false")
    @Column(name = "vacation_mode")
    private Boolean vacationMode;

    @Column(name = "apple_user_id")
    private String appleUserId;

    @Column(name = "locale_tag")
    private String localeTag;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private Role role;

    private Boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_buying",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "buying_id"))
    private Set<BuyingInfo> buyingInfo;

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Byte sellerLevel,
                Boolean vacationMode,
                String localeTag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
        this.localeTag = localeTag;
    }

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Byte sellerLevel,
                Boolean vacationMode,
                String appleUserId,
                Set<BuyingInfo> buyingInfo) {
        this(firstName, lastName, email, username, password, sellerLevel, vacationMode, appleUserId);
        this.buyingInfo = buyingInfo;
    }

    public User(String firstName,
                String lastName,
                String email,
                String username,
                String password,
                Byte sellerLevel,
                Boolean vacationMode,
                String appleUserId,
                String localeTag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
        this.vacationMode = vacationMode;
        this.appleUserId = appleUserId;
        this.localeTag = localeTag;
    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(Long id,
                String firstName,
                String lastName,
                String email,
                String username,
                String password,
                byte sellerLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sellerLevel = sellerLevel;
    }

    public User() {
        super();
        this.active = false;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}