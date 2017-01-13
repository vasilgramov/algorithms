import java.util.LinkedList;
import java.util.Scanner;

/*
4
4
0000
0**0
0*e0
0000
 */
//------------------------------------------
/*
5
6
000000
0**0*0
0**0*0
0*e000
000*00
 */

public class p06_pathsBetweenCellsInMatrix {
    static LinkedList<Character> directions = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rows = Integer.parseInt(in.nextLine());
        int cols = Integer.parseInt(in.nextLine());
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            char[] currentRow = in.nextLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = currentRow[j];
            }
        }

        findAllPaths(matrix, 0, 0, 'S', rows, cols);
        System.out.println();
    }

    private static void findAllPaths(char[][] matrix, int row, int col, char direction, int totalRows, int totalCols) {
        if (row < 0 || row >= totalRows ||
                col < 0 || col >= totalCols ||
                matrix[row][col] == '*' ||
                matrix[row][col] == '1') {
            return;
        }

        if (matrix[row][col] == 'e') {
            directions.addLast(direction);
            printSolution(matrix, totalRows, totalCols);
            directions.removeLast();
            return;
        }

        matrix[row][col] = '1';
        directions.addLast(direction);

        findAllPaths(matrix, row - 1, col, 'U', totalRows, totalCols);
        findAllPaths(matrix, row, col + 1, 'R', totalRows, totalCols);
        findAllPaths(matrix, row + 1, col, 'D', totalRows, totalCols);
        findAllPaths(matrix, row, col - 1, 'L', totalRows, totalCols);

        matrix[row][col] = '0';
        directions.removeLast();
    }

    private static void printSolution(char[][] matrix, int totalRows, int totalCols) {
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();
        }

        System.out.println(directions);
        System.out.println("==========================================");
    }


}
