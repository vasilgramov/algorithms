import java.util.Scanner;

/*
4
9
000*000*0
000*000*0
000*000*0
0000*0*00
 */

/*
5
10
*00*000*00
*00*000*00
*00*****00
*00*000*00
*00*000*00
 */

public class p07_connectedAreasInAMatrix {
    static int areasCounter = 0;
    static int currentSize = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rows = Integer.parseInt(in.nextLine());
        int cols = Integer.parseInt(in.nextLine());

        char[][] matrix = new char[rows][cols];
        fillingMatrix(in, rows, cols, matrix);

        findAllConnectedAreas(matrix, rows, cols);
    }

    private static void findAllConnectedAreas(char[][] matrix, int totalRows, int totalCols) {
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (matrix[row][col] == '0') {
                    areasCounter++;
                    markArea(matrix, row, col, totalRows, totalCols);

                    System.out.printf("Area %d at (%d, %d), size %d\n", areasCounter, row, col, currentSize);
                    currentSize = 0;
                }
            }
        }
    }

    private static void markArea(char[][] matrix, int row, int col, int totalRows, int totalCols) {
        if (row < 0 || row >= totalRows || col < 0 || col >= totalCols)
            return;


        if (matrix[row][col] != '0')
            return;

        matrix[row][col] = '1';
        currentSize++;

        markArea(matrix, row + 1, col, totalRows, totalCols);
        markArea(matrix, row, col + 1, totalRows, totalCols);
        markArea(matrix, row - 1, col, totalRows, totalCols);
        markArea(matrix, row, col - 1, totalRows, totalCols);
    }

    private static boolean isInMatrix(int row, int col, int totalRows, int totalCols) {
        boolean isInRow = row >= 0 && row < totalRows;
        boolean isInCol = col >= 0 && col < totalCols;

        return isInRow && isInCol;
    }

    private static void fillingMatrix(Scanner in, int rows, int cols, char[][] matrix) {
        for (int i = 0; i < rows; i++) {
            char[] currentRow = in.nextLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = currentRow[j];
            }
        }
    }

    private static void printMatrix(char[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();
        }

        System.out.println("==================================================");
    }
}
