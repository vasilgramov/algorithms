import java.util.Arrays;
import java.util.Scanner;

public class binarySearch {
    public static void main(String[] args) {
        int array[] = {3, 5, 11, 0, 0, 0, 12, 12, 0, 0, 0, 12, 12, 70, 71, 0, 90, 123, 140, 150, 166, 190, 0 };

        System.out.println(Arrays.binarySearch(array, 11));
        System.out.println(Arrays.binarySearch(array, 0));
    }
}