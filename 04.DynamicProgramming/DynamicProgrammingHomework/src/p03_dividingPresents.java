import java.util.*;

public class p03_dividingPresents {

    static int sumOfAllPresents;
    static int possiblyBestSum;
    static List<Integer> bestPresents;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] inputNumbersAsStrings = in.nextLine().split(",");
        
        sumOfAllPresents = 0;
        int[] numbers =  new int[inputNumbersAsStrings.length];
        for (int i = 0; i < inputNumbersAsStrings.length; i++) {
            int currentNumber = Integer.parseInt(inputNumbersAsStrings[i]);
            numbers[i] = currentNumber;
            sumOfAllPresents += currentNumber;
        }

        possiblyBestSum = sumOfAllPresents / 2;

        int smallestDifference = findSmallDifference(numbers);
        System.out.println("Difference: " + smallestDifference);
        System.out.println("Presents one of the twins takes: " + bestPresents);
    }

    private static int findSmallDifference(int[] numbers) {
        Map<List<Integer>, Integer> sumsByPresents = new HashMap<>();
        List<Integer> firstPresent = new ArrayList<>();
        firstPresent.add(numbers[0]);
        sumsByPresents.put(firstPresent, numbers[0]);

        bestPresents = firstPresent;
        int minDifferenceSoFar = possiblyBestSum - numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            int currentPresent = numbers[i];

            Map<List<Integer>, Integer> newSumsByPresents = new HashMap<>();

            for (Map.Entry<List<Integer>, Integer> listIntegerEntry : sumsByPresents.entrySet()) {
                List<Integer> newPresents = new ArrayList<>();
                newPresents.add(currentPresent);
                int newSumOfPresents = currentPresent;

                List<Integer> currentPresents =  listIntegerEntry.getKey();
                int currentSumOfPresents = listIntegerEntry.getValue();

                newPresents.addAll(currentPresents);
                newSumOfPresents += currentSumOfPresents;

                newSumsByPresents.put(newPresents, newSumOfPresents);

                int currentDiff = Math.abs(possiblyBestSum - newSumOfPresents);
                if (currentDiff < minDifferenceSoFar) {
                    minDifferenceSoFar = currentDiff;
                    bestPresents = newPresents;
                }
            }

            for (Map.Entry<List<Integer>, Integer> listIntegerEntry : newSumsByPresents.entrySet()) {
                List<Integer> key = listIntegerEntry.getKey();
                int value = listIntegerEntry.getValue();

                sumsByPresents.put(key, value);
            }
        }

        int sumOfBestPresents = sumsByPresents.get(bestPresents);
        int smallestDifference = Math.abs(Math.abs(sumOfAllPresents - sumOfBestPresents) - sumOfBestPresents);
        return smallestDifference;
    }
}