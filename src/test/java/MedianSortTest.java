import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedianSortTest {

    @Test
    void dumbTest() {
        Assertions.assertTrue(
                Collections.emptyList().containsAll(ListUtil.medianSort(Collections.emptyList()))
        );

        Assertions.assertTrue(
                List.of(1).containsAll(ListUtil.medianSort(List.of(1)))
        );
    }


    @Test
    void sortWorks() {
        List<Integer> sorted = ListUtil.medianSort(List.of(1, 3, 3, 6, 7, 8, 9));
        Assertions.assertEquals(
                List.of(6, 7, 8, 3, 3, 9, 1),
                sorted
        );

        sorted = ListUtil.medianSort(List.of(7, 10, 1, 3, 2, 5));

        Assertions.assertEquals(
                List.of(3, 5, 2, 1, 7, 10),
                sorted
        );
    }
}
