import java.util.Arrays;
import java.util.Scanner;

public class p01_binomialCoefficients {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = 2;
        int k = 1;

        if (n == 0 || n == 1 || k == 0 || n == k) {
            System.out.println(1);
            return;
        }

        int[] firstRow = new int[n + 1];
        firstRow[0] = 1;

        int[] secondRow = new int[n + 1];
        secondRow[0] = 1;
        secondRow[1] = 1;

        for (int i = 2; i <= n; i++) {
            int currenSize = i + 1;

            if (i % 2 == 0) { // first row
                firstRow[0] = 1;
                firstRow[currenSize - 1] = 1;

                for (int j = 1; j < currenSize - 1; j++) {
                    firstRow[j] = secondRow[j] + secondRow[j - 1];
                }
            } else {          // second row
                secondRow[0] = 1;
                secondRow[currenSize - 1] = 1;

                for (int j = 1; j < currenSize - 1; j++) {
                    secondRow[j] = firstRow[j] + firstRow[j - 1];
                }
            }
        }

        if (n % 2 == 0) {
            System.out.println(firstRow[k]);
        } else {
            System.out.println(secondRow[k]);
        }
    }
}
