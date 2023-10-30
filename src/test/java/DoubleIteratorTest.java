import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import iterator.DoubleIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleIteratorTest {


    @Test
    void justWorks() {
        DoubleIterator<Integer> doubleIterator = new DoubleIterator<>(List.of(1, 2).listIterator(),
                List.of(3, 4).iterator());
        for (int i = 1; i <= 4; ++i) {
            Assertions.assertEquals(i, doubleIterator.next());
        }
        Assertions.assertFalse(doubleIterator.hasNext());
    }

    @Test
    void worksWithCornerCases() {
        DoubleIterator<Integer> doubleIterator = new DoubleIterator<>(Collections.emptyIterator(),
                Collections.emptyListIterator());
        Assertions.assertFalse(doubleIterator.hasNext());

        Assertions.assertThrows(NoSuchElementException.class, doubleIterator::next);


        doubleIterator = new DoubleIterator<>(Collections.emptyIterator(), List.of(1, 2).listIterator());
        for (int i = 1; i <= 2; ++i) {
            Assertions.assertEquals(i, doubleIterator.next());
        }
        Assertions.assertFalse(doubleIterator.hasNext());


        doubleIterator = new DoubleIterator<>(List.of(1, 2).listIterator(), Collections.emptyIterator());
        for (int i = 1; i <= 2; ++i) {
            Assertions.assertEquals(i, doubleIterator.next());
        }
        Assertions.assertFalse(doubleIterator.hasNext());

    }
}
