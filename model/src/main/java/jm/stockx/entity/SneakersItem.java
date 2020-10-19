package jm.stockx.entity;

import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class SneakersItem extends Item {

    public SneakersItem(String name) {
        super(name);
    }
}
