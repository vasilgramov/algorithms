import java.util.Arrays;

public class p01_longestIncreasingSubsequence {
    public static void main(String[] args) {
// 30, 1, 20, 2, 3, -1 -> 1, 2, 3
// 3, 14, 5, 12, 15, 7, 8, 9, 11, 10, 1, 12, 13, 14, 20, 15, 30, 16, 17, 40, 18, 19, 20 -> 3, 5, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20

        int[] sequence = { 14, 5, 12, 15, 7, 8, 9, 11, 10, 1 };
        int[] LIS = findLongestIncreasingSubsequence(sequence);
        System.out.println("Longest increasing subsequence (LIS)");
        System.out.println("Length: " + LIS.length);
        System.out.println(Arrays.toString(LIS));
    }

    public static int[] findLongestIncreasingSubsequence(int[] sequence) {
        if (sequence.length == 0)
            return sequence;

        int longestSequenceLength = 0;
        int lastIndex = 0;
        int[] prevIndex = new int[sequence.length];
        int[] helpArray = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            helpArray[i] = 1;
            prevIndex[i] = -1;

            for (int j = 0; j <= i - 1; j++) {
                if (sequence[i] > sequence[j] && helpArray[j] + 1 > helpArray[i]) {
                    helpArray[i] = helpArray[j] + 1;
                    prevIndex[i] = j;
                }
            }

            if (helpArray[i] > longestSequenceLength) {
                longestSequenceLength = helpArray[i];
                lastIndex = i;
            }
        }

        int[] LIS = new int[longestSequenceLength];
        int index = 0;

        while (lastIndex != -1) {
            LIS[index] = sequence[lastIndex];
            lastIndex = prevIndex[lastIndex];
            index++;
        }

        return LIS;
    }
}
