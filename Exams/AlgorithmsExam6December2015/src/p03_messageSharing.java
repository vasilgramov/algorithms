import java.util.*;

public class p03_messageSharing {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] people = in.nextLine().substring(8).split(", ");
        String[] connections = in.nextLine().substring(13).split(", ");
        String[] startingPoints = in.nextLine().substring(7).split(", ");

        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < connections.length; i++) {
            String[] connectionArgs = connections[i].split(" - ");
            String personA = connectionArgs[0];
            String personB = connectionArgs[1];

            putIfAbsent(graph, personA, personB);
            putIfAbsent(graph, personB, personA);
        }

        Map<String, Integer> stepsByName = new HashMap<>();
        Deque<String> queue = new ArrayDeque<>();

        for (String startingPoint : startingPoints) {
            stepsByName.put(startingPoint, 0);
            queue.add(startingPoint);
        }

        while (!queue.isEmpty()) {
            String currentPerson = queue.removeFirst();

            List<String> currentPersonConnections = graph.get(currentPerson);
            if (currentPersonConnections != null) { // if currentPerson has connections
                for (String person : currentPersonConnections) {
                    if (!stepsByName.containsKey(person)) {
                        stepsByName.put(person, stepsByName.get(currentPerson) + 1);
                        queue.add(person);
                    }
                }
            }
        }

        if (stepsByName.size() != people.length) {
            List<String> unreachedPeople = new ArrayList<>();
            for (String person : people) {
                if (!stepsByName.containsKey(person)) {
                    unreachedPeople.add(person);
                }
            }

            Collections.sort(unreachedPeople);
            System.out.println("Cannot reach: " + String.join(", ", unreachedPeople));
        } else {
            int maxStep = findMaxStep(stepsByName);

            List<String> lastSharedMessagePeople = new ArrayList<>();
            for (Map.Entry<String, Integer> stringIntegerEntry : stepsByName.entrySet()) {
                if (stringIntegerEntry.getValue() == maxStep) {
                    lastSharedMessagePeople.add(stringIntegerEntry.getKey());
                }
            }

            Collections.sort(lastSharedMessagePeople);
            System.out.println(String.format("All people reached in %d steps", maxStep));
            System.out.println(String.format("People at last step: %s", String.join(", ", lastSharedMessagePeople)));
        }
    }

    private static int findMaxStep(Map<String, Integer> stepsByName) {
        int max = Integer.MIN_VALUE;
        for (Integer stepSize : stepsByName.values()) {
            if (stepSize > max) {
                max = stepSize;
            }
        }

        return max;
    }

    private static void putIfAbsent(Map<String, List<String>> graph, String personA, String personB) {
        if (!graph.containsKey(personA)) {
            List<String> currentConnections = new ArrayList<>();
            currentConnections.add(personB);
            graph.put(personA, currentConnections);
        } else {
            List<String> currentConnections = graph.get(personA);
            currentConnections.add(personB);
            graph.put(personA, currentConnections);
        }
    }
}
