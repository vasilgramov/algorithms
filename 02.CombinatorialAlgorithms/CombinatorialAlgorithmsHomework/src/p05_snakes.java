import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class p05_snakes {
    static int snakeSize;
    static char[][] matrix;
    static StringBuilder snake = new StringBuilder();

    static HashMap<Character, Character> comb1 = new HashMap<>();
    static HashMap<Character, Character> comb2 = new HashMap<>();
    static HashMap<Character, Character> comb3 = new HashMap<>();
    static HashMap<Character, Character> comb4 = new HashMap<>();
    static HashMap<Character, Character> comb5 = new HashMap<>();
    static HashMap<Character, Character> comb6 = new HashMap<>();
    static HashMap<Character, Character> comb7 = new HashMap<>();
    static HashMap<Character, Character> comb8 = new HashMap<>();

    static HashSet<String> snakes = new HashSet<>();
    static StringBuilder output = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        snakeSize = n;
        matrix = new char[n * 2][n * 2];

        fillCombinations();

        snake.append("S");
        matrix[n][n] = '@';

        findAllSnakes('R', n, n + 1);

        System.out.println(output + "Snakes count = " + count);
    }

    private static void fillCombinations() {
        comb1.put('U', 'U');
        comb1.put('R', 'R');
        comb1.put('L', 'L');
        comb1.put('D', 'D');

        comb2.put('U', 'R');
        comb2.put('R', 'U');
        comb2.put('L', 'D');
        comb2.put('D', 'L');

        comb3.put('U', 'R');
        comb3.put('R', 'D');
        comb3.put('L', 'U');
        comb3.put('D', 'L');

        comb4.put('U', 'D');
        comb4.put('R', 'R');
        comb4.put('L', 'L');
        comb4.put('D', 'U');

        comb5.put('U', 'D');
        comb5.put('R', 'L');
        comb5.put('L', 'R');
        comb5.put('D', 'U');

        comb6.put('U', 'L');
        comb6.put('R', 'U');
        comb6.put('L', 'D');
        comb6.put('D', 'R');

        comb7.put('U', 'L');
        comb7.put('R', 'D');
        comb7.put('L', 'U');
        comb7.put('D', 'R');

        comb8.put('U', 'U');
        comb8.put('R', 'L');
        comb8.put('L', 'R');
        comb8.put('D', 'D');
    }

    private static void findAllSnakes(char direction, int row, int col) {
        if (!isInMatrix(row, col) || !isCurrentCellFree(row, col))
            return;

        matrix[row][col] = '@';
        snake.append(direction);

        if (snake.length() == snakeSize) {
            generateAllPatterns(snake);

            matrix[row][col] = ' ';
            snake.deleteCharAt(snake.length() - 1);

            return;
        }

        findAllSnakes('R',row, col + 1);
        findAllSnakes('D', row + 1, col);
        findAllSnakes('L', row, col - 1);
        findAllSnakes('U', row - 1, col);

        matrix[row][col] = ' ';
        snake.deleteCharAt(snake.length() - 1);
    }

    private static void generateAllPatterns(StringBuilder snake) {
        if (snakes.size() == 0) {
            snakes.add(snake.toString());
            output.append(snake + "\n");
            count++;
            return;
        }

        if (snakes.contains(snake.toString()))
            return;

        StringBuilder snakeCopy1 = new StringBuilder();
        StringBuilder snakeCopy2 = new StringBuilder();
        StringBuilder snakeCopy3 = new StringBuilder();
        StringBuilder snakeCopy4 = new StringBuilder();
        StringBuilder snakeCopy5 = new StringBuilder();
        StringBuilder snakeCopy6 = new StringBuilder();
        StringBuilder snakeCopy7 = new StringBuilder();
        StringBuilder snakeCopy8 = new StringBuilder();

        snakeCopy1.append("S");
        snakeCopy2.append("S");
        snakeCopy3.append("S");
        snakeCopy4.append("S");
        snakeCopy5.append("S");
        snakeCopy6.append("S");
        snakeCopy7.append("S");
        snakeCopy8.append("S");

        for (int i = 1; i < snake.length(); i++) {
            snakeCopy1.append(comb1.get(snake.charAt(i)));
            snakeCopy2.append(comb2.get(snake.charAt(i)));
            snakeCopy3.append(comb3.get(snake.charAt(i)));
            snakeCopy4.append(comb4.get(snake.charAt(i)));
            snakeCopy5.append(comb5.get(snake.charAt(i)));
            snakeCopy6.append(comb6.get(snake.charAt(i)));
            snakeCopy7.append(comb7.get(snake.charAt(i)));
            snakeCopy8.append(comb8.get(snake.charAt(i)));
        }

        if (snakes.contains(snakeCopy1.toString()) ||
                snakes.contains(snakeCopy2.toString()) ||
                snakes.contains(snakeCopy3.toString()) ||
                snakes.contains(snakeCopy4.toString()) ||
                snakes.contains(snakeCopy5.toString()) ||
                snakes.contains(snakeCopy6.toString()) ||
                snakes.contains(snakeCopy7.toString()) ||
                snakes.contains(snakeCopy8.toString())) {
            return;
        }

        snakeCopy1.deleteCharAt(0);
        snakeCopy2.deleteCharAt(0);
        snakeCopy3.deleteCharAt(0);
        snakeCopy4.deleteCharAt(0);
        snakeCopy5.deleteCharAt(0);
        snakeCopy6.deleteCharAt(0);
        snakeCopy7.deleteCharAt(0);
        snakeCopy8.deleteCharAt(0);

        snakeCopy1 = snakeCopy1.reverse();
        snakeCopy2 = snakeCopy2.reverse();
        snakeCopy3 = snakeCopy3.reverse();
        snakeCopy4 = snakeCopy4.reverse();
        snakeCopy5 = snakeCopy5.reverse();
        snakeCopy6 = snakeCopy6.reverse();
        snakeCopy7 = snakeCopy7.reverse();
        snakeCopy8 = snakeCopy8.reverse();

        snakeCopy1.insert(0, "S");
        snakeCopy2.insert(0, "S");
        snakeCopy3.insert(0, "S");
        snakeCopy4.insert(0, "S");
        snakeCopy5.insert(0, "S");
        snakeCopy6.insert(0, "S");
        snakeCopy7.insert(0, "S");
        snakeCopy8.insert(0, "S");

        if (snakes.contains(snakeCopy1.toString()) ||
                snakes.contains(snakeCopy2.toString()) ||
                snakes.contains(snakeCopy3.toString()) ||
                snakes.contains(snakeCopy4.toString()) ||
                snakes.contains(snakeCopy5.toString()) ||
                snakes.contains(snakeCopy6.toString()) ||
                snakes.contains(snakeCopy7.toString()) ||
                snakes.contains(snakeCopy8.toString())) {
            return;
        }

        snakes.add(snake.toString());
        output.append(snake + "\n");
        count++;
    }

    private static boolean isCurrentCellFree(int row, int col) {
        if (matrix[row][col] == '@')
            return false;

        return true;
    }

    private static boolean isInMatrix(int row, int col) {
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix.length)
            return true;

        return false;
    }
}

