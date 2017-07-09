import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by vladix on 7/9/17.
 */
public class p03_binarySearch {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] arr = readArray();
        int searchedElement = Integer.parseInt(in.readLine());

        int index = binarySearchRecursive(arr, searchedElement, 0, arr.length - 1);
        System.out.println(index);
    }

    private static int binarySearchIterative(int[] arr, int searchedElement, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == searchedElement) {
                return mid;
            }

            if (arr[mid] > searchedElement) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int binarySearchRecursive(int[] arr, int searchedElement, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == searchedElement) {
            return mid;
        }

        if (arr[mid] > searchedElement) {
            return binarySearchRecursive(arr, searchedElement, start, mid - 1);
        } else {
            return binarySearchRecursive(arr, searchedElement, mid + 1, end);
        }
    }

    private static int[] readArray() throws IOException {
        String[] elements = in.readLine().split("\\s+");
        int[] arr = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        return arr;
    }
}
