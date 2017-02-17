import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p05_sumLimitedCoins {

    /*
    Find the number of ways we can combine the coins to obtain a certain sum.
     */
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sum = Integer.parseInt(in.nextLine());
        String[] coinsAsString = in.nextLine().split("\\s+");
        int[] coins = new int[coinsAsString.length];
        for (int i = 0; i < coinsAsString.length; i++) {
            coins[i] = Integer.parseInt(coinsAsString[i]);
        }

        int count = numberOfWaysToGetSumWithCoins(sum, coins);
        System.out.println(count);
    }

    public static int numberOfWaysToGetSumWithCoins(int sum, int[] coins) {
        int count = 0;

        Set<Integer> possibleSums = new HashSet<>();
        possibleSums.add(0);

        for (int i = 0; i < coins.length; i++) {
            int currentCoin = coins[i];
            Set<Integer> newSums = new HashSet<>();

            for (Integer possibleSum : possibleSums) {
                int newSum = currentCoin + possibleSum;
                if (newSum == sum)
                    count++;

                newSums.add(newSum);
            }

            possibleSums.addAll(newSums);
        }

        return count;
    }
}
