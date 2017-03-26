import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p01_medenka {

    private static int[] elements;
    private static StringBuilder result = new StringBuilder();
    private static List<Integer> nutsIndeces = new ArrayList<>();

    private static List<Integer> cuts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        elements = stringsToIntegers(in.nextLine().split("\\s+"));
        getNutsIndeces();

        if (nutsIndeces.size() == 1) {
            for (int i = 0; i < elements.length; i++) {
                System.out.print(elements[i]);
            }
        } else {
            generateCuts(0);
            System.out.println(result.toString().trim());
        }
    }

    private static int[] stringsToIntegers(String[] numbersAsString) {
        int[] parsedNumbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }


        return parsedNumbers;
    }

    private static void getNutsIndeces() {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == 1) {
                nutsIndeces.add(i);
            }
        }
    }

    private static void generateCuts(int index) {
        if (index == nutsIndeces.size() - 1) {
            print();
            return;
        }

        int currentNutIndex = nutsIndeces.get(index);
        int nextNutIndex = nutsIndeces.get(index + 1);
        for (int i = currentNutIndex; i < nextNutIndex; i++) {
            cuts.add(i);
            generateCuts(index + 1);
            cuts.remove(cuts.size() - 1);
        }
    }

    private static void print() {
        int currentCut = 0;
        for (int i = 0; i < cuts.size(); i++)
        {
            // Append all elements before cut
            for (int j = currentCut; j < cuts.get(i) + 1; j++)
            {
                result.append(elements[j]);
            }

            // Append cut
            result.append('|');
            currentCut = cuts.get(i) + 1;
        }

        // Add all 0s and 1s after last cut
        int lastCut = cuts.get(cuts.size() - 1);
        for (int i = lastCut + 1; i < elements.length; i++)
        {
            result.append(elements[i]);
        }

        result.append('\n');
    }
}
