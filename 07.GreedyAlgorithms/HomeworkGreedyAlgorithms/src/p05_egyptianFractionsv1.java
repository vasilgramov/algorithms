import java.text.DecimalFormat;
import java.util.Scanner;

public class p05_egyptianFractionsv1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] inputArgs = in.nextLine().split("/");
        double p = Double.parseDouble(inputArgs[0]);
        double q = Double.parseDouble(inputArgs[1]);

        double fraction = p / q;

        double divider = 2;
        if (fraction >= 1) {
            System.out.println("Error(fraction is equal to or greater than 1)");
        } else {
            StringBuilder builder = new StringBuilder();

            DecimalFormat decimalFormat = new DecimalFormat("0.#####");

            while (fraction >= 0.0000001) {
                double newFraction = 1 / divider;

                if (newFraction <= fraction) {
                    builder.append("1/" + decimalFormat.format(divider) + " + ");
                    fraction -= newFraction;
                }

                divider++;
            }

            System.out.println(builder.toString().substring(0, builder.length() - 2));
        }
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
