package pl.swiatek.homework_2.zad5;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProduktDao {


    public List<Product> getList(){
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Prod1", 2, 20));
        productList.add(new Product("Prod2", 1, 21));
        productList.add(new Product("Prod3", 2, 22));
        productList.add(new Product("Prod4", 2, 23));
        productList.add(new Product("Prod5", 2, 24));

        return productList;
    }

}
