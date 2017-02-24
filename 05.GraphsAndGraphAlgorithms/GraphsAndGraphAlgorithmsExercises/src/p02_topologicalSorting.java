import java.util.*;

public class p02_topologicalSorting {
    /*
    example inputs
    6
    "A" -> "B", "C"
    "B" -> "D", "E"
    "C" -> "F"
    "D" -> "C", "F"
    "E" -> "D"
    "F"  ->
    //----------------------------------------------------------
    5
    "IDEs" -> "variables", "loops"
    "variables" -> "conditionals", "loops", "bits"
    "conditionals" -> "loops"
    "loops" -> "bits"
    "bits"  ->
    //----------------------------------------------------------
    8
    "5" -> "11"
    "7" -> "11", "8"
    "8" -> "9"
    "11" -> "9", "10", "2"
    "9" ->
    "3" -> "8", "10"
    "2" ->
    "10" ->
     */

    private static Map<String, String[]> childrenByNode = new HashMap<>();
    private static Set<String> isVisited = new HashSet<>();
    private static Set<String> currentVisited = new HashSet<>();
    private static LinkedList<String> toposorted = new LinkedList<>();

    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = in.nextLine().split(" -> ");
            String node = tokens[0];

            if (tokens.length == 2) {
                String[] children = tokens[1].split(", ");
                childrenByNode.put(node, children);
            } else {
                childrenByNode.put(node, new String[0]);
            }
        }

        for (Map.Entry<String, String[]> stringEntry : childrenByNode.entrySet()) {
            String node = stringEntry.getKey();
            if (!isVisited.contains(node)) {
                toposort(node);
            }
        }

        System.out.println(toposorted);
    }

    private static void toposort(String node) throws Exception {
        if (currentVisited.contains(node)) {
            throw new Exception("Graph cannot be topological sorted!");
        }

        if (!isVisited.contains(node)) {
            isVisited.add(node);
            currentVisited.add(node);

            for (String child : childrenByNode.get(node)) {
                toposort(child);
            }

            currentVisited.remove(node);

            toposorted.addFirst(node);
        }
    }
}