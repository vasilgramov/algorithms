import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class p02_setCoverProblemTest {
    @Test
    public void zeroTest() {
        int[] universe = {1, 3, 5, 7, 9, 11, 20, 30, 40};
        int[][] sets =
                {
                        {20},
                        {1, 5, 20, 30},
                        {3, 7, 20, 30, 40},
                        {9, 30},
                        {11, 20, 30, 40},
                        {3, 7, 40}
                };

        List<int[]> expectedResult = new ArrayList<>();
        expectedResult.add(sets[2]);
        expectedResult.add(sets[1]);
        expectedResult.add(sets[3]);
        expectedResult.add(sets[4]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedResult.toArray(), usedSets.toArray());
    }

    @Test
    public void noRedundantSets() {
        int[] universe = {1, 2, 3, 4, 5};
        int[][] sets = {
                {1},
                {2, 4},
                {5},
                {3}
        };

        List<int[]> expectedSets = new ArrayList<>();
        expectedSets.add(sets[1]);
        expectedSets.add(sets[0]);
        expectedSets.add(sets[2]);
        expectedSets.add(sets[3]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedSets.toArray(), usedSets.toArray());
    }

    @Test
    public void withOneSetContainingUniverse() {
        int[] universe = {1, 2, 3, 4, 5};
        int[][] sets = {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5},
                {5},
                {3}
        };

        List<int[]> expectedSets = new ArrayList<>();
        expectedSets.add(sets[0]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedSets.toArray(), usedSets.toArray());
    }

    @Test
    public void withTwoSetsContainingUniverse() {
        int[] universe = {1, 2, 3, 4, 5};
        int[][] sets = {
                {1, 3, 5},
                {5, 1},
                {3, 2},
                {2, 4}
        };

        List<int[]> expectedSets = new ArrayList<>();
        expectedSets.add(sets[0]);
        expectedSets.add(sets[3]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedSets.toArray(), usedSets.toArray());
    }

    @Test
    public void withAllSetsNeeded() {
        int[] universe = {1, 2, 3, 4, 5};
        int[][] sets = {
                {1, 3, 5},
                {1, 2},
                {3, 4}
        };

        List<int[]> expectedSets = new ArrayList<>();
        expectedSets.add(sets[0]);
        expectedSets.add(sets[1]);
        expectedSets.add(sets[2]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedSets.toArray(), usedSets.toArray());
    }

    @Test
    public void withSeveralRedundantSets() {
        int[] universe = {1, 2, 3, 4, 5, 6};
        int[][] sets = {
                {1, 2, 5},
                {2, 3, 5},
                {3, 4, 5},
                {4, 5},
                {1, 3, 4, 6}
        };

        List<int[]> expectedSets = new ArrayList<>();
        expectedSets.add(sets[4]);
        expectedSets.add(sets[0]);

        List<int[]> usedSets = p02_setCoverProblem.getTakenSets(universe, sets);

        Assertions.assertArrayEquals(expectedSets.toArray(), usedSets.toArray());
    }
}