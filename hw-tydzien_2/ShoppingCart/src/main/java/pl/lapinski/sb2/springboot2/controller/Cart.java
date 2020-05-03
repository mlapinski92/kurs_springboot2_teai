package pl.lapinski.sb2.springboot2.controller;
import pl.lapinski.sb2.springboot2.model.Product;
import pl.lapinski.sb2.springboot2.service.ShoppingCartService;
import java.math.BigDecimal;

public interface Cart {

    BigDecimal getSumPrice();

    void showShoppingCart();

    default BigDecimal getTotalPrice(ShoppingCartService shoppingCartService) {
        BigDecimal sumPrice = new BigDecimal(0);
        for (Product p : shoppingCartService.getShoppingCart()) {
            sumPrice = sumPrice.add(p.getPrice());
        }
        return sumPrice;
    }
}
