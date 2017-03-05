import java.util.*;

public class p02_setCoverProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] universe = stringsToIntegers(in.nextLine().split(", "));

        int numberOfSets = Integer.parseInt(in.nextLine());
        int[][] sets = new int[numberOfSets][];
        for (int i = 0; i < numberOfSets; i++) {
            int[] set = stringsToIntegers(in.nextLine().split(", "));
            sets[i] = set;
        }

        List<int[]> takenSets = getTakenSets(universe, sets);

        System.out.println(takenSets.size());
        for (int[] takenSet : takenSets) {
            System.out.println(Arrays.toString(takenSet));
        }
    }

    public static List<int[]> getTakenSets(int[] universe, int[][] sets) {
        List<int[]> takenSets = new ArrayList<>();

        while (universe.length > 0) {
            int bestCount = 0;
            int[] bestSet = new int[0];
            for (int i = 0; i < sets.length; i++) {
                int numberOfCommonElements = getCountOfCommonElements(universe, sets[i]);
                if (numberOfCommonElements > bestCount) {
                    bestCount = numberOfCommonElements;
                    bestSet = sets[i];
                }
            }

            if (bestSet.length == 0) {
                System.out.println("ERROR");
                break;
            }

            takenSets.add(bestSet);
            for (int num : bestSet) {
                universe = removeElements(universe, num);
            }

            if (universe.length == 0) {
                break;
            }
        }
        return takenSets;
    }

    private static int[] removeElements(int[] universe, int deleteMe) {
        List<Integer> result = new ArrayList<>();

        int index = 0;
        for (int item : universe) {
            if (item != deleteMe) {
                result.add(item);
            }
        }

        int[] toReturn = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            toReturn[i] = result.get(i);
        }

        return toReturn;
    }

    private static int[] stringsToIntegers(String[] numbersAsString) {
        int[] parsedNumbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }


        return parsedNumbers;
    }

    private static int getCountOfCommonElements(int[] universe, int[] set) {
        int count = 0;

        if (universe.length > set.length) {
            for (int i = 0; i < universe.length; i++) {
                for (int j = 0; j < set.length; j++) {
                    if (universe[i] == set[j]) {
                        count++;
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < set.length; i++) {
                for (int j = 0; j < universe.length; j++) {
                    if (set[i] == universe[j]) {
                        count++;
                        break;
                    }
                }
            }
        }


        return count;
    }
}
