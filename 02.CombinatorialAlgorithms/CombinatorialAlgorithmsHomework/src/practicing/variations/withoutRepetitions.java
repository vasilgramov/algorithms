package practicing.variations;

import java.util.Arrays;

public class withoutRepetitions {
    static int n = 5;
    static int k = 3;
    static int[] combination = new int[k];

    public static void main(String[] args) {
        createVariationsNoRep(0);
    }

    private static void createVariationsNoRep(int index) {
        if (index == k) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            boolean hasFound = false;
            for (int element : combination) {
                if (element == i) {
                    hasFound = true;
                    break;
                }
            }

            if (hasFound) {
                continue;
            }

            combination[index] = i;
            createVariationsNoRep(index + 1);
        }
    }

    private static void print() {
        System.out.println(Arrays.toString(combination));
    }
}
