package pl.lapinski.sb2.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.lapinski.sb2.springboot2.model.Product;
import pl.lapinski.sb2.springboot2.service.ShoppingCartService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("PLUS")
public class CartPlus implements Cart {

    @Value("${value.membership}")
    private String membership;

    @Value("${value.VAT}")
    private BigDecimal vat;


    private ShoppingCartService shoppingCartService;

    @Autowired
    public CartPlus(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public BigDecimal getSumPrice() {
        return getTotalPrice(shoppingCartService);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void showShoppingCart() {
        System.out.println("Membership: " + membership);
        System.out.println("Your shopping cart contain: ");
        for ( Product product :shoppingCartService.getShoppingCart()) {
            System.out.println(product.getName() + " - " + product.getPrice() + " PLN");
        }
        System.out.println("Your summary price is: " + getSumPrice() + " PLN");
        System.out.println("VAT: " + getSumPrice().multiply(vat).setScale(2, RoundingMode.CEILING) + " PLN");
    }
}


