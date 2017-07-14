import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();


        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); i += 2) {
            int n = S.charAt(i) -'0';
            char c = S.charAt(i + 1);
            for (int j = 0; j < n; j++) {
                builder.append(Character.toString(c));
            }
        }

        System.out.println(builder.toString());
    }
}