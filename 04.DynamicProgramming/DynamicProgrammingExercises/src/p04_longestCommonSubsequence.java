import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p04_longestCommonSubsequence {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String firstStr = "GCGCAATG";
        String secondStr = "GCCCTAGCG";

        System.out.println("  first  = " + firstStr);
        System.out.println("  second = " + secondStr);

        String lcs = findLongestCommonSubsequence(firstStr, secondStr);
        System.out.println("Longest common subsequence:");

        System.out.println("  lcs    = " + lcs);
    }

    public static String findLongestCommonSubsequence(String firstStr, String secondStr) {
        int[][] matrix = new int[firstStr.length() + 1][secondStr.length() + 1];

        for (int i = 1; i <= firstStr.length(); i++) {
            for (int j = 1; j <= secondStr.length(); j++) {
                if (firstStr.charAt(i - 1) == secondStr.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }

        StringBuilder LCS = new StringBuilder();
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        while (row > 0 && col > 0) {
            boolean shouldBreak = false;
            while (matrix[row][col] == matrix[row - 1][col]) {
                row--;

                if (row == 0) {
                    shouldBreak = true;
                    break;
                }
            }

            if (shouldBreak)
                break;

            while (firstStr.charAt(row - 1) != secondStr.charAt(col - 1)) {
                col--;

                if (col <= 0) {
                    shouldBreak = true;
                    break;
                }
            }

            if (shouldBreak)
                break;

            LCS.append(firstStr.charAt(row - 1));
            row--;
            col--;
        }

        return LCS.reverse().toString();
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }
}
