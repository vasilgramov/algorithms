import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class p01_sumOfCoinsTest {
    @Test
    public void zeroTest01() {
        int[] coins = { 1, 2, 5, 10, 20, 50 };
        int targetSum = 923;

        int[] expectedCoins = { 1, 1, 0, 0, 1, 18 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void zeroTest02() {
        int[] coins = { 1 };
        int targetSum = 42;

        int[] expectedCoins = { 42 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void zeroTest03() {
        int[] coins = { 1, 2, 5 };
        int targetSum = 78;

        int[] expectedCoins = { 1, 1, 15 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void largeTargetSum() {
        int[] coins = { 1, 2, 5 };
        int targetSum = 2031154123;

        int[] expectedCoins = { 1, 1, 406230824 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void nonOptimalParameters() {
        int[] coins = { 1, 9, 10 };
        int targetSum = 27;

        int[] expectedCoins = { 7, 0, 2 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void smallCoins() {
        int[] coins = { 1, 2, 3, 4 };
        int targetSum = 1234;

        int[] expectedCoins = { 0, 1, 0, 308 };
        int[] usedCons = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCons);
    }

    @Test
    public void oneCoin() {
        int[] coins = { 1, 3, 214, 5 };
        int target = 214;

        int[] expectedCoins = { 0, 0, 0, 1 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, target);
        System.out.println(Arrays.toString(usedCoins));

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }

    @Test
    public void oneCoinOfEach() {
        int[] coins = { 1000, 200, 30, 4 };
        int targetSum = 1234;

        int[] expectedCoins = { 1, 1, 1, 1 };
        int[] usedCoins = p01_sumOfCoins.getUsedCoins(coins, targetSum);

        Assertions.assertArrayEquals(expectedCoins, usedCoins);
    }
}