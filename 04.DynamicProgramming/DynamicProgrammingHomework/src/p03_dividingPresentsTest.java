import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class p03_dividingPresentsTest {
    @Test
    public void test01() {
        int[] presents = new int[] {3,2,3,2,2,77,89,23,90,11};
        int expectedDifference = 30;

        int result = p03_dividingPresents.findSmallDifference(presents);

        Assertions.assertEquals(expectedDifference, result);
    }

    @Test
    public void test02() {
        int[] presents = new int[] {2,2,4,4,1,1};
        int expectedDifference = 0;

        int result = p03_dividingPresents.findSmallDifference(presents);

        Assertions.assertEquals(expectedDifference, result);
    }

    @Test
    public void test03() {
        int[] presents = new int[] {7,17,45,91,11,32,102,33,6,3};
        int expectedDifference = 1;

        int result = p03_dividingPresents.findSmallDifference(presents);

        Assertions.assertEquals(expectedDifference, result);
    }

    @Test
    public void test04() {
        int[] presents = new int[] {1,1,1,1,1,1,1,1,1,22};
        int expectedDifference = 13;

        int result = p03_dividingPresents.findSmallDifference(presents);

        Assertions.assertEquals(expectedDifference, result);
    }
}