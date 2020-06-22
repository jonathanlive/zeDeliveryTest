package model;

import com.github.javafaker.Faker;
import lombok.Getter;
import java.util.Locale;

@Getter
public class Customer {

    private Cart cart;
    private Address address;

    public Customer(){
        setAddress();
        cart = new Cart();
    }

    public void setAddress() {
        Faker faker = new Faker(new Locale("pt-BR"));
        String street = faker.address().streetName();
        String streetNumber = faker.address().streetAddressNumber();
        String secondaryAddress = faker.address().secondaryAddress();
        String neighborhood = faker.address().city();
        Address newAddress = new Address(street,streetNumber,secondaryAddress,neighborhood,false,false);
        this.address = newAddress;
    }

    public void addCartItem(String name, String price, String amount){
        getCart().getProductList().add(new Product(name,price,amount));
    }

    public Product getCartItem(int index){
        return getCart().getProductList().get(index);
    }
}
