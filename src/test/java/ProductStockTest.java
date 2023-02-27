import com.example.enums.Product;
import com.example.model.ProductStock;
import com.example.model.VendingMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductStockTest {

    static VendingMachine machine;
    static ProductStock productStock;

    @BeforeEach
    public void setUp() {


        Map<Product, Integer> products = new HashMap<>();
        products.put(Product.MIRENDINA, 10);
        products.put(Product.KITKAT, 10);
        products.put(Product.MILKA, 10);
        products.put(Product.SNICKERS, 10);
        products.put(Product.TANGO, 10);

        productStock = new ProductStock(products);


    }

    @Test
    public void testAddProducts() {
        // Create products map
        Map<Product, Integer> products = new HashMap<>();

        // Depositing products
        productStock.add(Product.MIRENDINA);
        productStock.add(Product.KITKAT);

        // asserting that the deposit was successful
        Assertions.assertEquals(11,
                productStock.getProducts().get(Product.MIRENDINA));
        Assertions.assertEquals(11,
                productStock.getProducts().get(Product.KITKAT));

    }
    @Test
    public void testDispenseProducts() {
        // Create products map
        Map<Product, Integer> products = new HashMap<>();

        // Depositing products
        productStock.dispense(Product.MIRENDINA);

        // asserting that the deposit was successful
        Assertions.assertEquals(9,
                productStock.getProducts().get(Product.MIRENDINA));

    }

}
