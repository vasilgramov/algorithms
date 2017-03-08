import java.util.Scanner;

public class p03_knightTour {

    public static int[][] knightMoves = new int[][]
    {
        { +1, +2 },
        { -1, +2 },
        { +2, +1 },
        { +1, -2 },
        { -1, -2 },
        { -2, +1 },
        { +2, -1 },
        { -2, -1 }
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        int[][] matrix = new int[n][n];

        Cell cell = new Cell(0, 0);
        matrix[0][0] = 1;

        int stepCount = 2;
        while (true) {
            Cell[] cells = new Cell[8];

            for (int i = 0; i < knightMoves.length; i++) {
                int row = knightMoves[i][0];
                int col = knightMoves[i][1];

                if (check(matrix, cell, row, col)) {
                    cells[i] = new Cell(cell.row + row, cell.col + col);
                }
            }

            int[] onwardCells = new int[8];
            for (int i = 0; i < cells.length; i++) {
                if (cells[i] != null) {
                    Cell currentCell = cells[i];
                    int count = 0;

                    for (int j = 0; j < knightMoves.length; j++) {
                        int row = knightMoves[j][0];
                        int col = knightMoves[j][1];
                        if (check(matrix, currentCell, row, col)) {
                            count++;
                        }
                    }

                    onwardCells[i] = count;
                }
            }

            int min = Integer.MAX_VALUE;
            Cell toCell = null;
            for (int i = 0; i < onwardCells.length; i++) {
                if (onwardCells[i] != 0 && onwardCells[i] < min) {
                    min = onwardCells[i];
                    toCell = cells[i];
                }
            }

            if (min == 0 || toCell == null) {
                for (int i = 0; i < cells.length; i++) {
                    if (cells[i] != null) {
                        matrix[cells[i].row][cells[i].col] = stepCount;
                    }
                }
                break;
            }

            cell = toCell;
            matrix[cell.row][cell.col] = stepCount;
            stepCount++;
        }
        
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static boolean check(int[][] matrix, Cell cell, int row, int col) {
        return isInMatrix(matrix, cell.row + row, cell.col + col) && matrix[cell.row + row][cell.col + col] == 0;
    }

    private static class Cell {
        private int row;
        private int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean isInMatrix(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length && matrix[row][col] == 0;
    }
}
