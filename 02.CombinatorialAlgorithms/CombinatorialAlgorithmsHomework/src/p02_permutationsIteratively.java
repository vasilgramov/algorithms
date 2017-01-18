import java.util.Arrays;
import java.util.Scanner;

public class p02_permutationsIteratively {
    static int[] arr;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        
        combWithoutRepetitionsIterative();
    }

    public static void combWithoutRepetitionsIterative() {
        System.out.println(Arrays.toString(arr));
        int n = arr.length;
        int[] p = new int[n];
        int i = 1;
        while (i < n) {
            if (p[i] < i) {
                int j = ((i % 2) == 0) ? 0 : p[i];
                swap(arr, i, j);

                System.out.println(Arrays.toString(arr));
                p[i]++;
                i = 1;
            }
            else {
                p[i] = 0;
                i++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
