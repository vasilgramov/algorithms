import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class p04_longestCommonSubsequenceTest {
    @Test
    public void testSimilarStrings() {
        String firstStr = "Petko Marinov";
        String secondStr=  "Pletko Malinov";
        String expectedLCS = "Petko Mainov";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testSimilarLongStrings() {
        String firstStr = "dynamic progamming we brak the oiginal prolem to smaller sub-problms that hae the same strcture";
        String secondStr = "In dynmic prgamming we break th oriinal problem to smaler subprobems that have the sme struture";
        String expectedLCS = "dynmic prgamming we brak th oiinal prolem to smaler subprobms that hae the sme strture";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testEqualStrings()
    {
        String firstStr = "hello";
        String secondStr = "hello";
        String expectedLCS = "hello";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testNonOverlappingStrings()
    {
        String firstStr = "hello";
        String secondStr = "rakiya";
        String expectedLCS = "";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testSingleLetterOverlappingStrings()
    {
        String firstStr = "hello";
        String secondStr = "beer";
        String expectedLCS = "e";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testSingleLetter()
    {
        String firstStr = "a";
        String secondStr = "a";
        String expectedLCS = "a";
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(firstStr, secondStr);

        assertEquals(expectedLCS, result);
    }

    @Test
    public void testPerformance3000Letters()
    {
        String first = "xxxxx" + newString("a", 3000) + "xxxxx";
        String second = "bbb" + newString("a", 3000) + "bbb";
        String expectedLCS = newString("a", 3000);
        String result = p04_longestCommonSubsequence.
                findLongestCommonSubsequence(first, second);

        assertEquals(expectedLCS, result);
    }

    private String newString(String str, int n) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            builder.append(str);
        }

        return builder.toString();
    }
}