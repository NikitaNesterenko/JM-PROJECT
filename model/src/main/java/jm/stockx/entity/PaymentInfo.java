package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "payment_info")

/* Таблица хранит данные для покупки: карту и адрес доставки */
public class PaymentInfo {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expires_date")
    private String cardExpiresDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    //лучше создать сущность Country, и хранить в БД список стран
    //а тут String сменить на Country и сделать связь.
    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    //String, т.к. в некоторых странах в индексе бывают дефисы и буквы
    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "paymentsInfo")
    private Set<PurchaseInfo> purchasesInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
