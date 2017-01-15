import java.util.Scanner;

public class p01_recursiveArraySum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int arrayLength = Integer.parseInt(in.nextLine());
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = in.nextInt();
        }

        int sumOfArray = getArraySum(array, arrayLength - 1);
        System.out.println(sumOfArray);
    }

    private static int getArraySum(int[] array, int index) {
        if (index == 0) {
            return array[index];
        } else {
            int sum = array[index] + getArraySum(array, index - 1);
            return sum;
        }
    }
}
