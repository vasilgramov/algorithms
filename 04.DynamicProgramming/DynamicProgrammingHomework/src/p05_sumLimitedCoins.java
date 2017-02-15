import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p05_sumLimitedCoins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sum = 6;
        int[] coins = new int[] { 1,2,2,3,3,4,6 };

//        int sum = 5;
//        int[] coins = new int[] { 1,2,2,5 };

//        int sum = 13;
//        int[] coins = new int[] { 1,2,2,5,5,10 };

//        int sum = 100;
//        int[] coins = new int[] { 50,20,20,20,20,20,10 };

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

        System.out.println(count);
    }
}
