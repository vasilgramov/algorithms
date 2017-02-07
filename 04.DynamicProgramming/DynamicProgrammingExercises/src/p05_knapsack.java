import java.util.ArrayList;

public class p05_knapsack {

    public static void main(String[] args) {
        int knapsackCapacity = 20;

        Item[] items = new Item[]{
                new Item(5, 30),
                new Item(8, 120),
                new Item(7, 10),
                new Item(0, 20),
                new Item(4, 50),
                new Item(5, 80),
                new Item(2, 10),
        };

        ArrayList<Item> itemsTaken = fillKnapsack(knapsackCapacity, items);

        System.out.println("Knapsack weight capacity: " + knapsackCapacity);
        System.out.println("Take the following items in the knapsack:");
        for (Item item : itemsTaken){
            System.out.println("weight: " + item.weight + ", " + "price: " + item.price);
        }

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        System.out.println("Total weight: " + totalWeight);
        System.out.println("Total price: " + totalPrice);
    }

    public static ArrayList<Item> fillKnapsack(int knapsackCapacity, Item[] items) {
        int[][] maxPrices = new int[items.length][knapsackCapacity + 1];
        boolean[][] isTaken = new boolean[items.length][knapsackCapacity + 1];

        for (int c = 0; c < knapsackCapacity + 1; c++) {
            if (items[0].weight <= c) {
                maxPrices[0][c] = items[0].price;
                isTaken[0][c] = true;
            }
        }

        for (int i = 1; i < items.length; i++) {
            for (int c = 0; c < knapsackCapacity + 1; c++) {
                Item currentItem = items[i];
                int price = currentItem.getPrice();
                int weight = currentItem.getWeight();

                maxPrices[i][c] = maxPrices[i - 1][c];

                if (weight <= c &&
                    maxPrices[i - 1][c - weight] + price > maxPrices[i - 1][c]) {
                    maxPrices[i][c] = maxPrices[i - 1][c - weight] + price;
                    isTaken[i][c] = true;
                }
            }
        }

        ArrayList<Item> itemsTaken = new ArrayList<>();
        int row = items.length - 1;
        int col = knapsackCapacity;
        while (row >= 0) {
            if (isTaken[row][col]) {
                itemsTaken.add(items[row]);
                col -= items[row].weight;
            }

            row--;
        }

        return itemsTaken;
    }

    public static class Item {
        private int weight;
        private int price;

        public Item(int weight, int price) {
            this.setWeight(weight);
            this.setPrice(price);
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}