import java.util.*;

public class p02_modifiedKruskalAlgorithm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(in.nextLine().split("\\s+")[1]);
        int numberOfEdges = Integer.parseInt(in.nextLine().split("\\s+")[1]);

        PriorityQueue edges = new PriorityQueue();
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edgeArgs = in.nextLine().split("\\s+");
            int startNode = Integer.parseInt(edgeArgs[0]);
            int endNode = Integer.parseInt(edgeArgs[1]);
            int weight = Integer.parseInt(edgeArgs[2]);

            edges.add(new Edge(startNode, endNode, weight));
        }

        int[] parentToNode = new int[numberOfNodes];
        for (int i = 0; i < parentToNode.length; i++) {
            parentToNode[i] = i;
        }

        Map<Integer, Set<Integer>> nodesByParent = new HashMap<>();

        List<Edge> minSpanningTree = new LinkedList<>();
        while (edges.count > 0) {
            Edge edge = edges.extractMin();

            int startNode = edge.startNode;
            int endNode = edge.endNode;

            int startNodeParent = getParent(startNode, parentToNode);
            int endNodeParent = getParent(endNode, parentToNode);

            if (startNodeParent != endNodeParent) {
                minSpanningTree.add(edge);

                if (nodesByParent.containsKey(startNodeParent)) {
                    boolean hasFound = false;
                    Set<Integer> toBeMerged = new HashSet<>();
                    if (nodesByParent.containsKey(endNodeParent)) {
                        toBeMerged = nodesByParent.get(endNodeParent);
                        hasFound = true;
                    }

                    if (hasFound) {
                        for (Integer node : toBeMerged) {
                            parentToNode[node] = startNodeParent;
                        }
                        nodesByParent.remove(endNodeParent);
                    }

                    Set<Integer> nodes = nodesByParent.get(startNodeParent);
                    nodes.add(startNode);
                    nodes.add(endNode);
                    nodes.addAll(toBeMerged);

                    parentToNode[startNode] = startNodeParent;
                    parentToNode[endNode] = startNodeParent;

                    nodesByParent.put(startNodeParent, nodes);
                } else if (nodesByParent.containsKey(endNodeParent)) {
                    boolean hasFound = false;
                    Set<Integer> toBeMerged = new HashSet<>();
                    if (nodesByParent.containsKey(startNodeParent)) {
                        toBeMerged = nodesByParent.get(startNodeParent);
                        hasFound = true;
                    }

                    if (hasFound) {
                        for (Integer node : toBeMerged) {
                            parentToNode[node] = endNodeParent;
                        }
                        nodesByParent.remove(startNodeParent);
                    }

                    Set<Integer> nodes = nodesByParent.get(endNodeParent);
                    nodes.add(startNode);
                    nodes.add(endNode);
                    nodes.addAll(toBeMerged);

                    parentToNode[startNode] = endNodeParent;
                    parentToNode[endNode] = endNodeParent;

                    nodesByParent.put(endNodeParent, nodes);
                } else {
                    Set<Integer> nodes = new HashSet<>();
                    nodes.add(startNode);
                    nodes.add(endNode);

                    parentToNode[startNode] = startNodeParent;
                    parentToNode[endNode] = startNodeParent;

                    nodesByParent.put(startNodeParent, nodes);
                }
            }
        }

        for (Edge edge : minSpanningTree) {
            System.out.println(edge);
        }
    }

    private static int getParent(int node, int[] parentToNode) {
        int root = node;

        while (root != parentToNode[root]) {
            root = parentToNode[root];
        }

        return root;
    }

    private static class Edge {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("{%d, %d} -> %d", this.startNode, this.endNode, this.weight);
        }
    }

    private static class PriorityQueue {
        private List<Edge> elements;

        private int count;

        public PriorityQueue() {
            this.elements = new ArrayList<>();
        }

        public void add(Edge element) {
            heapiyUp(element);
        }

        public Edge extractMin() {
            Edge toReturn = this.elements.get(0);
            Edge lastElement = this.elements.get(this.count - 1);
            this.elements.set(0, lastElement);
            this.count--;

            heapfifyDown(0);

            return toReturn;
        }

        private void heapiyUp(Edge edge) {
            this.elements.add(this.count, edge);
            if (this.count > 0) {
                int index = this.count;
                while (index > 0) {
                    int parentIndex = (index - 1) / 2;
                    Edge parent = this.elements.get(parentIndex);

                    if (parent.weight < edge.weight)
                        break;

                    this.elements.set(parentIndex, edge);
                    this.elements.set(index, parent);

                    index = parentIndex;
                }
            }

            this.count++;
        }

        private boolean heapfifyDown(int index) {
            Edge parent = this.elements.get(index);

            int leftChildIndex = (index * 2) + 1;
            int rightChildIndex = (index * 2) + 2;

            boolean hasLeftChild = leftChildIndex < this.count;
            boolean hasRightChild = rightChildIndex < this.count;

            if (!hasLeftChild && !hasRightChild)
                return true;

            if (hasLeftChild && !hasRightChild) {
                Edge leftChild = this.elements.get(leftChildIndex);

                if (parent.weight > leftChild.weight) {
                    this.elements.set(index, leftChild);
                    this.elements.set(leftChildIndex, parent);
                }

                return true;
            }

            if (hasLeftChild && hasRightChild) {
                Edge leftChild = this.elements.get(leftChildIndex);
                Edge rightChild = this.elements.get(rightChildIndex);

                if (parent.weight < leftChild.weight && parent.weight < rightChild.weight)
                    return true;

                if (leftChild.weight <= rightChild.weight) {
                    if (leftChild.weight < parent.weight) {
                        this.elements.set(index, leftChild);
                        this.elements.set(leftChildIndex, parent);

                        if (heapfifyDown(leftChildIndex))
                            return true;
                    }
                }else {
                    if (rightChild.weight < parent.weight) {
                        this.elements.set(index, rightChild);
                        this.elements.set(rightChildIndex, parent);

                        if (heapfifyDown(rightChildIndex)) {
                            return true;
                        }
                    }
                }
            }

            return true;
        }
    }
}
