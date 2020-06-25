package model.api;

import lombok.Getter;
import java.util.Arrays;

@Getter
public class Item {

    private String id;
    private String type;
    private String[] images;
    private String displayName;
    private String applicableDiscount;
    private Category category;
    private Brand brand;
    private Price price;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", images=" + Arrays.toString(images) +
                ", displayName='" + displayName + '\'' +
                ", applicableDiscount='" + applicableDiscount + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                ", price=" + price +
                '}';
    }
}
