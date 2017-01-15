import java.util.ArrayList;
import java.util.LinkedList;

public class p05_findAllPathsInLabyrinth {
    static int matrixRows = 5;
    static int matrixCols = 7;

    static char[][] labirynth = new char[][] {
            { '0', '0', '0', '*', '0', '0', '0'},
            { '*', '*', '0', '*', '0', '*', '0'},
            { '0', '0', '0', '0', '0', '0', '0'},
            { '0', '*', '*', '*', '*', '*', '0'},
            { '0', '0', '0', '0', '0', '0', 'e'},
    };

    static LinkedList<Character> paths = new LinkedList<>();


    public static void main(String[] args) {
        printSolution();

        findAllPaths(0, 0, 'F'); // F -> first
    }

    static boolean inRange(int row, int col)
    {
        boolean rowInRange = row >= 0 && row < matrixRows;
        boolean colInRange = col >= 0 && col < matrixCols;
        return rowInRange && colInRange;
    }

    private static void findAllPaths(int row, int col, char direction) {
        if (!inRange(row, col)) {
            return;
        }

        if (labirynth[row][col] == 'e') {
            printSolution();
            return;
        }

        if (labirynth[row][col] != '0') {
            return;
        }

        labirynth[row][col] = '.';

        paths.add(direction);

        findAllPaths(row - 1, col, 'U');
        findAllPaths(row, col + 1, 'R');
        findAllPaths(row + 1, col, 'D');
        findAllPaths(row, col - 1, 'L');

        labirynth[row][col] = '0';
        paths.removeLast();
    }

    private static void printSolution() {
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixCols; j++) {
                System.out.print(labirynth[i][j]);
            }

            System.out.println();
        }


        for (Character step : paths) {
            System.out.print(step);
        }

        System.out.println();
        System.out.println();
    }
}
