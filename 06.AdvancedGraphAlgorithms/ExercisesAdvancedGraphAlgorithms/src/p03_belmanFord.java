import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class p03_belmanFord {

    private static final int MAGIC_NUMBER = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
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

        List<Edge> edges = new ArrayList<>();
        Set<Integer> vertices = new HashSet<>();

        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] != 0) {
                    int startNode = i;
                    int endNode = j;
                    int weight = pattern[i][j];

                    vertices.add(startNode);
                    vertices.add(endNode);

                    edges.add(new Edge(startNode, endNode, weight));
                }
             }
        }

        int[] bestDistanceToNode = new int[vertices.size()];
        for (int i = 0; i < bestDistanceToNode.length; i++) {
            bestDistanceToNode[i] = MAGIC_NUMBER;
        }

        /*
        0 - 9 -> path: [0, 8, 5, 4, 11, 1, 9]; distance: 42
        0 - 2 -> path: [0, 8, 2]; distance: 26
        0 - 10 -> path: no path; distance: -1;
        0 - 11 -> path: [0, 8, 5, 4, 11]; distance: 31
        0 - 1 -> path: [0, 8, 5, 4, 11, 1]; distance 37
        */

        int sNode = 0;
        int eNode = 1;
        bestDistanceToNode[sNode] = 0;

        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge edge : edges) {
                int startNode = edge.startNode;
                int endNode = edge.endNode;
                int weight = edge.weight;

                if (bestDistanceToNode[startNode] + weight < bestDistanceToNode[endNode]) {
                    bestDistanceToNode[endNode] = bestDistanceToNode[startNode] + weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (bestDistanceToNode[edge.startNode] + edge.weight < bestDistanceToNode[edge.endNode]) {
                throw new Exception("NEGATIVE CYCLE DETECTED");
            }
        }



        System.out.println(bestDistanceToNode[eNode]);
    }

    static class Edge {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}
