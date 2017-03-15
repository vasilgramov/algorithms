import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'V','X', 'Y', 'Z' };
        String S = in.nextLine().toUpperCase();
        for (char aChar : chars) {
            if (!S.contains(Character.toString(aChar))) {
                System.out.println("false");
                return;
            }
        }

        System.out.println("true");
    }
}