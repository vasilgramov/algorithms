import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class p03_AStarSearchAlgorithmTest {
    @Test
    public void AStarZeroTest1() {
        char[][] map =
            {
                {'-', '-', '-', '-', '-'},
                {'-', '-', '*', '-', '-'},
                {'-', 'W', 'W', 'W', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', 'P', '-', '-'},
                {'-', '-', '-', '-', '-'}
            };

        p03_AStarSearchAlgorithm.AStar aStar = new p03_AStarSearchAlgorithm.AStar(map);
        int[] startCoordinates = { 4, 2 };
        int[] endCoordinates = { 1, 2 };
        List<int[]> cells = aStar.findShortestPath(startCoordinates, endCoordinates);

        Assertions.assertEquals(cells.get(0)[0], 1);
        Assertions.assertEquals(cells.get(0)[1], 2);
        Assertions.assertEquals(cells.get(1)[0], 1);
        Assertions.assertEquals(cells.get(1)[1], 3);
        Assertions.assertEquals(cells.get(2)[0], 2);
        Assertions.assertEquals(cells.get(2)[1], 4);
        Assertions.assertEquals(cells.get(3)[0], 3);
        Assertions.assertEquals(cells.get(3)[1], 3);
    }

    @Test
    public void AStarZeroTest2() {
        char[][] map =
            {
                    { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' },
                    { '-', '-', '-', 'W', '*', '-', '-', '-', '-', '-', '-' },
                    { '-', '-', '-', 'W', 'W', 'W', 'W', 'W', '-', '-', '-' },
                    { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' },
                    { '-', '-', '-', '-', '-', '-', '-', 'P', '-', '-', '-' },
                    { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' }
            };

        int[] startCoordinates = { 4, 7 };
        int[] endCoordinates = { 1, 4 };
        p03_AStarSearchAlgorithm.AStar aStar = new p03_AStarSearchAlgorithm.AStar(map);
        List<int[]> cells = aStar.findShortestPath(startCoordinates, endCoordinates);
        Assertions.assertEquals(cells.get(0)[0], 1);
        Assertions.assertEquals(cells.get(0)[1], 4);
        Assertions.assertEquals(cells.get(1)[0], 1);
        Assertions.assertEquals(cells.get(1)[1], 5);
        Assertions.assertEquals(cells.get(2)[0], 1);
        Assertions.assertEquals(cells.get(2)[1], 6);
        Assertions.assertEquals(cells.get(3)[0], 1);
        Assertions.assertEquals(cells.get(3)[1], 7);
        Assertions.assertEquals(cells.get(4)[0], 2);
        Assertions.assertEquals(cells.get(4)[1], 8);
        Assertions.assertEquals(cells.get(5)[0], 3);
        Assertions.assertEquals(cells.get(5)[1], 7);
    }

    @Test
    public void AStartZeroTest3() {
        char[][] map =
            {
                    { '-', '-', '-', '-', 'W', '-', '-', '-', 'W', '*', '-' },
                    { '-', 'W', '-', '-', 'W', '-', '-', '-', 'W', '-', '-' },
                    { 'P', '-', 'W', '-', 'W', '-', '-', '-', 'W', '-', '-' },
                    { '-', 'W', '-', '-', 'W', 'W', 'W', '-', 'W', 'W', '-' },
                    { '-', '-', '-', 'W', 'W', '-', '-', '-', '-', 'W', '-' },
                    { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' }
            };

        int[] startCoordinates = { 2, 0 };
        int[] endCoordinates = { 0, 9 };
        p03_AStarSearchAlgorithm.AStar aStar = new p03_AStarSearchAlgorithm.AStar(map);
        List<int[]> cells = aStar.findShortestPath(startCoordinates, endCoordinates);

        Assertions.assertEquals(cells.get(0)[0], 0);
        Assertions.assertEquals(cells.get(0)[1], 9);
        Assertions.assertEquals(cells.get(1)[0], 1);
        Assertions.assertEquals(cells.get(1)[1], 10);
        Assertions.assertEquals(cells.get(2)[0], 2);
        Assertions.assertEquals(cells.get(2)[1], 10);
        Assertions.assertEquals(cells.get(3)[0], 3);
        Assertions.assertEquals(cells.get(3)[1], 10);
        Assertions.assertEquals(cells.get(4)[0], 4);
        Assertions.assertEquals(cells.get(4)[1], 10);
        Assertions.assertEquals(cells.get(5)[0], 5);
        Assertions.assertEquals(cells.get(5)[1], 9);
        Assertions.assertEquals(cells.get(6)[0], 5);
        Assertions.assertEquals(cells.get(6)[1], 8);
        Assertions.assertEquals(cells.get(7)[0], 5);
        Assertions.assertEquals(cells.get(7)[1], 7);
        Assertions.assertEquals(cells.get(8)[0], 5);
        Assertions.assertEquals(cells.get(8)[1], 6);
        Assertions.assertEquals(cells.get(9)[0], 5);
        Assertions.assertEquals(cells.get(9)[1], 5);
        Assertions.assertEquals(cells.get(10)[0], 5);
        Assertions.assertEquals(cells.get(10)[1], 4);
        Assertions.assertEquals(cells.get(11)[0], 5);
        Assertions.assertEquals(cells.get(11)[1], 3);
        Assertions.assertEquals(cells.get(12)[0], 4);
        Assertions.assertEquals(cells.get(12)[1], 2);
        Assertions.assertEquals(cells.get(13)[0], 3);
        Assertions.assertEquals(cells.get(13)[1], 2);
        Assertions.assertEquals(cells.get(14)[0], 2);
        Assertions.assertEquals(cells.get(14)[1], 1);
    }

    @Test
    public void AStarZeroTest4() {
        char[][] map =
            {
                    { 'P', 'W', '-', 'W', '-', '-' },
                    { '-', '-', 'W', '-', 'W', '-' },
                    { '-', 'W', 'W', 'W', 'W', '-' },
                    { '-', 'W', 'W', 'W', '-', '*' },
                    { '-', '-', '-', '-', '-', '-' }

            };

        int[] startCoordinates = { 0, 0 };
        int[] endCoordinates = { 3, 5 };

        p03_AStarSearchAlgorithm.AStar aStar = new p03_AStarSearchAlgorithm.AStar(map);
        List<int[]> cells = aStar.findShortestPath(startCoordinates, endCoordinates);

        Assertions.assertEquals(cells.get(0)[0], 3);
        Assertions.assertEquals(cells.get(0)[1], 5);
        Assertions.assertEquals(cells.get(1)[0], 2);
        Assertions.assertEquals(cells.get(1)[1], 5);
        Assertions.assertEquals(cells.get(2)[0], 1);
        Assertions.assertEquals(cells.get(2)[1], 5);
        Assertions.assertEquals(cells.get(3)[0], 0);
        Assertions.assertEquals(cells.get(3)[1], 4);
        Assertions.assertEquals(cells.get(4)[0], 1);
        Assertions.assertEquals(cells.get(4)[1], 3);
        Assertions.assertEquals(cells.get(5)[0], 0);
        Assertions.assertEquals(cells.get(5)[1], 2);
        Assertions.assertEquals(cells.get(6)[0], 1);
        Assertions.assertEquals(cells.get(6)[1], 1);
    }
}