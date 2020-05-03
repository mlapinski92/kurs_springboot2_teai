package pl.lapinski.sb2.springboot2.service;

import org.springframework.stereotype.Service;
import pl.lapinski.sb2.springboot2.model.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ShoppingCartService {
    private final static int LOWER_BOUND = 50;
    private final static int UPPER_BOUND = 300;
    private final static int SCALE = 2;

    private List<Product> shoppingCart;

    public ShoppingCartService() {
        Product product1 = new Product("Keyboard", generatePrice());
        Product product2 = new Product("Book", generatePrice());
        Product product3 = new Product("Screen", generatePrice());
        Product product4 = new Product("Mouse", generatePrice());
        Product product5 = new Product("Chair", generatePrice());

        shoppingCart = new ArrayList<>();
        shoppingCart.add(product1);
        shoppingCart.add(product2);
        shoppingCart.add(product3);
        shoppingCart.add(product4);
        shoppingCart.add(product5);
    }

    public BigDecimal generatePrice(){
        Random r = new Random();
        int randomInt = r.nextInt((UPPER_BOUND - LOWER_BOUND)*100) + LOWER_BOUND;
        return new BigDecimal(BigInteger.valueOf(randomInt), SCALE);
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
