public class p04_floydWarshall {

    private static final int MAGIC_NUMBER = Integer.MAX_VALUE / 2 - 666;

    public static void main(String[] args) {
        int[][] pattern = {
                // 0   1   2   3   4   5   6   7   8   9  10  11
                { 0,  0,  0,  0,  0,  0, 10,  0, 12,  0,  0,  0 }, // 0
                { 0,  0,  0,  0, 20,  0,  0, 26,  0,  5,  0,  6 }, // 1
                { 0,  0,  0,  0,  0,  0,  0, 15, 14,  0,  0,  9 }, // 2
                { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0 }, // 3
                { 0, 20,  0,  0,  0,  5, 17,  0,  0,  0,  0, 11 }, // 4
                { 0,  0,  0,  0,  5,  0,  6,  0,  3,  0,  0, 33 }, // 5
                {10,  0,  0,  0, 17,  6,  0,  0,  0,  0,  0,  0 }, // 6
                { 0, 26, 15,  0,  0,  0,  0,  0,  0,  3,  0, 20 }, // 7
                {12,  0, 14,  0,  0,  3,  0,  0,  0,  0,  0,  0 }, // 8
                { 0,  5,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0 }, // 9
                { 0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0 }, // 10
                { 0,  6,  9,  0, 11, 33,  0, 20,  0,  0,  0,  0 }, // 11
        };

        int[][] distance = new int[pattern.length][pattern.length];
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (i != j) {
                    if (pattern[i][j] == 0) {
                        distance[i][j] = MAGIC_NUMBER;
                    } else {
                        distance[i][j] = pattern[i][j];
                    }
                }
            }
        }

        int vertices = pattern.length;
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        /*
        0 - 9 -> path: [0, 8, 5, 4, 11, 1, 9]; distance: 42
        0 - 2 -> path: [0, 8, 2]; distance: 26
        0 - 10 -> path: no path; distance: -1;
        0 - 11 -> path: [0, 8, 5, 4, 11]; distance: 31
        0 - 1 -> path: [0, 8, 5, 4, 11, 1]; distance 37
        */

        System.out.println(distance[0][1]);
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
