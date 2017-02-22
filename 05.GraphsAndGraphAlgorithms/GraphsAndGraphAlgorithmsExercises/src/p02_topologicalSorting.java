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

    static Map<String, String[]> childrenByNode = new HashMap<>();
    static Map<String, Boolean> visited = new HashMap<>();

    static Set<String> currentVisited = new HashSet<>();

    static LinkedList<String> topoSorted = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        for (int i = 0; i < n; i++) {
            String[] commandArgs = in.nextLine().replaceAll("\"", "").split(" -> ");
            String node = commandArgs[0].trim();
            String[] children = new String[0];

            if (commandArgs.length == 2 && !commandArgs[1].equals("")) {
                children = commandArgs[1].split(", ");
            }

            childrenByNode.put(node, children);
            visited.put(node, false);
        }


        boolean hasBeenSorted = false;

        for (Map.Entry<String, String[]> stringEntry : childrenByNode.entrySet()) {
            String node = stringEntry.getKey();

            try {
                topSortDFS(node);
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
            }

            if (topoSorted.size() == n) {
                hasBeenSorted = true;
                break;
            }

            for (Map.Entry<String, Boolean> stringBooleanEntry : visited.entrySet()) {
                stringBooleanEntry.setValue(false);
            }

            topoSorted = new LinkedList<>();
        }


        if (!hasBeenSorted) {
            System.out.println("Graph cannot be topologically sorted!");
            return;
        }

        System.out.println(String.join(" -> ", topoSorted));
    }

    private static void topSortDFS(String node) throws Exception {
        if (currentVisited.contains(node)) {
            throw new Exception("Cycle detected!");
        }

        if (!visited.get(node)) {
            visited.put(node, true);
            currentVisited.add(node);

            String[] children = childrenByNode.get(node);

            for (String child : children) {
                topSortDFS(child);
            }

            currentVisited.remove(node);

            topoSorted.addFirst(node);
        }
    }
}
