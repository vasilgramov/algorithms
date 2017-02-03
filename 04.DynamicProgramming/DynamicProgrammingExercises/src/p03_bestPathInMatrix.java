import java.util.Random;

public class p03_bestPathInMatrix {
    public static void main(String[] args) {
        // Find the biggest sum in matrix by moving right/down

        // Added random matrix generator by given size
        int[][] matrix = new int[][] {
                { 38, 74, 34, 63, 28, 46, 82, 89 },
                { 1, 58, 58, 50, 23, 89, 51, 90 },
                { 87, 15, 28, 38, 81, 9, 5, 21 },
                { 15, 83, 4, 12, 95, 4, 41, 61 },
                { 68, 74, 63, 32, 95, 48, 9, 83 },
                { 36, 96, 17, 91, 19, 93, 20, 40 },
                { 86, 73, 26, 43, 6, 18, 33, 28 },
                { 74, 16, 87, 3, 35, 20, 3, 95 },
        };

        // First of all we hard code best sums for first row and first col
        int[][] matrixCopy = fillMatrix(matrix);

        getBestPath(matrix, matrixCopy);

        // Recover best path
        int[][] bestPathCoordinates = new int[matrix.length + matrix.length - 1][2];
        bestPathCoordinates[0][0] = 0;
        bestPathCoordinates[0][1] = 0;

        recoverBestPath(matrix, matrixCopy, bestPathCoordinates);

        printBestPath(matrix, matrixCopy[matrix.length - 1], bestPathCoordinates);
    }

    private static void getBestPath(int[][] matrix, int[][] matrixCopy) {
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix.length; col++) {
                if (matrixCopy[row - 1][col] > matrixCopy[row][col - 1])
                    matrixCopy[row][col] = matrixCopy[row - 1][col] + matrix[row][col];
                else
                    matrixCopy[row][col] = matrixCopy[row][col - 1] + matrix[row][col];
            }
        }
    }

    private static void printBestPath(int[][] matrix, int[] ints, int[][] bestPathCoordinates) {
        int indexCounter = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                int bestRow = bestPathCoordinates[indexCounter][0];
                int bestCol = bestPathCoordinates[indexCounter][1];
                if (row == bestRow && col == bestCol) {
                    System.out.print(matrix[row][col] + " ");
                    indexCounter++;
                }
                else
                    System.out.print(" - ");
            }

            System.out.println();
        }

        System.out.println("Best sum: " + ints[matrix.length - 1]);
    }

    private static void recoverBestPath(int[][] matrix, int[][] matrixCopy, int[][] bestPathCoordinates) {
        int indexCounter = 1;
        int row = 0;
        int col = 0;

        while (row < matrix.length - 1 && col < matrix.length - 1) {
            if (matrixCopy[row + 1][col] > matrixCopy[row][col + 1]) {
                bestPathCoordinates[indexCounter][0] = row + 1;
                bestPathCoordinates[indexCounter][1] = col;
                row++;
            } else {
                bestPathCoordinates[indexCounter][0] = row;
                bestPathCoordinates[indexCounter][1] = col + 1;
                col++;
            }

            indexCounter++;
        }

        while (row < matrix.length - 1) {
            bestPathCoordinates[indexCounter][0] = row + 1;
            bestPathCoordinates[indexCounter][1] = col;
            row++;
            indexCounter++;
        }

        while (col < matrix.length - 1) {
            bestPathCoordinates[indexCounter][0] = row;
            bestPathCoordinates[indexCounter][1] = col + 1;
            col++;
            indexCounter++;
        }
    }

    private static int[][] fillMatrix(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][matrix.length];
        matrixCopy[0][0] = matrix[0][0];
        for (int row = 1; row < matrix.length; row++) {
            matrixCopy[row][0] = matrixCopy[row - 1][0] + matrix[row][0];
        }

        for (int col = 1; col < matrix.length; col++) {
            matrixCopy[0][col] += matrixCopy[0][col - 1] + matrix[0][col];
        }
        return matrixCopy;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }

            System.out.println();
        }
    }

    private static int[][] randomMatrix(int n) {
        int[][] randomMatrix = new int[n][n];

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Integer r = rand.nextInt()% 100;
                randomMatrix[i][j] = Math.abs(r);
            }

        }

        return randomMatrix;
    }
}
