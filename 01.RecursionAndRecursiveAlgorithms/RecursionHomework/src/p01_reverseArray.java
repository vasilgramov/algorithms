import java.util.Arrays;
import java.util.Scanner;

public class p01_reverseArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int arrayLength = Integer.parseInt(in.nextLine());
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = in.nextInt();
        }

        reverseArr(array, 0);
        System.out.println(Arrays.toString(array));
    }

    private static void reverseArr(int[] array, int index) {
        if (index == array.length / 2)
            return;

        int temp = array[index];
        array[index] = array[array.length - 1 - index];
        array[array.length - 1 - index] = temp;
        reverseArr(array, index + 1);
    }
}