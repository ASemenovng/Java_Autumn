import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import set.BaseSet;
import set.MyHashSet;

public class MyHashSetTest {

    @Test
    void justWorks() {
        BaseSet<Integer> hashSet = new MyHashSet<>();

        hashSet.add(1);
        Assertions.assertEquals(1, hashSet.size());

        hashSet.add(2);
        Assertions.assertEquals(2, hashSet.size());

        hashSet.add(1);
        Assertions.assertEquals(2, hashSet.size());


        Assertions.assertTrue(hashSet.contains(1));
        Assertions.assertTrue(hashSet.contains(2));
        Assertions.assertFalse(hashSet.contains(3));

        Assertions.assertFalse(hashSet.isEmpty());

    }

    @Test
    void removeAndEqualsWorks() {
        BaseSet<Integer> hashSet = new MyHashSet<>();

        hashSet.add(1);
        Assertions.assertEquals(1, hashSet.size());

        hashSet.add(2);
        Assertions.assertEquals(2, hashSet.size());

        Assertions.assertTrue(hashSet.contains(1));
        Assertions.assertTrue(hashSet.contains(2));


        hashSet.remove(1);
        Assertions.assertEquals(1, hashSet.size());

        BaseSet<Integer> otherSet = new MyHashSet<>();

        Assertions.assertNotEquals(hashSet, otherSet);
        otherSet.add(2);
        Assertions.assertEquals(hashSet, otherSet);

        hashSet.remove(2);
        Assertions.assertEquals(0, hashSet.size());
        Assertions.assertTrue(hashSet.isEmpty());

    }

    @Test
    void clearWorks() {
        BaseSet<Integer> hashSet = new MyHashSet<>();

        for (int i = 1; i <= 10; ++i) {
            hashSet.add(i);
            Assertions.assertEquals(i, hashSet.size());
        }

        hashSet.clear();
        Assertions.assertTrue(hashSet.isEmpty());
    }


    @Test
    void iteratorWorks() {
        BaseSet<Integer> hashSet = new MyHashSet<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            hashSet.add(i);
            numbers.add(i);
            Assertions.assertEquals(i, hashSet.size());
        }

        Iterator<Integer> listIterator = numbers.iterator();
        Iterator<Integer> hashSetIterator = hashSet.iterator();

        while (hashSetIterator.hasNext()) {
            Assertions.assertEquals(hashSetIterator.next(), listIterator.next());
        }

        hashSet.clear();
        Assertions.assertFalse(hashSet.iterator().hasNext());
    }

}
