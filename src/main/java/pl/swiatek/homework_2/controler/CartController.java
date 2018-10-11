package pl.swiatek.homework_2.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.swiatek.homework_2.zad5.Cart;
import pl.swiatek.homework_2.zad5.CartItem;
import pl.swiatek.homework_2.zad5.Product;
import pl.swiatek.homework_2.zad5.ProduktDao;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private Cart cart;

    @Autowired
    private ProduktDao produktDao;

    @RequestMapping("/addtocart")
    public String addtocart() {
        Random rand = new Random();
        cart.addToCart(new CartItem(1, new Product("prod" + rand.nextInt(10), rand.nextDouble(), rand.nextLong())));
        return "/zad5/addtocart";
    }


    @RequestMapping(path = "/addtocartdao", produces = "text/html; charset=UTF-8")
    public String addtocartDAO(@RequestParam int id, @RequestParam int quantity, Model model) {
        List<Product> products = produktDao.getList();

        String message = "Nie ma produktu o id" + id;

        for (CartItem ci : cart.getCartItems()) {
            if (ci.getProduct().getId() == id) {
                ci.setQuantity(ci.getQuantity() + quantity);
                message = "Zwiększono ilość produktu " + id + " do ilości " + ci.getQuantity();
                model.addAttribute("message", message);
                return "/zad5/addtocartdao";
            }
        }

        for (Product p : products) {
            if (p.getId() == id) {
                cart.addToCart(new CartItem(quantity, p));
                message = "Dodano produkt " + id + " w ilości " + quantity;
                model.addAttribute("message", message);
                return "/zad5/addtocartdao";

            }
        }
        model.addAttribute("message", message);
        return "/zad5/addtocartdao";
    }

    @GetMapping("/show")
    @ResponseBody
    public String show() {
        return this.cart.getCartItems().toString();
    }

    @GetMapping("/showcart")
    public String showAll(Model model) {

        model.addAttribute("cartsize", cart.getCartItems().size());
        int sum = 0;
        double price = 0;
        for (CartItem ci : cart.getCartItems()) {
            sum = sum + ci.getQuantity();
            price = price + (ci.getQuantity() * ci.getProduct().getPrice());
        }
        model.addAttribute("cartquant", sum);
        model.addAttribute("cartprice", price);
        model.addAttribute("items", cart.getCartItems());

        return "/zad5/showCart";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        ProduktDao produktDao = new ProduktDao();
        List<Product> list = produktDao.getList();
        model.addAttribute("list", list);
        return "/zad5/addProduct";
    }

    @GetMapping("/delProduct")
    public String delProduct(@RequestParam int id) {
        cart.delByProdId(id);
        return "redirect:/cart/showcart";

    }

    @GetMapping("/moreProduct")
    public String moreProduct(@RequestParam int id) {
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem ci : cartItems) {
            if (ci.getProduct().getId() == id) {
                ci.setQuantity(ci.getQuantity() + 1);
            }
        }
        return "redirect:/cart/showcart";
    }

    @GetMapping("/lessProduct")
    public String lessProduct(@RequestParam int id) {
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem ci : cartItems) {
            if (ci.getProduct().getId() == id) {
                if (ci.getQuantity() == 1){
                    return "redirect:/cart/delProduct?id=" + id;
                }
                ci.setQuantity(ci.getQuantity() - 1);
            }
        }
        return "redirect:/cart/showcart";
    }


}

