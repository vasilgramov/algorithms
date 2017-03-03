import java.util.*;

public class p01_extendANetworkCable {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int budget = Integer.parseInt(in.nextLine().split(" ")[1]);
        int nodes = Integer.parseInt(in.nextLine().split(" ")[1]);
        int edges = Integer.parseInt(in.nextLine().split(" ")[1]);

        int[] parentToIndex = new int[nodes];
        for (int i = 0; i < parentToIndex.length; i++) {
            parentToIndex[i] = i;
        }

        Set<Integer> connectedNodes = new HashSet<>();
        Set<Integer> parents = new HashSet<>();
        SortedSet<Edge> nonConnectedEdges = new TreeSet<>();

        for (int i = 0; i < edges; i++) {
            String[] edgeTokens = in.nextLine().split(" ");
            int startNode = Integer.parseInt(edgeTokens[0]);
            int endNode = Integer.parseInt(edgeTokens[1]);
            int weight = Integer.parseInt(edgeTokens[2]);

            if (edgeTokens.length == 4) {
                connectedNodes.add(startNode);
                connectedNodes.add(endNode);

                int rootStartNode = findParent(startNode, parentToIndex);
                int rootEndNode = findParent(endNode, parentToIndex);

                if (rootStartNode != rootEndNode) {
                    int theRoot = rootStartNode;
                    if (endNode != rootEndNode) {
                        theRoot = rootEndNode;
                    }

                    parents.add(theRoot);

                    parentToIndex[startNode] = theRoot;
                    parentToIndex[endNode] = theRoot;
                }
            } else {
                nonConnectedEdges.add(new Edge(startNode, endNode, weight));
            }
        }

        List<Edge> usedEdges = new LinkedList<>();
        while (true) {
            Edge toBeRemoved = null;

            for (Edge nonConnectedEdge : nonConnectedEdges) {
                if ((connectedNodes.contains(nonConnectedEdge.startNode) || connectedNodes.contains(nonConnectedEdge.endNode))
                        && budget >= nonConnectedEdge.weight &&
                        parentToIndex[nonConnectedEdge.startNode] != parentToIndex[nonConnectedEdge.endNode]) {
                    budget -= nonConnectedEdge.weight;
                    toBeRemoved = nonConnectedEdge;
                    usedEdges.add(nonConnectedEdge);

                    int theParent = parentToIndex[nonConnectedEdge.startNode];
                    if (!parents.contains(theParent)) {
                        theParent = parentToIndex[nonConnectedEdge.endNode];
                    }

                    parentToIndex[nonConnectedEdge.startNode] = theParent;
                    parentToIndex[nonConnectedEdge.endNode] = theParent;

                    break;
                }
            }

            if (toBeRemoved == null) {
                break;
            }

            nonConnectedEdges.remove(toBeRemoved);
        }

        for (Edge usedEdge : usedEdges) {
            System.out.println(usedEdge);
        }
    }

    private static int findParent(int node, int[] parentToIndex) {
        int parent = parentToIndex[node];

        while (parent != parentToIndex[parent]) {
            parent = parentToIndex[parent];
        }

        return parent;
    }

    private static class Edge implements Comparable<Edge> {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }

        @Override
        public String toString() {
            return String.format("{%d, %d} -> %d", this.startNode, this.endNode, this.weight);
        }
    }
}
