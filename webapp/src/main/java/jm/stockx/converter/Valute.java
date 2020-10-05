package jm.stockx.converter;

public class Valute {
    private final String ID;
    private final Integer NumCode;
    private final String CharCode;
    private final Integer Nominal;
    private final String Name;
    private final Double Value;
    private final Double Previous;


    public Valute(String id, Integer numCode, String charCode, Integer nominal, String name, Double value, Double previous) {
        ID = id;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }

    public String getID() {
        return ID;
    }

    public Integer getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public Integer getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public Double getValue() {
        return Value;
    }

    public Double getPrevious() {
        return Previous;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "ID='" + ID + '\'' +
                ", NumCode=" + NumCode +
                ", CharCode='" + CharCode + '\'' +
                ", Nominal=" + Nominal +
                ", Name='" + Name + '\'' +
                ", Value=" + Value +
                ", Previous=" + Previous +
                '}';
    }
}
