import java.util.Arrays;
import java.util.Scanner;

public class p02_variationsWithoutRepetition {
    static int n;
    static int k;

    static int[] used;

    static int[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = Integer.parseInt(in.nextLine());
        k = Integer.parseInt(in.nextLine());

        fillUsedArray();

        genVariationsWithoutRepetition(0);
    }

    private static void fillUsedArray() {
        used = new int[n];
        arr = new int[k];

        for (int i = 1; i <= n; i++) {
            used[i - 1] = i;
        }
    }

    private static void genVariationsWithoutRepetition(int index) {
        if (index == k) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = index; i < n; i++) {
            arr[index] = used[i];

            int current = used[i];
            used[i] = used[index];
            used[index] = current;

            genVariationsWithoutRepetition(index + 1);

            used[index] = used[i];
            used[i] = current;
        }
    }
}
