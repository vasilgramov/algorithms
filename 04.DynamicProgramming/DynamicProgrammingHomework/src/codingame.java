import java.util.Scanner;

public class codingame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();

        char firstChar = S.charAt(1);

        if (S.charAt(3) == '0') {
            System.out.println(firstChar + " 0" + " 0" + " 0" + " " + S.charAt(4));
        }
    }
}