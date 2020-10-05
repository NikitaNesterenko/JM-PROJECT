package jm.stockx.converter;

import org.joda.money.Money;

public class Currency {
    private final String id;
    private final Integer numCode;
    private final String charCode;
    private final Integer nominal;
    private final String name;
    private final Money value;
    private final Money previous;

    public Currency(String id, Integer numCode, String charCode, Integer nominal, String name, Money value, Money previous) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.previous = previous;
    }

    public String getId() {
        return id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public Money getValue() {
        return value;
    }

    public Money getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "ID='" + id + '\'' +
                ", NumCode=" + numCode +
                ", CharCode='" + charCode + '\'' +
                ", Nominal=" + nominal +
                ", Name='" + name + '\'' +
                ", Value=" + value +
                ", Previous=" + previous +
                '}';
    }
}
