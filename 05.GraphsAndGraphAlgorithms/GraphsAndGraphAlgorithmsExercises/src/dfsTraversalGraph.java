import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dfsTraversalGraph {
    static List<Integer>[] graph = new ArrayList[7];
    static boolean[] visited;

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(3, 6);
        List<Integer> list2 = Arrays.asList(2, 3, 4, 5, 6);
        List<Integer> list3 = Arrays.asList(1, 4, 5);
        List<Integer> list4 = Arrays.asList(0, 1, 5);
        List<Integer> list5 = Arrays.asList(1, 2, 6);
        List<Integer> list6 = Arrays.asList(1, 2, 3);
        List<Integer> list7=  Arrays.asList(0, 1, 4);

//        List<Integer> list1 = Arrays.asList(3);
//        List<Integer> list2 = Arrays.asList(3);
//        List<Integer> list3 = Arrays.asList();
//        List<Integer> list4 = Arrays.asList(3);
//        List<Integer> list5 = Arrays.asList();
//        List<Integer> list6 = Arrays.asList(0);
//        List<Integer> list7=  Arrays.asList(0);

        graph[0] = new ArrayList<>(list1);
        graph[1] = new ArrayList<>(list2);
        graph[2] = new ArrayList<>(list3);
        graph[3] = new ArrayList<>(list4);
        graph[4] = new ArrayList<>(list5);
        graph[5] = new ArrayList<>(list6);
        graph[6] = new ArrayList<>(list7);

        visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            dfs(i);
        }
    }

    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            for (Integer child : graph[node]) {
                dfs(child);
            }

            System.out.println(node);
        }
    }
}
