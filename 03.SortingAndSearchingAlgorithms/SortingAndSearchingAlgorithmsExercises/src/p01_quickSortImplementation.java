import java.util.Arrays;
import java.util.Scanner;

public class p01_quickSortImplementation {
    static boolean hasWallIndex = false;
    static int wallIndexForRightPart = 0;
    static int counter = 0;

    static boolean hasPivot = false;
    static int realPivotIndex = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] numbersAsString = in.nextLine().split(" ");
        int[] unsortedNumbers = new int[numbersAsString.length];
        for (int i = 0; i < numbersAsString.length; i++) {
            unsortedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }

        applyQuickLeftPart(unsortedNumbers, unsortedNumbers.length - 1, 0);

        applyQuickRightPart(unsortedNumbers, unsortedNumbers.length - 1, wallIndexForRightPart);
        System.out.println(Arrays.toString(unsortedNumbers));
    }

    private static boolean applyQuickRightPart(int[] numbers, int pivotIndex, int wallIndexForRightPart) {
        if (wallIndexForRightPart >= numbers.length - 1 || pivotIndex == wallIndexForRightPart)
            return true;

        int copyWallIndex = wallIndexForRightPart;

        boolean isPivotIndexTheLargest = true;

        for (int i = wallIndexForRightPart; i < pivotIndex; i++) {
            if (numbers[i] <= numbers[pivotIndex]) {
                int temp = numbers[wallIndexForRightPart];
                numbers[wallIndexForRightPart] = numbers[i];
                numbers[i] = temp;

                wallIndexForRightPart++;
            } else {
                isPivotIndexTheLargest = false;
            }
        }

        int temp = numbers[wallIndexForRightPart];
        numbers[wallIndexForRightPart] = numbers[pivotIndex];
        numbers[pivotIndex] = temp;

        if (isPivotIndexTheLargest) {
            if (applyQuickRightPart(numbers, pivotIndex - 1, copyWallIndex))
                return true;
        }

        if (applyQuickRightPart(numbers, pivotIndex, copyWallIndex + 1))
            return true;

        return true;
    }

    private static boolean applyQuickLeftPart(int[] numbers, int pivotIndex, int wallIndex) {
        if (pivotIndex < 0 || pivotIndex >= numbers.length)
            return true;

        if (realPivotIndex != 0) {
            pivotIndex = realPivotIndex;
        }

        boolean isPivotGreates = false;
        for (int i = 0; i < pivotIndex; i++) {
            if (numbers[i] <= numbers[pivotIndex]) {
                int temp = numbers[wallIndex];
                numbers[wallIndex] = numbers[i];
                numbers[i] = temp;

                wallIndex++;
            } else {
                isPivotGreates = true;
            }
        }

        if (!isPivotGreates)
            realPivotIndex--;

        if (!hasPivot) {
            realPivotIndex = wallIndex - 1;
            hasPivot = true;
        }

        int temp = numbers[wallIndex];
        numbers[wallIndex] = numbers[pivotIndex];
        numbers[pivotIndex] = temp;

        if (!hasWallIndex) {
            wallIndexForRightPart = wallIndex + 1;
            hasWallIndex = true;
        }

        if (applyQuickLeftPart(numbers, realPivotIndex - 1, 0))
            return true;

        return true;
    }
}
