import java.util.*;

public class p01_distanceBetweenVertices {
    /*
    Get number of edges between two vertices
     */

    static Map<Integer, int[]> childrenByNode = new HashMap<>();

    static Deque<Child> queue = new ArrayDeque<>();
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] commandArgs = in.nextLine().split(" -> ");
            int node = Integer.parseInt(commandArgs[0]);
            int[] children = new int[0];
            if (commandArgs.length == 2) {
                children = stringsToIntegers(commandArgs[1].split(", "));
            }

            childrenByNode.put(node, children);
        }

        n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            int[] tokens = stringsToIntegers(in.nextLine().split("-"));
            int start = tokens[0];
            int end = tokens[1];

            Child child = new Child(start, 1);
            queue.addLast(child);

            System.out.printf("{%d, %d} -> ", start, end);
            System.out.println(findShortestPathTo(end));

            queue.clear();
            visited.clear();
        }
    }

    private static int findShortestPathTo(int end) {
        while (!queue.isEmpty()) {
            Child currentNode = queue.removeFirst();
            int[] children = childrenByNode.get(currentNode.value);

            for (int child : children) {
                if (!visited.contains(child)) {
                    visited.add(child);

                    if (child == end) {
                        return currentNode.layer;
                    }

                    queue.addLast(new Child(child, currentNode.layer + 1));
                }
            }
        }

        return -1;
    }



    private static int[] stringsToIntegers(String[] numbersAsString) {
        int[] parsedNumbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }


        return parsedNumbers;
    }

    static class Child {
        int value;
        int layer;

        public Child(int value, int layer) {
            this.value = value;
            this.layer = layer;
        }
    }
}
