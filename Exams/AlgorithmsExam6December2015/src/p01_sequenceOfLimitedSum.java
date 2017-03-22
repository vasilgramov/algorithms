import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class p01_sequenceOfLimitedSum {

    private static int n;
    private static Deque<Integer> sequence = new LinkedList<>();

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = Integer.parseInt(in.nextLine());

        generateSequences(0);

        System.out.println(result);
    }

    private static void generateSequences(int sum) {
        for (int i = 1; i <= n; i++) {
            if (sum + i <= n) {
                sequence.add(i);

                for (Integer integer : sequence) {
                    result.append(integer).append(" ");
                }

                result.append("\n");

                generateSequences(sum + i);
            } else {
                break;
            }

            sequence.removeLast();
        }
        
    }
}
