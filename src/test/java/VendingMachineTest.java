
import com.example.enums.Coin;
import com.example.enums.Product;
import com.example.exception.InsufficientFundsException;
import com.example.exception.ProductNotFoundException;
import com.example.model.CoinInventory;
import com.example.model.ProductStock;
import com.example.model.VendingMachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineTest {

    static VendingMachine machine;
    static CoinInventory coinStock;
    static ProductStock productStock;

    @BeforeEach
    public void setUp() {

        Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_0_POINT_5, 10);
        coins.put(Coin.COIN_1, 10);
        coins.put(Coin.COIN_2, 10);
        coins.put(Coin.COIN_5, 0);
        coins.put(Coin.COIN_10, 10);

        coinStock = new CoinInventory(coins);

        Map<Product, Integer> products = new HashMap<>();
        products.put(Product.MIRENDINA, 10);
        products.put(Product.KITKAT, 10);
        products.put(Product.MILKA, 10);
        products.put(Product.SNICKERS, 10);
        products.put(Product.TANGO, 10);

        productStock = new ProductStock(products);

        machine = new VendingMachine(coinStock, productStock);

    }

    @Test
    public void testDepositCoins() {
        // Create coins map
        Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_0_POINT_5, 1);
        coins.put(Coin.COIN_1, 2);

        // Depositing coins
        machine.depositCoins(coins);

        // asserting that the deposit was successful
        Assertions.assertEquals(11,
                machine.getCoinInventory().getCoins().get(Coin.COIN_0_POINT_5));
        Assertions.assertEquals(
                12, machine.getCoinInventory().getCoins().get(Coin.COIN_1));

    }

    @Test
    public void testWithdrawCoins() {

        // Create coins map
        Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_0_POINT_5, 1);
        coins.put(Coin.COIN_1, 2);

        // Depositing coins
        machine.withdrawCoins(coins);

        // asserting that the deposit was successful
        Assertions.assertEquals(9,
                machine.getCoinInventory().getCoins().get(Coin.COIN_0_POINT_5));
        Assertions.assertEquals(
                8, machine.getCoinInventory().getCoins().get(Coin.COIN_1));
    }

    @Test
    public void testRefundCoins() {

    }

    @Test
    public void giveCorrectParams_whenPurchase_thenSucceed()
            throws ProductNotFoundException, InsufficientFundsException {

        int productId = 5;

        // Create coins map
        Map<Coin, Integer> given_coins = new HashMap<>();
        given_coins.put(Coin.COIN_10, 1);

        Map<Coin, Integer> ExpectedChange = new HashMap<>();

        ExpectedChange.put(Coin.COIN_2, 4);

        Map<Coin, Integer> change = machine.purchase(given_coins, productId);

        Assertions.assertEquals(ExpectedChange, change);
        Assertions.assertEquals(11,
                machine.getCoinInventory().getCoins().get(Coin.COIN_10));
        Assertions.assertEquals(0,
                machine.getCoinInventory().getCoins().get(Coin.COIN_5));
        Assertions.assertEquals(6,
                machine.getCoinInventory().getCoins().get(Coin.COIN_2));
        Assertions.assertEquals(10,
                machine.getCoinInventory().getCoins().get(Coin.COIN_1));

    }

    @Test
    public void giveWrongProduct_whenPurchase_thenThrowsProductNotFoundException()
            throws ProductNotFoundException, InsufficientFundsException {

        int productId = 34;

        // Create coins map
        Map<Coin, Integer> given_coins = new HashMap<>();
        given_coins.put(Coin.COIN_10, 1);

        Assertions.assertThrows(ProductNotFoundException.class, () -> machine.purchase(given_coins, productId));

    }

    @Test
    public void giveInsufficientFunds_whenPurchase_thenThrowsInsufficientFundsException()
            throws ProductNotFoundException, InsufficientFundsException {

        int productId = 5;

        // Create coins map
        Map<Coin, Integer> given_coins = new HashMap<>();
        given_coins.put(Coin.COIN_0_POINT_5, 1);

        Assertions.assertThrows(InsufficientFundsException.class, () -> machine.purchase(given_coins, productId));

    }
}
