import java.util.*;

public class p01_minSpannigTree {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfVertices = 9;
        List<Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new Edge(0, 3, 9));
        graphEdges.add(new Edge(0, 5, 4));
        graphEdges.add(new Edge(0, 8, 5));
        graphEdges.add(new Edge(1, 4, 8));
        graphEdges.add(new Edge(1, 7, 7));
        graphEdges.add(new Edge(2, 6, 12));
        graphEdges.add(new Edge(3, 5, 2));
        graphEdges.add(new Edge(3, 6, 8));
        graphEdges.add(new Edge(3, 8, 20));
        graphEdges.add(new Edge(4, 7, 10));
        graphEdges.add(new Edge(6, 8, 7));

//        int numberOfVertices = 9;
//        List<p01_minSpannigTree.Edge> graphEdges = new ArrayList<>();
//        graphEdges.add(new p01_minSpannigTree.Edge(0, 3, 9));
//        graphEdges.add(new p01_minSpannigTree.Edge(0, 8, 5));
//        graphEdges.add(new p01_minSpannigTree.Edge(1, 4, 8));
//        graphEdges.add(new p01_minSpannigTree.Edge(1, 7, 7));
//        graphEdges.add(new p01_minSpannigTree.Edge(2, 6, 12));
//        graphEdges.add(new p01_minSpannigTree.Edge(3, 5, 2));
//        graphEdges.add(new p01_minSpannigTree.Edge(3, 6, 8));
//        graphEdges.add(new p01_minSpannigTree.Edge(3, 8, 20));
//        graphEdges.add(new p01_minSpannigTree.Edge(4, 7, 10));
//        graphEdges.add(new p01_minSpannigTree.Edge(6, 8, 7));

        List<Edge> minSpannigTree = kruskalAlgorithm(numberOfVertices, graphEdges);
        int totalWeight = getTotalWeight(minSpannigTree);

        System.out.println("-----Total weight: " + totalWeight);
        for (Edge edge : minSpannigTree) {
            System.out.println(edge);
        }
    }

    public static int getTotalWeight(List<Edge> minSpannigTree) {
        int total = 0;
        for (Edge edge : minSpannigTree) {
            total += edge.getWeight();
        }

        return total;
    }

    public static List<Edge> kruskalAlgorithm(int numberOfVertices, List<Edge> graphEdges) {
        List<Edge> minSpanningTree = new ArrayList<>();

        Collections.sort(graphEdges);
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (Edge graphEdge : graphEdges) {
            int startNode = graphEdge.getStartNode();
            int endNode = graphEdge.getEndNode();

            int rootStartNode = findParent(startNode, parent);
            int rootEndNode = findParent(endNode, parent);

            if (rootStartNode != rootEndNode) {
                minSpanningTree.add(graphEdge);

                int realParent = rootStartNode;
                if (startNode != rootStartNode) {
                    realParent = rootStartNode;
                } else if (endNode != rootEndNode) {
                    realParent = rootEndNode;
                }

                parent[startNode] = realParent;
                parent[endNode] = realParent;
            }
        }

        return minSpanningTree;
    }

    private static int findParent(int node, int[] parent) {
        int root = node;

        while (root != parent[root]) {
            root = parent[root];
        }

        return root;
    }

    static class Edge implements Comparable<Edge> {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.setStartNode(startNode);
            this.setEndNode(endNode);
            this.setWeight(weight);
        }

        public int getStartNode() {
            return this.startNode;
        }

        private void setStartNode(int startNode) {
            this.startNode = startNode;
        }

        public int getEndNode() {
            return this.endNode;
        }

        private void setEndNode(int endNode) {
            this.endNode = endNode;
        }

        public int getWeight() {
            return this.weight;
        }

        private void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.getWeight(), other.getWeight());
        }

        @Override
        public String toString() {
            return String.format("(%d %d) -> %d", this.getStartNode(), this.getEndNode(), this.getWeight());
        }
    }
}
