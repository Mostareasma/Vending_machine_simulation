
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

public class CoinInventoryTest {

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


    }

    @Test
    public void testDepositCoins() {
        // Create coins map
        Map<Coin, Integer> coins = new HashMap<>();
        coins.put(Coin.COIN_0_POINT_5, 1);
        coins.put(Coin.COIN_1, 2);

        // Depositing coins
        coinStock.deposit(coins);

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
}
