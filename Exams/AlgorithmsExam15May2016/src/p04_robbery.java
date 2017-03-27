import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_robbery {
    private static final int INFINITY = Integer.MAX_VALUE / 2 + 2017;

    private static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

    private static Map<Integer, Node> nodeByIndex = new HashMap<>();
    private static Map<Node, List<Node>> childrenByNode = new HashMap<>();

    private static Set<Node> visited = new HashSet<>();

    private static int[] bestPathToNde;
    private static Map<Edge, Integer> distanceBetweenNodes;

    private static int energy;
    private static int costForWaiting;

    private static int startNodeIndex;
    private static int endNodeIndex;

    private static int neededEnergy;

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        readNodes();

        energy = Integer.parseInt(in.readLine());
        costForWaiting = Integer.parseInt(in.readLine());

        startNodeIndex = Integer.parseInt(in.readLine());
        endNodeIndex = Integer.parseInt(in.readLine());

        readConnections();

        dijkstra();

        if (energy >= neededEnergy) {
            System.out.println(energy - neededEnergy);
        } else {
            System.out.println(String.format("Busted - need %d more energy", neededEnergy - energy));
        }
    }

    private static void dijkstra() throws Exception {
        bestPathToNde[startNodeIndex] = Integer.MIN_VALUE;

        Node startCell = nodeByIndex.get(startNodeIndex);
        priorityQueue.enqueue(startCell);

        while (priorityQueue.size() > 0) {
            Node node = priorityQueue.extractMin();

            if (visited.contains(node)) {
               throw new Exception();
            }

            visited.add(node);

            if (node.index == endNodeIndex) {
                neededEnergy = node.weight;
                break;
            }

            boolean shouldSwap = node.shouldSwap;

            List<Node> connections = childrenByNode.get(node);
            for (Node child : connections) {
                int distanceToCurrent = node.weight + distanceBetweenNodes.get(new Edge(node.index, child.index));

                if (!shouldSwap) {
                    if (child.color == 'w') {
                        child.shouldSwap = true;
                    } else {
                        child.shouldSwap = false;
                        distanceToCurrent += costForWaiting;
                    }
                } else {
                    if (child.color == 'w') {
                        distanceToCurrent += costForWaiting;
                        child.shouldSwap = true;
                    } else {
                        child.shouldSwap = false;
                    }
                }

                if (bestPathToNde[child.index] > distanceToCurrent) {
                    bestPathToNde[child.index] = distanceToCurrent;
                    child.weight = distanceToCurrent;

                    child.prev = node;

                    if (!priorityQueue.contains(child)) {
                        priorityQueue.enqueue(child);
                    } else {
                        priorityQueue.decreaseKey(child);
                    }
                }
            }
        }
    }

    private static void readConnections() throws IOException {
        distanceBetweenNodes = new HashMap<>();

        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            String[] connectionArgs = in.readLine().split("\\s+");
            int indexA = Integer.parseInt(connectionArgs[0]);
            int indexB = Integer.parseInt(connectionArgs[1]);
            int weight = Integer.parseInt(connectionArgs[2]);

            distanceBetweenNodes.put(new Edge(indexA, indexB), weight);

            Node parent = nodeByIndex.get(indexA);
            Node child = nodeByIndex.get(indexB);

            List<Node> children = childrenByNode.get(parent);
            children.add(child);
            childrenByNode.put(parent, children);
        }
    }

    private static void readNodes() throws IOException {
        String[] nodes = in.readLine().split("\\s+");

        bestPathToNde = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            bestPathToNde[i] = INFINITY;

            String nodeArgs = nodes[i];
            int index = Integer.parseInt(nodeArgs.substring(0, nodeArgs.length() - 1));
            char color = nodeArgs.charAt(nodeArgs.length() - 1);

            Node node = new Node(index);
            node.color = color;

            nodeByIndex.put(index, node);
            childrenByNode.put(node, new ArrayList<>());
        }
    }

    private static class Edge {
        private int fromNode;
        private int toNode;

        public Edge(int fromNode, int toNode) {
            this.fromNode = fromNode;
            this.toNode = toNode;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s [%s]", this.fromNode, this.toNode);
        }

        @Override
        public boolean equals(Object obj) {
            Edge edge = (Edge) obj;
            return this.fromNode == edge.fromNode && this.toNode == edge.toNode;
        }

        @Override
        public int hashCode() {
            return (this.fromNode + "" + this.toNode).hashCode();
        }
    }

    private static class Node implements Comparable<Node> {
        private int index;
        private char color;
        private int weight;
        private boolean shouldSwap;

        private Node prev;

        public Node(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            return this.index == ((Node) obj).index;
        }

        @Override
        public int hashCode() {
            return Integer.toString(this.index).hashCode();
        }

        @Override
        public String toString() {
            return Integer.toString(index);
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static class PriorityQueue<T extends Comparable<T>> {
        private Map<T, Integer> searchCollection;
        private List<T> heap;

        public PriorityQueue() {
            this.heap = new ArrayList<>();
            this.searchCollection = new HashMap<T, Integer>();
        }

        public int size() {
            return this.heap.size();
        }

        public T extractMin() {
            T min = this.heap.get(0);
            T last = this.heap.get(this.heap.size() - 1);
            this.searchCollection.put(last, 0);
            this.heap.set(0, last);
            this.heap.remove(this.heap.size() - 1);
            if (this.heap.size() > 0) {
                this.heapifyDown(0);
            }

            this.searchCollection.remove(min);

            return min;
        }

        public T peekMin() {
            return this.heap.get(0);
        }

        public boolean contains(T element) {
            return this.searchCollection.containsKey(element);
        }

        public void enqueue(T element) {
            this.searchCollection.put(element, this.heap.size());
            this.heap.add(element);
            this.heapifyUp(this.heap.size() - 1);
        }

        private void heapifyDown(int i) {
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            int smallest = i;

            if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != i) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, smallest);
                this.searchCollection.put(this.heap.get(smallest), i);
                this.heap.set(i, this.heap.get(smallest));
                this.heap.set(smallest, old);
                this.heapifyDown(smallest);
            }
        }

        private void heapifyUp(int i) {
            int parent = (i - 1) / 2;
            while (i > 0 && this.heap.get(i).compareTo(this.heap.get(parent)) < 0) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, parent);
                this.searchCollection.put(this.heap.get(parent), i);
                this.heap.set(i, this.heap.get(parent));
                this.heap.set(parent, old);

                i = parent;
                parent = (i - 1) / 2;
            }
        }

        public void decreaseKey(T element)
        {
            int index = this.searchCollection.get(element);
            this.heapifyUp(index);
        }
    }
}
