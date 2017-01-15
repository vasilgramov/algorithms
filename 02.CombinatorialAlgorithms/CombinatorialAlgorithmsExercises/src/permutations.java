import java.util.Arrays;
import java.util.Scanner;

public class permutations {
    static int[] nums = {1, 2, 3};

    public static void main(String[] args) {
        perm(0);
    }

    private static void perm(int i) {
        if (i == nums.length) {
            System.out.println(Arrays.toString(nums));
            return;
        }

        perm(i + 1);
        for (int j = i + 1; j < nums.length ; j++) {
            int current = nums[i];
            nums[i] = nums[j];
            nums[j] = current;

            perm(i + 1);

            nums[j] = nums[i];
            nums[i] = current;
        }
    }
}
