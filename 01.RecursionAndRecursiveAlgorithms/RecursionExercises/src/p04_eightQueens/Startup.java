package p04_eightQueens;

import java.util.ArrayList;

public class Startup {
    static int counter = 0;

    public static void main(String[] args) {
        int[][] chess = new int[8][8];

        placeQueens(chess, 0);
        System.out.println(counter);
    }

    private static void placeQueens(int[][] chess, int row) {
        if (row == chess.length) {
            printChess(chess);
            return;
        }

        for (int col = 0; col < 8; col++) {
            if (canPlaceQueen(chess[row][col])) {
                Queen currentQueen = new Queen(row, col);

                markAllAttackedPositons(chess, row, col, currentQueen);

                placeQueens(chess, row + 1);

                unmarkAllAttackedPositions(chess, row, col, currentQueen);
            }
        }
    }

    private static void unmarkAllAttackedPositions(int[][] chess, int row, int col, Queen queen) {
        ArrayList<int[][]> attackedPositions = queen.getAttackedPositions();

        for (int i = 0; i < attackedPositions.size(); i++) {
            int[][] currentAttackedPositions = attackedPositions.get(i);
            int attackedRow = currentAttackedPositions[0][0];
            int attackedCol = currentAttackedPositions[0][1];

            chess[attackedRow][attackedCol] = 0;
        }

        chess[queen.getRow()][queen.getCol()] = 0;
    }

    private static void markAllAttackedPositons(int[][] chess, int row, int col, Queen currentQueen) {
        // optimized
//        for (int i = 0; i < 8; i++) {
//            if (chess[row][i] == 0) {
//                currentQueen.addAttackedPosition(row, i);
//                chess[row][i] = -1;
//            }
//        }

        for (int i = 0; i < 8; i++) {
            if (chess[i][col] == 0) {
                currentQueen.addAttackedPosition(i, col);
                chess[i][col] = -1;
            }
        }

        // marking up-left
        int index = 1;
        while (row - index >= 0 && col - index >= 0) {
            if (chess[row - index][col - index] == 0) {
                currentQueen.addAttackedPosition(row - index, col - index);
                chess[row - index][col - index] = -1;
            }

            index++;
        }

        // marking down-right
        index = 1;
        while (row + index < 8 && col + index < 8) {
            if (chess[row + index][col + index] == 0) {
                currentQueen.addAttackedPosition(row + index, col + index);
                chess[row + index][col + index] = -1;
            }

            index++;
        }

        // marking down-left
        index = 1;
        while (row + index < 8 && col - index >= 0) {
            if (chess[row + index][col - index] == 0) {
                currentQueen.addAttackedPosition(row + index, col - index);
                chess[row + index][col - index] = -1;
            }

            index++;
        }

        // marking up-right
        index = 1;
        while (row - index >= 0 && col + index < 8) {
            if (chess[row - index][col + index] == 0) {
                currentQueen.addAttackedPosition(row - index, col + index);
                chess[row - index][col + index] = -1;
            }

            index++;
        }

        // setting queen pos to -2
        chess[row][col] = -2;
    }

    private static boolean canPlaceQueen(int position) {
        if (position < 0)
            return false;

        return true;
    }

    private static void printChess(int[][] chess) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess[i][j] == -2) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }

            System.out.println();
        }

        System.out.println("====================================================");
        counter++;
    }
}
