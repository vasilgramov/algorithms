import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class p01_longestIncreasingSubsequenceTest {
    @Test
    public void testLIS() {
        int[] sequence = { 3, 14, 5, 12, 15, 7, 8, 9, 11, 10, 1 };
        int[] LIS = { 11, 9, 8, 7, 5, 3 };

        int[] result = p01_longestIncreasingSubsequence.findLongestIncreasingSubsequence(sequence);

        assertArrayEquals(LIS, result);
    }
}