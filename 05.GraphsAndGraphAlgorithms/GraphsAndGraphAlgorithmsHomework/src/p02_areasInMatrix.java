import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p02_areasInMatrix {
    /*
     Find connected cells in matrix
     */

    private static Map<Character, Integer> countByCharacter = new LinkedHashMap<>();
    private static int areasCount = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rows = Integer.parseInt(in.nextLine());
        char[][] matrix = new char[rows][];
        for (int row = 0; row < rows; row++) {
            char[] currentRow = in.nextLine().toCharArray();
            matrix[row] = new char[currentRow.length];
            for (int col = 0; col < currentRow.length; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != '\u0000') {
                    areasCount++;

                    char currentChar = matrix[row][col];
                    if (!countByCharacter.containsKey(currentChar)) {
                        countByCharacter.put(currentChar, 1);
                    } else {
                        countByCharacter.put(currentChar, countByCharacter.get(currentChar) + 1);
                    }

                    dfs(matrix, row, col, currentChar);
                }
            }
        }

        System.out.println("Areas: " + areasCount);
        for (Map.Entry<Character, Integer> characterIntegerEntry : countByCharacter.entrySet()) {
            char key = characterIntegerEntry.getKey();
            int value = characterIntegerEntry.getValue();

            System.out.printf("Letter '%s' -> %d%n", key, value);
        }
    }

    private static void dfs(char[][] matrix, int row, int col, char currentChar) {
        if (!isInMatrix(matrix, row, col) || matrix[row][col] != currentChar) {
            return;
        }

        matrix[row][col] = '\u0000';

        dfs(matrix, row - 1, col, currentChar); // up
        dfs(matrix, row, col + 1, currentChar);  // right
        dfs(matrix, row + 1, col, currentChar); // down
        dfs(matrix, row, col - 1, currentChar);  // left
    }

    private static boolean isInMatrix(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] aMatrix : matrix) {
            for (char anAMatrix : aMatrix) {
                System.out.print(anAMatrix);
            }

            System.out.println();
        }
    }
}
