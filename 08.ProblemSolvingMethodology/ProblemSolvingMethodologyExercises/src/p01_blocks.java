import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p01_blocks {

    private static char[] allSymbols;
    private static char[] currentPermutation = new char[4];
    private static Set<String> blocks = new TreeSet<>();
    private static int n;
    private static boolean[] taken;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        allSymbols = new char[n];
        taken = new boolean[n];

        for (int i = 0; i < allSymbols.length; i++) {
            allSymbols[i] = (char) (65 + i);
        }

        createVariations(0);

        StringBuilder builder = new StringBuilder();
        System.out.println("Number of blocks: " + blocks.size());
        for (String block : blocks) {
            builder.append(block).append("\n");
        }

        System.out.println(builder.toString().trim());
    }

    private static void createVariations(int index) {
        if (index == currentPermutation.length) {
            String permutation = new String(currentPermutation);

            String copyOfPermutation = new String(currentPermutation);

            boolean hasFound = rotate(0, permutation);
            if (!hasFound) {
                blocks.add(copyOfPermutation);
            }

            return;
        }

        for (int i = index; i < allSymbols.length; i++) {
            char temp = allSymbols[i];
            allSymbols[i] = allSymbols[index];
            allSymbols[index] = allSymbols[i];

            currentPermutation[index] = temp;
            createVariations(index + 1);

            allSymbols[index] = allSymbols[i];
            allSymbols[i] = temp;
        }
    }

    private static boolean rotate(int counter, String currentPermutation) {
        if (counter > 3) {
            return false;
        }

        if (blocks.contains(currentPermutation)) {
            return true;
        }

        char firstChar = currentPermutation.charAt(0);
        currentPermutation = currentPermutation.substring(1) + firstChar;
        return rotate(counter + 1, currentPermutation);
    }
}
