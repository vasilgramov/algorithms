import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class p03_dividingPresentsTest {
    @Test
    public void test01() {
        int[] presents = new int[] {3,2,3,2,2,77,89,23,90,11};
        int expectedSum = 166;

        int result = p03_dividingPresents.findSumWithSmallestDifference(presents);

        Assertions.assertEquals(expectedSum, result);
    }

    @Test
    public void test02() {
        int[] presents = new int[] {2,2,4,4,1,1};
        int expectedSum = 7;

        int result = p03_dividingPresents.findSumWithSmallestDifference(presents);

        Assertions.assertEquals(expectedSum, result);
    }

    @Test
    public void test03() {
        int[] presents = new int[] {7,17,45,91,11,32,102,33,6,3};
        int expectedSum = 173;

        int result = p03_dividingPresents.findSumWithSmallestDifference(presents);

        Assertions.assertEquals(expectedSum, result);
    }

    @Test
    public void test04() {
        int[] presents = new int[] {1,1,1,1,1,1,1,1,1,22};
        int expectedSum = 9;

        int result = p03_dividingPresents.findSumWithSmallestDifference(presents);

        Assertions.assertEquals(expectedSum, result);
    }

    @Test
    public void performanceTest() {
        int[] presents = new int[] {1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8,1,9,2,8};
        int expectedSum = 400;

        int result = p03_dividingPresents.findSumWithSmallestDifference(presents);

        Assertions.assertEquals(expectedSum, result);
    }
}