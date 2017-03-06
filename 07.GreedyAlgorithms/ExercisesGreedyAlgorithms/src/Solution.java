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
        String sentence = in.nextLine();

        StringBuilder builder = new StringBuilder(sentence);
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == 'a' ||
                    builder.charAt(i) == 'A' ||
                    builder.charAt(i) == 'e' ||
                    builder.charAt(i) == 'E' ||
                    builder.charAt(i) == 'i' ||
                    builder.charAt(i) == 'I' ||
                    builder.charAt(i) == 'o' ||
                    builder.charAt(i) == 'O' ||
                    builder.charAt(i) == 'u' ||
                    builder.charAt(i) == 'U') {
                builder.insert(i + 1, "p");
                builder.insert(i + 2, Character.toChars(builder.charAt(i)));
                i += 2;
            }
        }

        System.out.println(builder);
    }
}