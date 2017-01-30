import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p02_needles {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();


        TreeSet<Integer> appearedNumbers = new TreeSet<>();
        String[] commandArgs = in.readLine().split(" ");
        int numbersCount = Integer.parseInt(commandArgs[0]);
        int needlesCount = Integer.parseInt(commandArgs[1]);

        int[] zerosToIndex = new int[numbersCount];
        ArrayList<Integer> numbers = new ArrayList<>();

        int zerosCounter = 0;
        String[] lineAsString = in.readLine().split(" ");
        for (int i = 0; i < numbersCount; i++) {
            int currentNumber = Integer.parseInt(lineAsString[i]);
            if (currentNumber == 0) {
                zerosToIndex[i] = zerosCounter++;
            } else {
                numbers.add(currentNumber);
                appearedNumbers.add(currentNumber);
            }
        }

        String[] needlesAsString = in.readLine().split(" ");
        for (int i = 0; i < needlesCount; i++) {
            int currentElement = Integer.parseInt(needlesAsString[i]);

            if (currentElement <= appearedNumbers.first()) {
                result.append("0 ");
                continue;
            }

            if (currentElement > appearedNumbers.last()) {
                result.append(numbersCount + " " );
                continue;
            }

            currentElement = appearedNumbers.contains(currentElement) ? currentElement : appearedNumbers.floor(currentElement);

            // binary search
        }

        System.out.println(result);
    }
}
