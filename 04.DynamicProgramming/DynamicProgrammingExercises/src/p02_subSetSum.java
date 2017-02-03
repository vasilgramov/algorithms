import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p02_subSetSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int targetSum = 9;
        int[] nums = { 1, 7, 7, 1, 12 };

        if (isPossibleToSum(targetSum, nums))
            System.out.println("Yes");
        else
            System.out.println("No");

    }

    private static boolean isPossibleToSum(int targetSum, int[] nums) {
        Set<Integer> allPossibleSums = new HashSet<>();
        allPossibleSums.add(0);

        for (int num : nums) {
            Set<Integer> newSums = new HashSet<>();
            for (Integer sum : allPossibleSums) {
                newSums.add(sum + num);
            }

            allPossibleSums.addAll(newSums);
            if (allPossibleSums.contains(targetSum))
                return true;
        }

        return false;
    }
}
