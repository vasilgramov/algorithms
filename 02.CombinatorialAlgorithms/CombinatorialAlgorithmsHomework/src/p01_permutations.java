import java.util.*;

public class p01_permutations {
    static int[] arr;
    static int targetSum;

    static Set<String> printed = new HashSet<>();
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] tokens = in.nextLine().split(" ");
        arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        targetSum = Integer.parseInt(in.nextLine());

        createAllPermutations(0);
        System.out.println(result);
    }


    private static void createAllPermutations(int index) {
        if (index == arr.length) {

            int sum = 0;
            Deque<Integer> taken = new ArrayDeque<>();
            for (int i = 0; i < arr.length; i++) {
                sum += (arr[i] * (i + 1));
                taken.addLast(arr[i]);
                if (sum == targetSum) {
                    if (!printed.contains(taken.toString())) {
                        result.append(taken.toString().replace(",", "")).append("\n");
                        printed.add(taken.toString());
                    }
                }


                if (sum > 10) break;
            }

            return;
        }

        createAllPermutations(index + 1);
        for (int i = index + 1; i < arr.length ; i++) {
            int current = arr[index];
            arr[index] = arr[i];
            arr[i] = current;

            createAllPermutations(index + 1);

            arr[i] = arr[index];
            arr[index] = current;
        }
    }
}
