import java.util.Scanner;

public class p04_shortestPathBetweenAllNodes {

    private static final int INFINITY = Integer.MAX_VALUE / 2 - 2017;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(in.nextLine().split("\\s+")[1]);
        int numberOfEdges = Integer.parseInt(in.nextLine().split("\\s+")[1]);

        int[][] graph = buildGraph(numberOfNodes);
        fillGraph(in, numberOfEdges, graph);

        getShortestPathsBetweenAllEdges(numberOfNodes, graph);

        print(graph);
    }

    private static void print(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static void getShortestPathsBetweenAllEdges(int numberOfNodes, int[][] graph) {
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    if (graph[k][i] + graph[j][k] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[j][k];
                    }
                }
            }
        }
    }

    private static int[][] buildGraph(int numberOfNodes) {
        int[][] graph = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (i != j) {
                    graph[i][j] = INFINITY;
                }
            }
        }
        return graph;
    }

    private static void fillGraph(Scanner in, int numberOfEdges, int[][] graph) {
        for (int i = 0; i < numberOfEdges; i++) {
            String[] edgeArgs = in.nextLine().split("\\s+");
            int startNode = Integer.parseInt(edgeArgs[0]);
            int endNode = Integer.parseInt(edgeArgs[1]);
            int weight = Integer.parseInt(edgeArgs[2]);

            graph[startNode][endNode] = weight;
            graph[endNode][startNode] = weight;
        }
    }
}
