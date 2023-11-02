import static hw_classes.ListUtil.rangeList;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeListTest {

    @Test
    void justWorks() {
        List<Integer> range = rangeList(0, 10);
        Assertions.assertEquals(10, range.size());

        range.forEach(
                number -> {
                    Assertions.assertEquals(number, range.indexOf(number));
                    Assertions.assertEquals(number, range.lastIndexOf(number));
                }
        );

        Assertions.assertEquals(-1, range.indexOf(-3));
        Assertions.assertEquals(-1, range.lastIndexOf(-3));

        Assertions.assertTrue(range.contains(9));
    }

    @Test
    void worksWithCornerCases() {
        List<Integer> range = rangeList(0, 0);
        Assertions.assertEquals(0, range.size());

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> range.get(0));
    }


}
