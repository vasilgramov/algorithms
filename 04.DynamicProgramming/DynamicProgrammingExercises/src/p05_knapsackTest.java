import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class p05_knapsackTest {
    @Test
    public void testKnapsack7ItemsCapacity19()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                        new p05_knapsack.Item(5, 30),
                        new p05_knapsack.Item(8, 120),
                        new p05_knapsack.Item(7, 10),
                        new p05_knapsack.Item(0, 20),
                        new p05_knapsack.Item(4, 50),
                        new p05_knapsack.Item(5, 80),
                        new p05_knapsack.Item(2, 10),
                };

        int knapsackCapacity = 19;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(19, totalWeight);
        assertEquals(280, totalPrice);
    }

    @Test
    public void testKnapsack7ItemsCapacity0()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
                new p05_knapsack.Item(8, 120),
                new p05_knapsack.Item(7, 10),
                new p05_knapsack.Item(0, 20),
                new p05_knapsack.Item(4, 50),
                new p05_knapsack.Item(5, 80),
                new p05_knapsack.Item(2, 10),
        };

        int knapsackCapacity = 0;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(0, totalWeight);
        assertEquals(20, totalPrice);
    }

    @Test
    public void testKnapsack7ItemsCapacity1()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
                new p05_knapsack.Item(8, 120),
                new p05_knapsack.Item(7, 10),
                new p05_knapsack.Item(0, 20),
                new p05_knapsack.Item(4, 50),
                new p05_knapsack.Item(5, 80),
                new p05_knapsack.Item(2, 10),
        };

        int knapsackCapacity = 1;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(0, totalWeight);
        assertEquals(20, totalPrice);
    }

    @Test
    public void testKnapsack7ItemsCapacity2()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
                new p05_knapsack.Item(8, 120),
                new p05_knapsack.Item(7, 10),
                new p05_knapsack.Item(0, 20),
                new p05_knapsack.Item(4, 50),
                new p05_knapsack.Item(5, 80),
                new p05_knapsack.Item(2, 10),
        };

        int knapsackCapacity = 2;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(2, totalWeight);
        assertEquals(30, totalPrice);
    }

    @Test
    public void testKnapsack7ItemsCapacity100()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
                new p05_knapsack.Item(8, 120),
                new p05_knapsack.Item(7, 10),
                new p05_knapsack.Item(0, 20),
                new p05_knapsack.Item(4, 50),
                new p05_knapsack.Item(5, 80),
                new p05_knapsack.Item(2, 10),
        };

        int knapsackCapacity = 100;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(31, totalWeight);
        assertEquals(320, totalPrice);
    }

    @Test
    public void testKnapsack1ItemEqualCapacity()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
        };

        int knapsackCapacity = 5;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(5, totalWeight);
        assertEquals(30, totalPrice);
    }

    @Test
    public void testKnapsack1ItemEnoughCapacity()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
        };

        int knapsackCapacity = 6;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(5, totalWeight);
        assertEquals(30, totalPrice);
    }

    @Test
    public void testKnapsack2ItemsInsufficientCapacity()
    {
        p05_knapsack.Item[] items = new p05_knapsack.Item[] {
                new p05_knapsack.Item(5, 30),
                new p05_knapsack.Item(10, 55),
        };

        int knapsackCapacity = 3;

        ArrayList<p05_knapsack.Item> itemsTaken = p05_knapsack.fillKnapsack(knapsackCapacity, items);

        int totalWeight = itemsTaken.stream().mapToInt(w -> w.getWeight()).sum();
        int totalPrice = itemsTaken.stream().mapToInt(w -> w.getPrice()).sum();

        assertEquals(0, totalWeight);
        assertEquals(0, totalPrice);
    }
}