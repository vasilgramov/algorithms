import java.util.Arrays;
import java.util.Scanner;

public class p01_variationsWithRepetition {
    static int n;
    static int k;

    static int[] comb = new int[k];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = Integer.parseInt(in.nextLine());
        k = Integer.parseInt(in.nextLine());

        genVariationsWithRepetition(0);
    }

    private static void genVariationsWithRepetition(int index) {
        if (index == k) {
            System.out.println(Arrays.toString(comb));
            return;
        }

        for (int i = 1; i <= n; i++) {
            comb[index] = i;
            genVariationsWithRepetition(index + 1);
        }
    }
}
