package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {

    List<Product> productList;

    public Cart(){
        productList = new ArrayList<>();
    }

}
