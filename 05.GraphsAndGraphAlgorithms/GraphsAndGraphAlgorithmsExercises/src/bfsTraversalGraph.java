import java.util.*;

public class bfsTraversalGraph {
    static List<Integer>[] graph = new ArrayList[7];
    static boolean[] visited;

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(0, 4);
        List<Integer> list3 = Arrays.asList(0, 5);
        List<Integer> list4 = Arrays.asList(0, 6);
        List<Integer> list5 = Arrays.asList(1);
        List<Integer> list6 = Arrays.asList(2);
        List<Integer> list7 = Arrays.asList(3);

        graph[0] = new ArrayList<>(list1);
        graph[1] = new ArrayList<>(list2);
        graph[2] = new ArrayList<>(list3);
        graph[3] = new ArrayList<>(list4);
        graph[4] = new ArrayList<>(list5);
        graph[5] = new ArrayList<>(list6);
        graph[6] = new ArrayList<>(list7);

        visited = new boolean[graph.length];

        Deque<Integer> queue = new ArrayDeque<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                visited[node] = true;

                queue.addLast(node);

                while (!queue.isEmpty()) {
                    int firstNode = queue.removeFirst();

                    for (Integer child : graph[firstNode]) {
                        if (!visited[child]) {
                            visited[child] = true;

                            queue.addLast(child);
                        }
                    }

                    System.out.println(firstNode);
                }
            }
        }
    }
}