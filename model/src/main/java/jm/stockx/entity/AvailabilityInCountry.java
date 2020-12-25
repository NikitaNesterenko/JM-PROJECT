package jm.stockx.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "availability_in_country")
public class AvailabilityInCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long   id;

    @Column(name = "locale_tag")
    private String localeTag;

    @Column(name = "delivery_from")
    private Boolean deliveryFrom;

    @Column(name = "delivery_to")
    private Boolean deliveryTo;

    public AvailabilityInCountry(User user, boolean deliveryFrom, boolean deliveryTo) {
        this.localeTag = user.getLocaleTag();
        this.deliveryFrom = deliveryFrom;
        this.deliveryTo = deliveryTo;
    }

    public AvailabilityInCountry(String localeTag, boolean deliveryFrom, boolean deliveryTo) {
        this.localeTag = localeTag;
        this.deliveryFrom = deliveryFrom;
        this.deliveryTo = deliveryTo;
    }
}
