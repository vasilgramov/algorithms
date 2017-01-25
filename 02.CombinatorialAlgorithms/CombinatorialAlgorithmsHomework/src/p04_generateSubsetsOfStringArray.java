import java.util.Scanner;

/*
3
test
rock
fun
2
 */

public class p04_generateSubsetsOfStringArray {
    static String[] arr;
    static int k;
    static int[] indexesForCombination;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int arraySize = Integer.parseInt(in.nextLine());
        arr = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            arr[i] = in.nextLine();
        }

        k = Integer.parseInt(in.nextLine());
        indexesForCombination = new int[k];

        allSubsets(0, 0);
    }

    private static void allSubsets(int index, int startIndex) {
        if (index == k) {
            printComb();
            return;
        }

        for (int i = startIndex; i < arr.length; i++) {
            indexesForCombination[index] = i;
            allSubsets(index + 1, i + 1);
        }
    }

    private static void printComb() {
        for (int i = 0; i < k; i++) {
            System.out.print(arr[indexesForCombination[i]] + " ");
        }

        System.out.println();
    }
}
