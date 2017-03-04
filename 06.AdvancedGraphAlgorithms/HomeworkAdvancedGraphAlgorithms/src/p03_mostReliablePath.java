import java.util.*;

public class p03_mostReliablePath {

    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    private static final int INFINITY = Integer.MAX_VALUE / 2;

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

        int distanceToEnd = dijkstra(0, 7);
        System.out.println(distanceToEnd);
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

    private static int dijkstra(int start, int end) {
        int[] previous = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        visited[start] = true;

        int[] bestPathToIndex = new int[graph.size()];
        for (int i = 0; i < bestPathToIndex.length; i++) {
            bestPathToIndex[i] = INFINITY;
        }

        PriorityQueue priorityQueue = new PriorityQueue();

        Map<Integer, Integer> connectionsToStart = graph.get(start);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : connectionsToStart.entrySet()) {
            priorityQueue.add(new Node(integerIntegerEntry.getKey(), integerIntegerEntry.getValue()));

            previous[integerIntegerEntry.getKey()] = start;
            bestPathToIndex[integerIntegerEntry.getKey()] = integerIntegerEntry.getValue();
        }

        while (true) {
            if (priorityQueue.getSize() == 0) {
                return -1;
            }

            Node node = priorityQueue.extractMax();

            if (node.index == end) {
                break;
            }

            visited[node.index] = true;

            Map<Integer, Integer> connectionsToCurrentNode = graph.get(node.index);
            for (Map.Entry<Integer, Integer> integerIntegerEntry : connectionsToCurrentNode.entrySet()) {
                int nodeIndex = integerIntegerEntry.getKey();

                if (!visited[nodeIndex]) {
                    int distanceToNode = node.weight + integerIntegerEntry.getValue();

                    if (distanceToNode < bestPathToIndex[nodeIndex]) {
                        priorityQueue.add(new Node(nodeIndex, distanceToNode));

                        bestPathToIndex[nodeIndex] = distanceToNode;
                        previous[nodeIndex] = node.index;
                    }
                }
            }
        }

        int distance = bestPathToIndex[end];

        Deque<Integer> path = new LinkedList<>();
        path.addFirst(end);
        while (end != start) {
            path.addFirst(previous[end]);
            end = previous[end];
        }

        System.out.println(path);

        return distance;
    }

    private static class Node {
        private int index;
        private int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    private static class PriorityQueue {
        private Map<Integer, Integer> indexByNode;
        private List<Node> elements;
        private int size;

        public PriorityQueue() {
            this.indexByNode = new HashMap<>();
            this.elements = new ArrayList<>();
        }

        public int getSize() {
            return this.elements.size();
        }

        public void add(Node node) {
            if (indexByNode.containsKey(node.index)) {
                this.elements.get(indexByNode.get(node.index)).weight = node.weight;
                heapifyUp(node, indexByNode.get(node.index));
            } else {
                this.elements.add(node);
                this.indexByNode.put(node.index, this.elements.size() - 1);
                heapifyUp(node, this.elements.size() - 1);
            }
        }

        private void heapifyUp(Node node, int index) {
            int parentIndex = (index - 1) / 2;

            if(this.elements.get(parentIndex).weight > this.elements.get(index).weight) {
                Node parent = this.elements.get(parentIndex);

                this.indexByNode.put(node.index, parentIndex);
                this.indexByNode.put(parentIndex, index);

                this.elements.set(index, parent);
                this.elements.set(parentIndex, node);

                heapifyUp(node, parentIndex);
            }
        }

        public Node extractMax() {
            Node toReturn = this.elements.get(0);

            this.elements.set(0, this.elements.get(this.elements.size() - 1));
            this.indexByNode.put(this.elements.get(0).index, 0);

            this.elements.remove(this.elements.size() - 1);

            if (this.elements.size() > 0) {
                Node node = this.elements.get(0);
                heapifyDown(node, 0);
            }

            return toReturn;
        }

        private void heapifyDown(Node node, int elementIndex) {
            int leftChildIndex = elementIndex * 2 + 1;
            int rightChildIndex = elementIndex * 2 + 2;
            int largest = elementIndex;

            if (this.elements.size() > leftChildIndex && this.elements.get(leftChildIndex).weight < this.elements.get(largest).weight) {     // change the last operator to  "<"
                largest = leftChildIndex;
            }

            if (this.elements.size() > rightChildIndex && this.elements.get(rightChildIndex).weight < this.elements.get(largest).weight) {   // change the last operator to  "<"
                largest = rightChildIndex;
            }

            if (largest != elementIndex) {
                Node parent = this.elements.get(largest);

                this.elements.set(elementIndex, this.elements.get(largest));
                this.indexByNode.put(parent.index, elementIndex);

                this.elements.set(largest, node);
                this.indexByNode.put(node.index, largest);

                heapifyDown(node, largest);
            }
        }
    }
}
