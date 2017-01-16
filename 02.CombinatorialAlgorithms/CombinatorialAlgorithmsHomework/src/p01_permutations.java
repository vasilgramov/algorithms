import java.util.Arrays;
import java.util.Scanner;

public class p01_permutations {
    static int[] arr;
    static int permutationsCounter = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        arr = new int[n];
        fillArray(n);

        createAllPermutations(0);
        System.out.println("Total permutations: " + permutationsCounter);
    }

    private static void fillArray(int n) {
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
    }

    private static void createAllPermutations(int index) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            permutationsCounter++;
            return;
        }

        createAllPermutations(index + 1);
        for (int i = index + 1; i < arr.length ; i++) {
            int current = arr[index];
            arr[index] = arr[i];
            arr[i] = current;

            createAllPermutations(index + 1);

            arr[i] = arr[index];
            arr[index] = current;
        }
    }
}
