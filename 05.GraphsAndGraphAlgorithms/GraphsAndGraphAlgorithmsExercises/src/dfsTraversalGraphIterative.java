import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class dfsTraversalGraphIterative {
    static List<Integer>[] graph = new ArrayList[7];
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

//        List<Integer> list1 = Arrays.asList(3, 6);
//        List<Integer> list2 = Arrays.asList(2, 3, 4, 5, 6);
//        List<Integer> list3 = Arrays.asList(1, 4, 5);
//        List<Integer> list4 = Arrays.asList(0, 1, 5);
//        List<Integer> list5 = Arrays.asList(1, 2, 6);
//        List<Integer> list6 = Arrays.asList(1, 2, 3);
//        List<Integer> list7=  Arrays.asList(0, 1, 4);

        List<Integer> list1 = Arrays.asList(3, 4, 6);
        List<Integer> list2 = Arrays.asList(6);
        List<Integer> list3 = Arrays.asList(5);
        List<Integer> list4 = Arrays.asList(0, 4);
        List<Integer> list5 = Arrays.asList(3, 0);
        List<Integer> list6 = Arrays.asList(6, 2);
        List<Integer> list7=  Arrays.asList(0, 1, 5);

        graph[0] = new ArrayList<>(list1);
        graph[1] = new ArrayList<>(list2);
        graph[2] = new ArrayList<>(list3);
        graph[3] = new ArrayList<>(list4);
        graph[4] = new ArrayList<>(list5);
        graph[5] = new ArrayList<>(list6);
        graph[6] = new ArrayList<>(list7);

        visited = new boolean[graph.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                visited[node] = true;

                stack.addLast(node);
                while (!stack.isEmpty()) {
                    int lastNode = stack.peekLast();

                    while (true) {
                        boolean hasFound = false;

                        for (Integer child : graph[lastNode]) {
                            if (!visited[child]) {
                                visited[child] = true;
                                hasFound = true;

                                stack.addLast(child);
                            }
                        }

                        if (!hasFound) {
                            System.out.println(stack.removeLast());
                            break;
                        }

                        lastNode = stack.peekLast();
                    }
                }
            }

            while (!stack.isEmpty()) {
                System.out.println(stack.removeLast());
            }
        }
    }
}
