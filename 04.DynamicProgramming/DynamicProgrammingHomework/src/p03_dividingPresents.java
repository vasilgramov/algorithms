import java.util.*;

public class p03_dividingPresents {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] inputNumbersAsStrings = in.nextLine().split(",");

        int[] numbers =  new int[inputNumbersAsStrings.length];
        for (int i = 0; i < inputNumbersAsStrings.length; i++) {
            int currentNumber = Integer.parseInt(inputNumbersAsStrings[i]);
            numbers[i] = currentNumber;
        }

        int sum = findSumWithSmallestDifference(numbers);
        System.out.println(sum);

        Map<Integer, List<Integer>> numbersBySum = new HashMap<>();
        numbersBySum.put(0, new ArrayList<>());

        List<Integer> numbersWithSum = getNumbersWithSum(numbers, sum, numbersBySum);
        System.out.println(numbersWithSum);
    }

    private static List<Integer> getNumbersWithSum(int[] numbers, int sum, Map<Integer, List<Integer>> numbersBySum) {
        List<Integer> takenNumbers = new ArrayList<>();
        outerLoop:
        for (int i = 0; i < numbers.length; i++) {
            int currentNumber = numbers[i];

            Map<Integer, List<Integer>> newNumbersBySum = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> integerListEntry : numbersBySum.entrySet()) {
                int currentSum = integerListEntry.getKey();
                List<Integer> currentNumbers = integerListEntry.getValue();

                int newSum = currentSum + currentNumber;
                List<Integer> newNumbers = new ArrayList<>(currentNumbers);
                newNumbers.add(currentNumber);

                if (newSum == sum) {
                    takenNumbers = newNumbers;

                    break outerLoop;
                }

                newNumbersBySum.put(newSum, newNumbers);
            }

            for (Map.Entry<Integer, List<Integer>> integerListEntry : newNumbersBySum.entrySet()) {
                numbersBySum.put(integerListEntry.getKey(), integerListEntry.getValue());
            }
        }

        return takenNumbers;
    }

    public static int findSumWithSmallestDifference(int[] numbers) {
        int sumOfAllNumbers = 0;
        for (int number : numbers) {
            sumOfAllNumbers += number;
        }

        int possibleBestSum = sumOfAllNumbers / 2;
        int bestSum = 0;

        Set<Integer> possibleSums = new HashSet<>();
        possibleSums.add(0);

        for (int currentNumber : numbers) {
            Set<Integer> newSums = new HashSet<>();

            for (Integer possibleSum : possibleSums) {
                int currentSum = currentNumber + possibleSum;

                if (currentSum == possibleBestSum)
                    return currentSum;

                if (Math.abs(possibleBestSum - currentSum) < Math.abs(possibleBestSum - bestSum))
                    bestSum = currentSum;

                newSums.add(currentSum);
            }

            possibleSums.addAll(newSums);
        }

        return bestSum;
    }
}