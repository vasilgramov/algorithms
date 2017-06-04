import java.util.Scanner;

public class p01_quickSortImplementation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }

        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int first, int last) {
        if (first >= last)
            return;

        int pivot = array[last];
        int i = first;
        for (int j = first; j < last; j++) {
            if (array[j] <= pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        array[last] = array[i];
        array[i] = pivot;
        printArray(array);
        quickSort(array, first, i - 1);
        quickSort(array, i + 1, last);
    }

    private static void printArray(int[] array) {
        for (int number : array) {
            System.out.print(number + " ");
        }

        System.out.println();
    }
}

