import java.util.*;

public class p04_salaries {

    private static Map<Integer, List<Integer>> childrenByNode = new HashMap<>();

    private static Deque<Integer> toposorted = new LinkedList<>();
    private static boolean[] isVisited;

    private static Set<Integer> currentVisited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int parent = i;

            char[] currentConnections = in.nextLine().toCharArray();
            List<Integer> children = new ArrayList<>();
            for (int j = 0; j < currentConnections.length; j++) {
                int child = j;

                if (currentConnections[j] == 'Y') {
                    children.add(child);
                }
            }

            childrenByNode.put(parent, children);
        }

        for (Map.Entry<Integer, List<Integer>> integerListEntry : childrenByNode.entrySet()) {
            int node = integerListEntry.getKey();
            toposort(node);
        }

        long total = 0L;
        Map<Integer, Long> salaryByNode = new HashMap<>();
        for (Integer node : toposorted) {
            int employees = childrenByNode.get(node).size();

            if (employees == 0) {
                salaryByNode.put(node, 1L);
                total++;
            } else {
                long salary = 0L;
                for (Integer child : childrenByNode.get(node)) {
                    salary += salaryByNode.get(child);
                }

                total += salary;
                salaryByNode.put(node, salary);
            }
        }

        System.out.println(total);
    }

    private static void toposort(int node) throws Exception {
        if (currentVisited.contains(node)) {
            throw new Exception("Graph is cyclic!");
        }

        if (!isVisited[node]) {
            isVisited[node] = true;
            currentVisited.add(node);

            List<Integer> children = childrenByNode.get(node);
            for (Integer child : children) {
                toposort(child);
            }

            currentVisited.remove(node);
            toposorted.addLast(node);
        }
    }
}
