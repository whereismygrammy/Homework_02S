package pl.swiatek.homework_2.zad5;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addToCart(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void delByProdId(int id) {

        for (CartItem ci : cartItems) {
            if (ci.getProduct().getId() == id) {
                this.cartItems.remove(ci);
                break;
            }
        }


    }
}
