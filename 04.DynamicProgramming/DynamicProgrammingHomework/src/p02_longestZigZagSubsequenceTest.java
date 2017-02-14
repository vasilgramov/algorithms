import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class p02_longestZigZagSubsequenceTest {
    @Test
    public void zeroTest1() {
        int[] input = new int[] { 8,3,5,7,0,8,9,10,20,20,20,12,19,11 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(8);
            add(3);
            add(5);
            add(0);
            add(20);
            add(12);
            add(19);
            add(11);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void zeroTest2() {
        int[] input = new int[] { 1,2,3 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void zeroTest3() {
        int[] input = new int[] { 1,3,2 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(2);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void zeroTest4() {
        int[] input = new int[] { 24,5,31,3,3,342,51,114,52,55,56,58 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(24);
            add(5);
            add(31);
            add(3);
            add(342);
            add(51);
            add(114);
            add(52);
            add(55);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void onlyIncreasingNumbers() {
        int[] input = new int[] { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void radnomGenrated1() {
        int[] input = new int[] { 46,11,37,6,84,59,42,68,82,34,34,11,40,36,21,70,16,20,26,35,56,33,97,55,32,71,46,78,31,98 };
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(46);
            add(11);
            add(37);
            add(6);
            add(84);
            add(59);
            add(68);
            add(34);
            add(40);
            add(36);
            add(70);
            add(16);
            add(35);
            add(33);
            add(97);
            add(55);
            add(71);
            add(46);
            add(78);
            add(31);
            add(98);
        }};

        ArrayList<Integer> result = p02_longestZigZagSubsequence.getLongestZigZagSubsequence(input);
        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

}