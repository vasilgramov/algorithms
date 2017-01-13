import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class p04_towerOfHanoi {
    static int stepsCount = 1;

    // using ArrayList instead of LinkedList for the "clearer" output
    static ArrayList<Integer> source = new ArrayList<>();
    static ArrayList<Integer> spare = new ArrayList<>();
    static ArrayList<Integer> destination = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of discs: ");
        int n = in.nextInt();
        fillSource(n, source);

        moveDiscs(n, source, spare, destination, "Source", "Spare", "Destination");

        System.out.println("destination rod " + destination);
    }

    static void moveDiscs(int n, ArrayList<Integer> source, ArrayList<Integer> spare, ArrayList<Integer> destination, String from, String using, String to)
    {
        if (n > 0) {
            moveDiscs(n - 1, source, destination, spare, from, to, using);

            int disk = source.get(source.size() - 1);
            source.remove(source.size() - 1);
            System.out.printf("Step #%d: move disk %d from %s to %s\n", stepsCount, disk, from, to);
            destination.add(destination.size(), disk);
            printRods();

            stepsCount++;

            moveDiscs(n - 1, spare, source, destination, using, from, to);
        }
    }

    private static void printRods() {
        System.out.println("Source: " + source);
        System.out.println("Space: "+ spare);
        System.out.println("Destination: " + destination);
    }

    private static void fillSource(int n, ArrayList<Integer> source) {
        for (int i = n; i >= 1; i--) {
            source.add(i);
        }
    }
}
