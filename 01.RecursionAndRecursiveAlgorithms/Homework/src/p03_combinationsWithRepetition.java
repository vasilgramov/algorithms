import java.util.Arrays;
import java.util.Scanner;

public class p03_combinationsWithRepetition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int k = Integer.parseInt(in.nextLine());

        int[] array = new int[k];
        createCombinations(array, 0, 1, n);
    }

    private static void createCombinations(int[] array, int index, int startNum, int n) {
        if (index == array.length) {
            System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = startNum; i <= n; i++) {
            array[index] = i;
            createCombinations(array, index + 1, i, n);
        }
    }
}
