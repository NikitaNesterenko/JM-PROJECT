package jm.stockx.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "items")
@TypeDef(name = "joda_MoneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Item(String name) {
        this.name = name;
    }
}
