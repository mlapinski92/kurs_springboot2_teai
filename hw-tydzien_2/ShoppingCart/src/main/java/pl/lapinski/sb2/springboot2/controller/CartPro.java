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
import java.math.RoundingMode;

@Component
@Profile("PRO")
public class CartPro implements Cart{

    @Value("${value.membership}")
    private String membership;

    @Value("${value.VAT}")
    private BigDecimal vat;

    @Value("${value.discount}")
    private BigDecimal discount;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public CartPro(ShoppingCartService shoppingCartService) {
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
        System.out.println("PRO membership discout: " + discount + "%");
        System.out.println("Base price: " + getSumPrice() + " PLN");
        System.out.println("Your price after discount: " +  getSumPrice().subtract(getSumPrice().multiply(discount.divide(BigDecimal.valueOf(100))))
                .setScale(2,RoundingMode.CEILING) + " PLN");
        System.out.println("VAT: " + getSumPrice().multiply(vat).setScale(2, RoundingMode.CEILING) + " PLN");

    }
}
