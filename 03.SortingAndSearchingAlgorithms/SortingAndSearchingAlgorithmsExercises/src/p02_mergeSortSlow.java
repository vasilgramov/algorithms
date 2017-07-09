import java.util.Arrays;
import java.util.Scanner;

public class p02_mergeSortSlow {
    public static void main(String[] args) {
        /*
        sample inputs
        5 4 3 2 1
        3 3 8 4 4 3 2
        8 1 6 2 0 7 2
        11 16 2 8 1 9 4 7
        4 0 3 9 9 3 9
        5 1 1 0 9 8 0
        6 9 3 7 7 6 5
         */

        Scanner in = new Scanner(System.in);

        String[] elementsAsString = in.nextLine().split("\\s+");
        int[] unsortedElements = new int[elementsAsString.length];
        for (int i = 0; i < elementsAsString.length; i++) {
            unsortedElements[i] = Integer.parseInt(elementsAsString[i]);
        }

        mergeSort(unsortedElements, 0, unsortedElements.length - 1);

        System.out.println(Arrays.toString(unsortedElements).replace("[", "").replace("]", "").replace(",", ""));
    }

    private static void mergeSort(int[] unsortedElements, int lowIndex, int highIndex) {
        if (lowIndex == highIndex) {
            return;
        }

        int midIndex = lowIndex + (highIndex - lowIndex) / 2;
        mergeSort(unsortedElements, 0, midIndex);
        mergeSort(unsortedElements, midIndex + 1, highIndex);

        int[] sortedElementsToHighIndex = merge(unsortedElements, lowIndex, midIndex, highIndex);
        int indexCounter = 0;
        for (int i = lowIndex; i <= highIndex; i++) {
            unsortedElements[i] = sortedElementsToHighIndex[indexCounter++];
        }
    }

    private static int[] merge(int[] unsortedElements, int lowIndex, int midIndex, int highIndex) {
        int[] elements = new int[highIndex - lowIndex + 1];
        int indexCounter = 0;

        int leftPointer = lowIndex;
        int rightPointer = midIndex + 1;

        while (leftPointer <= midIndex && rightPointer <= highIndex) {
            int leftNumber = unsortedElements[leftPointer];
            int rightNumber = unsortedElements[rightPointer];

            if (leftNumber < rightNumber) {
                elements[indexCounter++] = unsortedElements[leftPointer++];
            } else {
                elements[indexCounter++] = unsortedElements[rightPointer++];
            }
        }

        while (leftPointer <= midIndex) {
            elements[indexCounter++] = unsortedElements[leftPointer++];
        }

        while (rightPointer <= highIndex) {
            elements[indexCounter++] = unsortedElements[rightPointer++];
        }

        return elements;
    }
}
