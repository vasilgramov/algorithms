import java.util.Scanner;

public class p02_permutationsIteratively {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = 3/*Integer.parseInt(in.nextLine())*/;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        int index = n - 1;
        while (true) {
            for (int i = index + 1; i < n; i++) {
                int current = arr[index];
                arr[index] = arr[i];

            }

            index--;
        }
    }
}
