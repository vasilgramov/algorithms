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

        ArrayDeque<Character> brackets = new ArrayDeque<>();
        String input = in.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char currentBracket = input.charAt(i);

            switch (currentBracket) {
                case '(':
                case '[':
                case '{':
                    brackets.addLast(currentBracket);
                    break;
                case ')':
                    if (isEmpty(brackets)) {
                        System.out.println("NO");
                        return;
                    }

                    char round = brackets.removeLast();
                    if (round != '(' || currentBracket != ')') {
                        System.out.println("NO");
                        return;
                    }
                    break;
                case ']':
                    if (isEmpty(brackets)) {
                        System.out.println("NO");
                        return;
                    }

                    char square = brackets.removeLast();
                    if (square != '[' || currentBracket != ']') {
                        System.out.println("NO");
                        return;
                    }
                    break;
                case '}':
                    if (isEmpty(brackets)) {
                        System.out.println("NO");
                        return;
                    }

                    char curly = brackets.removeLast();
                    if (curly != '{' || currentBracket != '}') {
                        System.out.println("NO");
                        return;
                    }
                    break;
            }
        }


        if (!isEmpty(brackets))
            System.out.println("false");
        else
            System.out.println("true");

    }

    private static boolean isEmpty(ArrayDeque<Character> brackets) {
        if (brackets.size() == 0) {
            return true;
        }

        return false;
    }
}