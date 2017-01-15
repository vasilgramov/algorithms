import java.util.Arrays;
import java.util.Scanner;

public class p05_combinationsWithoutRepetitions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        int k = Integer.parseInt(in.nextLine());

        int[] array = new int[k];
        printCombinations(array, n, 1, 0);
    }

    private static void printCombinations(int[] array, int n, int startNum, int index) {
        if (index == array.length) {
            System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = startNum; i <= n; i++) {
            array[index] = i;
            printCombinations(array, n, array[index] + 1, index + 1);
        }
    }
}
