import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class p01_sumOfCoins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] coinsAsStrings = in.nextLine().split(", ");
        int[] coins =  new int[coinsAsStrings.length];

        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(coinsAsStrings[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().split("\\s+")[1]);

        int[] usedCoins = getUsedCoins(coins, targetSum);
        for (int i = 0; i < usedCoins.length; i++) {
            System.out.println(String.format("%d coin(s) with value %d", usedCoins[i], coins[i]));
        }
    }

    public static int[] getUsedCoins(int[] coins, int targetSum) {
        Arrays.sort(coins);

        int[] usedCoins = new int[coins.length];

        int temp = 1;
        int coin = coins[coins.length - temp];

        while (targetSum > 0) {
            while (targetSum - coin < 0) {
                temp++;
                if (coins.length - temp < 0) {
                    return null;
                }

                coin = coins[coins.length - temp];
            }

            targetSum -= coin;
            usedCoins[coins.length - temp]++;
        }

        return usedCoins;
    }
}
