import java.util.Scanner;

public class p04_sumUnlimitedCoins {

    static int[] combination;
    static int counter = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sum = 6;
        int[] coins = {1,2,3,4,6};

//        int sum = 5;
//        int[] coins = {1,2,5};

//        int sum = 13;
//        int[] coins = {1,2,5,10};

//        int sum = 100;
//        int[] coins = {1,2,5,10,20,50,100};

        int combinations = findAllCombinations(coins, sum);
        System.out.println(counter);
    }

    private static int findAllCombinations(int[] coins, int targetSum) {
        int count = 0;

        int[] coinsWithZero = new int[coins.length + 1];
        coinsWithZero[0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            coinsWithZero[i] = coins[i - 1];
        }

        combination = new int[coinsWithZero.length];
        createCombinations(coinsWithZero, 0, 0, targetSum, 0);

        return count;
    }

    private static void createCombinations(int[] coins, int index, int startNumber, int targetSum, int currentSum) {
        if (index == coins.length) {
            if (currentSum == targetSum)
                counter++;

            return;
        }

        for (int i = startNumber; i < coins.length; i++) {
            combination[index] = coins[i];
            currentSum += coins[i];

            if (currentSum == targetSum)
                counter++;

            if (currentSum > targetSum)
                return;

            createCombinations(coins, index + 1, startNumber, targetSum, currentSum);

            currentSum -= coins[i];
        }
    }


}
