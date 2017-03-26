import java.util.*;

public class p03_sticksV1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        Set<Integer> removedSticks = new LinkedHashSet<>();
        Map<Integer, Set<Integer>> sticksOnStick = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sticksOnStick.put(i, new HashSet<>());
        }

        int connections = Integer.parseInt(in.nextLine());
        for (int i = 0; i < connections; i++) {
            String[] sticks = in.nextLine().split("\\s+");
            int stickA = Integer.parseInt(sticks[0]);
            int stickB = Integer.parseInt(sticks[1]);

            update(sticksOnStick, stickA, stickB);
        }

        while (!sticksOnStick.isEmpty()) {
            TreeSet<Integer> toBeRemoved = new TreeSet<>();
            for (Map.Entry<Integer, Set<Integer>> integerSetEntry : sticksOnStick.entrySet()) {
                if (integerSetEntry.getValue().size() == 0) {
                    toBeRemoved.add(integerSetEntry.getKey());
                }
            }

            if (toBeRemoved.isEmpty()) {
                break;
            }

            while (!toBeRemoved.isEmpty()) {
                int last = toBeRemoved.last();
                sticksOnStick.remove(last);

                removedSticks.add(last);

                for (Map.Entry<Integer, Set<Integer>> integerSetEntry : sticksOnStick.entrySet()) {
                    integerSetEntry.getValue().remove(last);
                    if (integerSetEntry.getValue().size() == 0) {
                        toBeRemoved.add(integerSetEntry.getKey());
                    }
                }

                toBeRemoved.remove(last);
            }
        }

        if (removedSticks.size() != n) {
            System.out.println("Cannot lift all sticks");
        }

        StringBuilder result = new StringBuilder();
        for (Integer removedStick : removedSticks) {
            result.append(removedStick).append(" ");
        }

        System.out.println(result);
    }

    private static void update(Map<Integer, Set<Integer>> sticksOnStick, int stickA, int stickB) {
        Set<Integer> onSticks = sticksOnStick.get(stickB);
        onSticks.add(stickA);
        sticksOnStick.put(stickB, onSticks);
    }
}
