import java.util.Arrays;

public class p02_mergeSortImplementation {
    public static void main(String[] args) {
        int[] unsortedElements = { 11,16, 2, 8, 1, 9, 4, 7 };

        mergeSort(unsortedElements, 0, unsortedElements.length - 1);

        System.out.println(Arrays.toString(unsortedElements));
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
