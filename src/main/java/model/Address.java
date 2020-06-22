package model;

import lombok.Getter;

@Getter
public class Address {

    private String street;
    private String number;
    private String compl;
    private String neighborhood;
    private Boolean withoutNumber;
    private Boolean withoutCompl;

    public Address(String street, String number, String compl, String neighborhood, Boolean withoutNumber, Boolean withoutCompl) {
        this.street = street;
        this.number = number;
        this.compl = compl;
        this.neighborhood = neighborhood;
        this.withoutNumber = withoutNumber;
        this.withoutCompl = withoutCompl;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", compl='" + compl + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", withoutNumber=" + withoutNumber +
                ", withoutCompl=" + withoutCompl +
                '}';
    }
}
