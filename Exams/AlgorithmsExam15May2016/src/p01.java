import java.util.Scanner;

public class p01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] input = in.nextLine().split("\\s+");
        int b1 = Integer.parseInt(input[0]);
        int b2 = Integer.parseInt(input[1]);

        int c = 0;
        while (1 != 0) {
            b1 *= 3;
            b2 *= 2;
            c++;

            if (b1 > b2) {
                System.out.println(c);
                return;
            }
        }

    }
}
