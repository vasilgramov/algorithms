import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class p01_words {
    static int count;
    static char[] input;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HashMap<Character, Integer> appearanceBySymbol = new HashMap<>();
        input = in.nextLine().toCharArray();
        if (optimization(appearanceBySymbol)) return;

        Arrays.sort(input);
        permuteRep(0);
        System.out.println(count);
    }

    private static boolean optimization(HashMap<Character, Integer> appearanceBySymbol) {
        for (int i = 0; i < input.length; i++) {
            if (!appearanceBySymbol.containsKey(input[i])) {
                appearanceBySymbol.put(input[i], 1);
            } else {
                appearanceBySymbol.put(input[i], appearanceBySymbol.get(input[i] + 1));
            }
        }

        if (input.length == appearanceBySymbol.size()) {
            System.out.println(calculateFactorial(input.length));
            return true;
        }
        return false;
    }

    private static int calculateFactorial(int length) {
        int fact = 1;
        for (int i = 2; i <= length; i++) {
            fact *= i;
        }

        return fact;
    }


    private static void permuteRep(int index) {
        if (index == input.length) {
            boolean hasFound = true;
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] == input[i + 1]) {
                    hasFound = false;
                    break;
                }
            }

            if (hasFound)
                count++;

            return;
        }

        HashSet<Character> swapped = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (!swapped.contains(input[i])) {
                char current = input[index];
                input[index] = input[i];
                input[i] = current;

                permuteRep(index + 1);

                input[i] = input[index];
                input[index] = current;

                swapped.add(input[i]);
            }
        }
    }
}
