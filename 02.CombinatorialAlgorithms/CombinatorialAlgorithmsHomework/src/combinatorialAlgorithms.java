import java.util.Arrays;
import java.util.HashSet;

public class combinatorialAlgorithms {
    static int n = 5;
    static int[] arr = new int[3];
    static int count = 0;
    static boolean[] taken = new boolean[n];
    static int[] allElements = new int[]{1, 2, 3, 4, 5};

    static int[] arrAsString = new int[5];
    static int[] elementsAsString = new int[] { 1, 2, 3, 4, 5 };

    static int[] repetitionArray = new int[]{1, 5, 5, 5, 5, 5};

    static int[] nArray = new int[n];

    public static void main(String[] args) {
//        createVariationWithRepetitionIterative(); // n ^ k

//        createVariationWithRepetitionRecursive(0); // n ^ k

//        createVariationsWithoutRepetitionRecursive(0); // boolean[] => (n) * (n - 1) * (n - 2) - k times

//        createVariationsWithoutRepetitionRecursiveOptimized(0); // allElements[] (n) * (n - 1) * (n - 2) - k times

//        createPermutationsWithRepetitionRecursive(0);   // n ^ n

//        createPermutationsWithoutRepetitionRecursive(0);     // n!

        // sort array ???
//        createPermutationsWithRepetitionWithoutRepetitionRecursive(0);

//        Array.Sort(arr);
//        createPermutationsWithRepetitionWithoutRepetitionRecursiveOptimized(arr, 0, arr.length);

        createCombinationsWithRepetition(0, 1);    // (n + k - 1)! / (n - 1)! * k!
//        createCombinationsWithoutRepetition(0, 1);   // n! / (n - k)! * k!

        System.out.println(count);
    }

    private static void createCombinationsWithoutRepetition(int index, int startNum) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            count++;
            return;
        }

        for (int i = startNum; i <= n; i++) {
            arr[index] = i;
            createCombinationsWithoutRepetition(index + 1, i + 1);
        }
    }


    private static void createCombinationsWithRepetition(int index, int startNumber) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            count++;
            return;
        }

        for (int i = startNumber; i <= n; i++) {
            arr[index] = i;
            createCombinationsWithRepetition(index + 1, i);
        }
    }

//    private static void createPermutationsWithRepetitionWithoutRepetitionRecursiveOptimized(int[] arr, int start, int end) {
//         written on c#
//        Print(arr);
//        for (int left = end - 1; left >= start; left--)
//        {
//            for (int right = left + 1; right <= end; right++)
//                if (arr[left] != arr[right])
//                {
//                    Swap(ref arr[left], ref arr[right]);
//                    PermuteRep(arr, left + 1, end);
//                }
//            var firstElement = arr[left];
//            for (int i = left; i <= end - 1; i++)
//                arr[i] = arr[i + 1];
//            arr[end] = firstElement;
//        }
//    }

    private static void createPermutationsWithRepetitionWithoutRepetitionRecursive(int index) {
        if (index == repetitionArray.length) {
            System.out.println(Arrays.toString(repetitionArray));
            count++;
            return;
        }

        HashSet<Integer> swapped = new HashSet<>();
        for (int i = index; i < repetitionArray.length; i++) {
            if (!swapped.contains(repetitionArray[i])) {
                int current = repetitionArray[index];
                repetitionArray[index] = repetitionArray[i];
                repetitionArray[i] = current;

                createPermutationsWithRepetitionWithoutRepetitionRecursive(index + 1);

                repetitionArray[i] = repetitionArray[index];
                repetitionArray[index] = current;

                swapped.add(repetitionArray[i]);
            }
        }
    }

    private static void createPermutationsWithRepetitionRecursive(int index) {
        if (index == n) {
            System.out.println(Arrays.toString(nArray));
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            nArray[index] = i;
            createPermutationsWithRepetitionRecursive(index + 1);
        }
    }

    private static void createPermutationsWithoutRepetitionRecursive(int index) {
        if (index == allElements.length) {
            System.out.println(Arrays.toString(allElements));
            count++;
            return;
        }

        createPermutationsWithoutRepetitionRecursive(index + 1);
        for (int i = index + 1; i < allElements.length; i++) {
            int current = allElements[index];
            allElements[index] = allElements[i];
            allElements[i] = current;

            createPermutationsWithoutRepetitionRecursive(index + 1);

            allElements[i] = allElements[index];
            allElements[index] = current;
        }
    }

    private static void createVariationsWithoutRepetitionRecursive(int index) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!taken[i - 1]) {
                taken[i - 1] = true;

                arr[index] = i;
                createVariationsWithoutRepetitionRecursive(index + 1);

                taken[i - 1] = false;
            }
        }
    }


    private static void createVariationsWithoutRepetitionRecursiveOptimized(int index) {  // with swap
        if (index == arrAsString.length) {
            System.out.println(Arrays.toString(arrAsString));
            count++;
            return;
        }

        for (int i = index; i < arrAsString.length; i++) {
            int temp = elementsAsString[i];
            elementsAsString[i] = elementsAsString[index];
            elementsAsString[index] = elementsAsString[i];

            arrAsString[index] = temp;
            createVariationsWithoutRepetitionRecursiveOptimized(index + 1);

            elementsAsString[index] = elementsAsString[i];
            elementsAsString[i] = temp;
        }
    }

    private static void createVariationWithRepetitionIterative() {
        while (true) {
            System.out.println(Arrays.toString(arr));
            count++;

            int index = arr.length - 1;

            while (index >= 0 && arr[index] == n - 1)
                index--;

            if (index < 0)
                return;

            arr[index]++;

            for (int i = index + 1; i < arr.length; i++) {
                arr[i] = 0;
            }
        }
    }

    private static void createVariationWithRepetitionRecursive(int index) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            arr[index] = i;
            createVariationWithRepetitionRecursive(index + 1);
        }
    }
}