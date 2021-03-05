package jm.stockx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Admin extends User {

    public Admin(String firstName,
                 String lastName,
                 String email,
                 String username,
                 String password,
                 Byte sellerLevel,
                 Boolean vacationMode,
                 String appleUserId,
                 String localeTag) {
        super(firstName, lastName, email, username, password, sellerLevel, vacationMode, appleUserId, localeTag);
    }
}
