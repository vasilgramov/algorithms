import java.util.Arrays;
import java.util.Scanner;

public class p02_nestedLoopsToRecursion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        int[] array = new int[n];

        createCombinations(array, 0);
    }

    private static void createCombinations(int[] array, int index) {
        if (index == array.length) {
            System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = 1; i <= array.length; i++) {
            array[index] = i;
            createCombinations(array, index + 1);
        }
    }
}
