import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class p05_sumLimitedCoinsTest {
    @Test
    public void zeroTest01() {
        int[] coins = new int[] {1,2,2,3,3,4,6};
        int targetSum = 6;

        int expectedResult = 4;
        int result = p05_sumLimitedCoins.numberOfWaysToGetSumWithCoins(targetSum, coins);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void zeroTest02() {
        int[] coins = new int[] {1,2,2,5};
        int targetSum = 5;

        int expectedResult = 2;
        int result = p05_sumLimitedCoins.numberOfWaysToGetSumWithCoins(targetSum, coins);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void zeroTest03() {
        int[] coins = new int[] {1,2,2,5,5,10};
        int targetSum = 13;

        int expectedResult = 2;
        int result = p05_sumLimitedCoins.numberOfWaysToGetSumWithCoins(targetSum, coins);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void zeroTest04() {
        int[] coins = new int[] {50,20,20,20,20,20,10};
        int targetSum = 100;

        int expectedResult = 2;
        int result = p05_sumLimitedCoins.numberOfWaysToGetSumWithCoins(targetSum, coins);

        Assertions.assertEquals(expectedResult, result);
    }
}