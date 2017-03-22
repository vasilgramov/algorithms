import java.util.*;

public class p02_nonCrossingBridges {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<Integer, Integer> indexByBridge = new HashMap<>();
        int[] bridges = stringsToIntegers(in.nextLine().split("\\s+"));
        getBridgeIndexes(indexByBridge, bridges);

        int pivot = 0;
        List<Integer> connections = new ArrayList<>();
        getConnections(indexByBridge, bridges, pivot, connections);

        output(bridges, connections);
    }

    private static void output(int[] bridges, List<Integer> connections) {
        if (connections.size() == 0) {
            System.out.println("No bridges found");
        } else if (connections.size() / 2 == 1) {
            System.out.println("1 bridge found");
        } else {
            System.out.println(connections.size() / 2 + " bridges found");
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bridges.length; i++) {
            if (!connections.contains(i)) {
                result.append("X").append(" ");
            } else {
                result.append(bridges[i]).append(" ");
            }
        }

        System.out.println(result);
    }

    private static void getConnections(Map<Integer, Integer> indexByBridge, int[] bridges, int pivot, List<Integer> connections) {
        for (int bridgeIndex = 1; bridgeIndex < bridges.length; bridgeIndex++) {
            int bridgeValue = bridges[bridgeIndex];
            for (int v = bridgeIndex - 1; v >= pivot; v--) {
                int currentBridgeValue = bridges[v];
                if (bridgeValue == currentBridgeValue) {
                    int currentBridgeIndex = indexByBridge.get(currentBridgeValue);
                    if (currentBridgeIndex >= pivot) {
                        pivot = bridgeIndex;

                        connections.add(currentBridgeIndex);
                        connections.add(bridgeIndex);
                    }
                }
            }

            indexByBridge.put(bridgeValue, bridgeIndex);
        }
    }

    private static void getBridgeIndexes(Map<Integer, Integer> indexByBridge, int[] bridges) {
        for (int i = 0; i < bridges.length; i++) {
            int currentBridgeValue = bridges[i];
            if (!indexByBridge.containsKey(currentBridgeValue)) {
                indexByBridge.put(currentBridgeValue, i);
            }
        }
    }

    private static int[] stringsToIntegers(String[] numbersAsString) {
        int[] parsedNumbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }


        return parsedNumbers;
    }
}
