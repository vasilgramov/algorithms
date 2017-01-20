import java.util.Arrays;
import java.util.Scanner;

public class p03_variationsIterative {
    static int[] arr;
    static int n;
    static int k;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = 5/*Integer.parseInt(in.nextLine())*/;
        k = 3/*Integer.parseInt(in.nextLine())*/;

        arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = 1;
        }

        while (1 != 0) {
            System.out.println(Arrays.toString(arr));

            int index = k - 1;
            while (index >= 0 && arr[index] >= n) {
                index--;
            }

            if (index < 0)
                break;

            arr[index]++;
            for (int i = index + 1; i < k; i++) {
                arr[i] = 1;
            }
        }
    }
}
