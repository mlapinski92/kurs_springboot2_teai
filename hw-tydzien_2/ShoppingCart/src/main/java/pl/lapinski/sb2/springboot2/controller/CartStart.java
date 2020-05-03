package pl.lapinski.sb2.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.lapinski.sb2.springboot2.model.Product;
import pl.lapinski.sb2.springboot2.service.ShoppingCartService;

import java.math.BigDecimal;

@Component
@Profile("START")
public class CartStart implements Cart{

    @Value("${value.membership}")
    private String membership;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public CartStart(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public BigDecimal getSumPrice() {
        return getTotalPrice(shoppingCartService);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void showShoppingCart() {
        System.out.println("Membership:" + membership);
        System.out.println("Your shopping cart contain: ");
        for ( Product product :shoppingCartService.getShoppingCart()) {
            System.out.println(product.getName() + " - " + product.getPrice() + " PLN");
        }
        System.out.println("Your summary price is: " + getSumPrice());
        }
    }


