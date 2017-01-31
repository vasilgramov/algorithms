import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p02_needles {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();
        TreeMap<Integer, Integer> numbers = new TreeMap<>();

        String[] commandArgs = in.readLine().split(" ");
        int numbersCount = Integer.parseInt(commandArgs[0]);
        int needlesCount = Integer.parseInt(commandArgs[1]);

        int zeroes = 0;
        int index = 0;
        String[] numbersAsString = in.readLine().split("\\s+");
        for (int i = 0; i < numbersCount; i++) {
            int currentNumber = Integer.parseInt(numbersAsString[i]);

            if (currentNumber != 0 && !numbers.containsKey(currentNumber)) {
                numbers.put(currentNumber, index - zeroes);
            }

            if (currentNumber == 0) {
                zeroes++;
            } else {
                zeroes = 0;
            }

            index++;
        }

        String[] needles = in.readLine().split("\\s+");
        for (int i = 0; i < needlesCount; i++) {
            int currentNeedle = Integer.parseInt(needles[i]);

            try {
                int searchedNumber  = numbers.containsKey(currentNeedle) ? currentNeedle : numbers.ceilingKey(currentNeedle);
                int sex = numbers.get(searchedNumber);

                result.append(sex + " ");
            } catch (NullPointerException exc) {
                boolean hasFound = false;
                for (int j = numbersAsString.length - 1; j >= 0; j--) {
                    if (!numbersAsString[j].equals("0")) {
                        result.append((j + 1) + " ");
                        hasFound = true;
                        break;
                    }
                }

                if (!hasFound)
                    result.append("0 ");
            }
        }

        System.out.println(result);
    }
}
