import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();

        for (int i = 0; i < s; i++) {
            System.out.println(newString("+", s));
        }
    }

    private static String newString(String str, int n) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            builder.append(str);
        }

        return builder.toString();
    }
}