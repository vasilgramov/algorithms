import java.util.Arrays;
import java.util.Scanner;

public class p04_combinationsWithoutRepetition {
    static int n;
    static int k;

    static int[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = Integer.parseInt(in.nextLine());
        k = Integer.parseInt(in.nextLine());

        arr = new int[k];

        createCombinationsWithoutRepetition(0, 1);
    }

    private static void createCombinationsWithoutRepetition(int index, int startNum) {
        if (index == k) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = startNum; i <= n; i++) {
            arr[index] = i;
            createCombinationsWithoutRepetition(index + 1, i + 1);
        }
    }
}
