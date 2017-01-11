import java.util.Scanner;

public class p02_printAllEightBitNumbers {
    private static int counter = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = 8;
        int[] vector = new int[n];
        gen01(n, vector);
        System.out.println(counter);
    }

    private static void gen01(int n, int[] vector) {
        if (n == 0) {
            print(vector);
            return;
        }

        counter++;

        for (int i = 0; i <= 1; i++) {
            vector[n - 1] = i;
            gen01(n - 1, vector);
        }
    }

    private static void print(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }

        System.out.println();
    }
}
