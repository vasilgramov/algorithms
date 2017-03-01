import java.util.*;

public class p02_dijkstra {
//                      index     connections weight
    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    private static int[] previous;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[][] pattern = {
            // 0   1   2   3   4   5   6   7   8   9  10  11
            { 0,  0,  0,  0,  0,  0, 10,  0, 12,  0,  0,  0 }, // 0
            { 0,  0,  0,  0, 20,  0,  0, 26,  0,  5,  0,  6 }, // 1
            { 0,  0,  0,  0,  0,  0,  0, 15, 14,  0,  0,  9 }, // 2
            { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0 }, // 3
            { 0, 20,  0,  0,  0,  5, 17,  0,  0,  0,  0, 11 }, // 4
            { 0,  0,  0,  0,  5,  0,  6,  0,  3,  0,  0, 33 }, // 5
            {10,  0,  0,  0, 17,  6,  0,  0,  0,  0,  0,  0 }, // 6
            { 0, 26, 15,  0,  0,  0,  0,  0,  0,  3,  0, 20 }, // 7
            {12,  0, 14,  0,  0,  3,  0,  0,  0,  0,  0,  0 }, // 8
            { 0,  5,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0 }, // 9
            { 0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0 }, // 10
            { 0,  6,  9,  0, 11, 33,  0, 20,  0,  0,  0,  0 }, // 11
        };

        buildGraph(pattern);

        int startNode = Integer.parseInt(in.nextLine());
        int endNode = Integer.parseInt(in.nextLine());

        /*
        // You can see the graph here -> https://gyazo.com/8bf6381db163bf304875aadd32b10b98 

        0 - 9 -> path: [0, 8, 5, 4, 11, 1, 9]; distance: 42
        0 - 2 -> path: [0, 8, 2]; distance: 26
        0 - 10 -> path: no path; distance: -1;
        0 - 11 -> path: [0, 8, 5, 4, 11]; distance: 31
        0 - 1 -> path: [0, 8, 5, 4, 11, 1]; distance 37
         */
        int distance = dijkstra(startNode, endNode);  // if -1 no path between startNode - endNode
        System.out.println(distance);
    }

    private static int dijkstra(int startNode, int endNode) {
        previous = new int[graph.size()];

        int[] bestDistanceToIndex = new int[graph.size()];
        for (int i = 0; i < bestDistanceToIndex.length; i++) {
            bestDistanceToIndex[i] = -1;
        }

        boolean[] visited = new boolean[graph.size()];

        TreeSet<Node> priorityQueue = new TreeSet<>();
        Map<Integer, Integer> startNodeConnections = graph.get(startNode);
        visited[startNode] = true;
        for (Map.Entry<Integer, Integer> connection : startNodeConnections.entrySet()) {
            int index = connection.getKey();
            int weight = connection.getValue();

            bestDistanceToIndex[index] = weight;

            Node node = new Node(index, weight);
            priorityQueue.add(node);
        }

        while (true) {
            if (priorityQueue.isEmpty()) {
                return -1;
            }

            Node node = priorityQueue.pollFirst();
            visited[node.index] = true;

            if (node.index == endNode) {
                break;
            }

            int index = node.index;
            Map<Integer, Integer> nodeConnections = graph.get(index);
            for (Map.Entry<Integer, Integer> connection : nodeConnections.entrySet()) {
                int connectionIndex = connection.getKey();
                if (visited[connectionIndex]) {
                    continue;
                }

                int connectionWeight = connection.getValue();

                if (bestDistanceToIndex[connectionIndex] == -1 ||
                        node.weight + connectionWeight < bestDistanceToIndex[connectionIndex]) {

                    Node currentNode = new Node(connectionIndex, bestDistanceToIndex[connectionIndex]);
                    if (priorityQueue.contains(currentNode)) {
                        priorityQueue.remove(currentNode);
                    }

                    bestDistanceToIndex[connectionIndex] = node.weight + connectionWeight;

                    previous[connectionIndex] = node.index;

                    priorityQueue.add(new Node(connectionIndex, node.weight + connectionWeight));
                }
            }
        }

        int distance = bestDistanceToIndex[endNode];

        Deque<Integer> path = recoverPath(startNode, endNode);

        System.out.println(path);

        return distance;
    }

    private static Deque<Integer> recoverPath(int startNode, int endNode) {
        Deque<Integer> path = new LinkedList<>();
        path.addFirst(endNode);
        while (endNode != startNode) {
            path.addFirst(previous[endNode]);
            endNode = previous[endNode];
        }

        return path;
    }

    private static void buildGraph(int[][] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            int index = i;
            Map<Integer, Integer> weightByIndex = new HashMap<>();
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] != 0) {
                    int connectionIndex = j;
                    int weight = pattern[i][j];

                    weightByIndex.put(connectionIndex, weight);
                }
            }

            graph.put(index, weightByIndex);
        }
    }

    static class Node implements Comparable<Node> {
        private int index;
        private int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
