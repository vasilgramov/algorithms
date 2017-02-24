import java.util.*;

public class p03_cyclesInGraph {
    /*
     Check if graph is acyclic
     */

    private static Map<String, Set<String>> connections = new HashMap<>();
    private static Set<String> visitedNodes = new HashSet<>();

    private static boolean hasFoundCycle = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        buildGraph(in);

        Map.Entry<String, Set<String>> stringListEntry = connections.entrySet().iterator().next();
        String node = stringListEntry.getKey();

        dfs(node);

        System.out.println("Acyclic (no cycle) ? " + (hasFoundCycle ? "No" : "Yes"));
    }

    private static void buildGraph(Scanner in) {
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] connection = in.nextLine().split(" – ");
            String node = connection[0];
            String child = connection[1];

            updateConnections(node, child);
        }
    }

    private static void dfs(String node) {
        if (visitedNodes.contains(node)) {
            hasFoundCycle = true;
            return;
        }

        visitedNodes.add(node);

        Set<String> nodes = connections.get(node);
        for (String child : nodes) {
            Set<String> byChildren = connections.get(child);
            byChildren.remove(node);
            connections.put(child, byChildren);

            dfs(child);

            if (hasFoundCycle) {
                return;
            }
        }
    }

    private static void updateConnections(String firstNode, String secondNode) {
        update(firstNode, secondNode);
        update(secondNode, firstNode);
    }

    private static void update(String firstNode, String secondNode) {
        if (!connections.containsKey(firstNode)) {
            Set<String> connectedNodes = new HashSet<>();
            connectedNodes.add(secondNode);

            connections.put(firstNode, connectedNodes);
        } else {
            Set<String> connectedNodes = connections.get(firstNode);
            connectedNodes.add(secondNode);

            connections.put(firstNode, connectedNodes);
        }
    }
}
