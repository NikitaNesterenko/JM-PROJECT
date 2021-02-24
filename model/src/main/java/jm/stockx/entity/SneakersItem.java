package jm.stockx.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class SneakersItem extends Item {

    public SneakersItem(String name) {
        super(name);
    }
}
