import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Calculate recursive N-th Fibonacci using memoization
 */

public class fibonacciNumbersMemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine()); // searched nth fibonacci
        long fib = getNthFibonacci(n);

        System.out.println(fib);
    }

    static Map<Integer, Long> valueByN = new HashMap<>();
    private static long getNthFibonacci(int n) {
        if (n <= 2)
            return 1;
        else if (valueByN.containsKey(n))
            return valueByN.get(n);
        else {
            long value = getNthFibonacci(n - 1) + getNthFibonacci(n - 2);
            valueByN.put(n, value);

            return value;
        }
    }
}
