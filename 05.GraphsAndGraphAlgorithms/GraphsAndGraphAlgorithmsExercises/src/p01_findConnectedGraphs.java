import java.util.*;

public class p01_findConnectedGraphs {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        List<Set<Integer>> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String command = in.nextLine();
            Set<Integer> nodes = new LinkedHashSet<>();
            nodes.add(i);

            if (!"".equals(command)) {
                nodes.addAll(stringsToIntegers(command.split("\\s+")));

                boolean hasFound = false;

                outerLoop:
                for (Set<Integer> component : components) {
                    for (Integer node : nodes) {
                        if (component.contains(node)) {
                            hasFound = true;

                            component.addAll(nodes);
                            break outerLoop;
                        }
                    }
                }

                if (!hasFound) {
                    components.add(nodes);
                }
            } else {
                components.add(nodes);
            }
        }

        for (Set<Integer> component : components) {
            System.out.println("Connected component: " + component);
        }
    }

    private static Set<Integer> stringsToIntegers(String[] numbersAsString) {
        Set<Integer> parsedNumbers = new LinkedHashSet<>();

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers.add(Integer.parseInt(numbersAsString[i]));
        }


        return parsedNumbers;
    }
}
