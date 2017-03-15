import java.util.*;

public class p02_zigzagMatrix {

    private static int rows;
    private static int cols;
    private static int[][] matrix;

    private static int[][] maxPath;
    private static int[][] previousIndex;


    public static void main(String[] args) {
        read();

        logic();

        print();
    }

    private static void print() {
        int maxRowValue = Integer.MIN_VALUE;
        int maxRowIndex = -1;

        for (int i = 0; i < rows; i++) {
            if (maxPath[i][cols - 1] > maxRowValue) {
                maxRowValue = maxPath[i][cols - 1];
                maxRowIndex = i;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(maxRowValue).append(" = ");

        Deque<Integer> usedNumbers = new LinkedList<>();
        int columnIndex = cols - 1;
        while (columnIndex >= 0) {
            usedNumbers.addFirst(matrix[maxRowIndex][columnIndex]);
            maxRowIndex = previousIndex[maxRowIndex][columnIndex];
            columnIndex--;
        }

        for (Integer usedNumber : usedNumbers) {
            result.append(usedNumber + " + ");
        }

        System.out.println(result.substring(0, result.length() - 3));
    }

    private static void logic() {
        maxPath = new int[rows][cols];
        previousIndex = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            maxPath[row][0] = matrix[row][0];
        }

        boolean goDown = true;
        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int prevMaxValue = Integer.MIN_VALUE;

                if (goDown) {
                    for (int i = row + 1; i < rows; i++) {
                        if (maxPath[i][col - 1] > prevMaxValue) {
                            prevMaxValue = maxPath[i][col - 1];
                            previousIndex[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (maxPath[i][col - 1] > prevMaxValue) {
                            prevMaxValue = maxPath[i][col - 1];
                            previousIndex[row][col] = i;
                        }
                    }
                }

                maxPath[row][col] = prevMaxValue + matrix[row][col];
            }

            goDown = !goDown;
        }
    }

    private static void read() {
        Scanner in = new Scanner(System.in);

        rows = Integer.parseInt(in.nextLine());
        cols = Integer.parseInt(in.nextLine());

        matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] currentLine = in.nextLine().split(",");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(currentLine[col]);
            }
        }
    }
}
