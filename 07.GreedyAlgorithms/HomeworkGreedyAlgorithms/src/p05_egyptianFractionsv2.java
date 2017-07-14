import java.util.Scanner;

public class p05_egyptianFractionsv2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] tokens = in.nextLine().split("/");
        int p = Integer.parseInt(tokens[0]);
        int q = Integer.parseInt(tokens[1]);

        if (p >= q) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }

        System.out.print(String.format("%d/%d = ", p, q));

        if (q % p == 0) {
            q = q / p;
            p = 1;
        }

        while (p > 1) {
            int delimiter = (p + q) / p;

            System.out.print(String.format("1/%d + ", delimiter));
            p = p * delimiter - q;
            q = q * delimiter;

            if (q % p == 0) {
                q = q / p;
                p = 1;
                break;
            }
        }

        System.out.println(String.format("1/%d", q));
    }
}
