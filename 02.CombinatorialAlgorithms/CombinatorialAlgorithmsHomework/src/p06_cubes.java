import java.util.HashSet;
import java.util.Scanner;

public class p06_cubes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        HashSet<String> patterns = new HashSet<>();
        StringBuilder a = new StringBuilder("ABC");
        patterns.add(a.toString());

        System.out.println(patterns.contains(new StringBuilder("ABC").toString()));

        System.out.println(patterns.add(new StringBuilder("ABC").toString()));
    }
}
