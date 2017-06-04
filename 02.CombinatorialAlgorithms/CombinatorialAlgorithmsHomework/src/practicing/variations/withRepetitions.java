package practicing.variations;

import java.util.Arrays;

public class withRepetitions {
    static int n = 10;
    static int k = 3;
    static int[] combination = new int[k];

    public static void main(String[] args) {
        createVariationsRep(0);
    }

    private static void createVariationsRep(int index) {
        if (index == 3) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            combination[index] = i;
            createVariationsRep(index + 1);
        }
    }

    private static void print() {
        System.out.println(Arrays.toString(combination));
    }
}
