import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by vladix on 7/9/17.
 */
public class p02_mergeSortImplementationFast {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static int[] arr;
    private static int[] aux;

    public static void main(String[] args) throws IOException {
        arr = readArray();
        aux = new int[arr.length];

        mergeSort(0, arr.length - 1);

//        System.out.println(Arrays.toString(arr).replace("[", "").replace("]", "").replace(",", ""));
        StringBuilder result = new StringBuilder();
        for (int i : arr) {
            result.append(i).append(" ");
        }

        System.out.println(result);
    }

    private static void mergeSort(int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);

        mergeSubArrays(start, mid, end);
    }

    private static void mergeSubArrays(int start, int mid, int end) {
        if (arr[mid] < arr[mid + 1]) {
            return;
        }

        for (int i = start; i <= end; i++) {
            aux[i] = arr[i];
        }

        int i = start;
        int j = mid + 1;
        for (int index = start; index <= end; index++) {
            if (i > mid) {
                arr[index] = aux[j++];
            } else if (j > end) {
                arr[index] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[index] = aux[i++];
            } else {
                arr[index] = aux[j++];
            }
        }
    }

    private static int[] readArray() throws IOException {
        String[] elements = in.readLine().split("\\s+");
        int[] arr = new int[elements.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        return arr;
    }
}
