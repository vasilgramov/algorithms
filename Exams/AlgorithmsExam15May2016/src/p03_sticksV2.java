import java.util.*;

public class p03_sticksV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        int[] parentCount = new int[n];

        Set<Integer> nonTaken = new HashSet<>();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            nonTaken.add(i);
        }

        int connections = Integer.parseInt(in.nextLine());
        for (int i = 0; i < connections; i++) {
            String[] sticks = in.nextLine().split(" ");
            int stickA = Integer.parseInt(sticks[0]);
            int stickB = Integer.parseInt(sticks[1]);

            graph[stickA].add(stickB);
            parentCount[stickB]++;
        }

        Set<Integer> removedSticks = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            int stickToRemove = -1;

            for (Integer integer : nonTaken) {
                if (parentCount[integer] == 0 && integer > stickToRemove) {
                    stickToRemove = integer;
                }
            }

            if (stickToRemove == -1) {
                break;
            }

            for (Integer integer : graph[stickToRemove]) {
                parentCount[integer]--;
            }

            removedSticks.add(stickToRemove);
            nonTaken.remove(stickToRemove);
        }

        if (removedSticks.size() < n) {
            System.out.println("Cannot lift all sticks");
        }

        StringBuilder builder = new StringBuilder();
        for (Integer removedStick : removedSticks) {
            builder.append(removedStick).append(" ");
        }

        System.out.println(builder);
    }
}
