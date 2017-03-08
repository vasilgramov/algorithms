import java.util.*;

public class p01_fractionKnapsackProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int capacity = Integer.parseInt(in.nextLine().split("\\s+")[1]);
        int items = Integer.parseInt(in.nextLine().split("\\s+")[1]);

        TreeSet<Item> itemSet = new TreeSet<>();
        for (int i = 0; i < items; i++) {
            String[] itemArgs = in.nextLine().split(" -> ");
            int price = Integer.parseInt(itemArgs[0]);
            int weight = Integer.parseInt(itemArgs[1]);

            itemSet.add(new Item(price, weight));
        }

        Deque<Item> takenItems = new ArrayDeque<>();
        while (capacity > 0) {
            Item item = itemSet.pollLast();
            if (capacity > item.weight) {
                item.percent = 100;
                takenItems.addLast(item);

                capacity -= item.weight;
            } else {
                int weight = item.weight;
                double percentForOne = 100.0 / weight;
                item.percent = capacity * percentForOne;
                takenItems.add(item);

                capacity = 0;
            }
        }

        for (Item takenItem : takenItems) {
            System.out.println(takenItem);
        }
    }

    private static class Item implements Comparable<Item> {
        private int price;
        private int weight;
        private double percent;

        public Item(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Item other) {
            double item1 = (double)this.price / (double)this.weight;
            double item2 = (double)other.price / (double)other.weight;
            return Double.compare(item1, item2);
        }

        @Override
        public String toString() {
            return this.price + " -> " + this.weight + String.format(": %.2f", this.percent) + "%";
        }
    }
}
