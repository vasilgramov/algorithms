import java.util.*;

public class p02 {

    static Map<Integer, Set<Integer>> childrenByNode = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();
    static Set<Integer> allVisited = new HashSet<>();


    static int current;

    static boolean isReasonable = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] strings = in.nextLine().split("\\s+");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

//        for (int i = 0; i < n; i++) {
//            childrenByNode.put(i + 1, new HashSet<>());
//        }

        for (int i = 0; i < m; i++) {
            String[] connection = in.nextLine().split("\\s+");
            int a = Integer.parseInt(connection[0]);
            int b = Integer.parseInt(connection[1]);

            update(a, b);
            update(b, a);
        }

        for (Map.Entry<Integer, Set<Integer>> node : childrenByNode.entrySet()) {
            current = node.getKey();
            if (!allVisited.contains(current)) {
                dfs(node.getKey());

                if (!isReasonable) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }

    private static void dfs(int node) {
        if (isReasonable && !allVisited.contains(node)) {
            if (visited.contains(node)) {
                return;
            }

            visited.add(node);
            allVisited.add(node);

            Set<Integer> children = childrenByNode.get(node);
            for (Integer child : children) {

                if (current == child || childrenByNode.get(child).contains(current)) {
                    dfs(child);

                    if (!isReasonable) {
                        return;
                    }
                } else {
                    isReasonable = false;
                }
            }

            visited.remove(node);
        }
    }

    private static void update(int a, int b) {
        if (!childrenByNode.containsKey(a)) {
            Set<Integer> children = new HashSet<>();
            children.add(b);
            childrenByNode.put(a, children);
        } else {
            Set<Integer> children = childrenByNode.get(a);
            children.add(b);
            childrenByNode.put(a, children);
        }
    }
}
