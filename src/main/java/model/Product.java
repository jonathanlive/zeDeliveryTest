package model;

import lombok.Getter;

@Getter
public class Product {

    private String name;
    private String price;
    private String amount;

    public Product(String name, String price, String amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + amount + '\'' +
                '}';
    }
}
