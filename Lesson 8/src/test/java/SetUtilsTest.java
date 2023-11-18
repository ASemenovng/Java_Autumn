import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import set.SetUtils;

public class SetUtilsTest {

    @Test
    void unionTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5), SetUtils.union(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(firstSet, SetUtils.union(firstSet, secondSet));

    }

    @Test
    void intersectionTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(firstSet, SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(firstSet, SetUtils.intersection(firstSet, secondSet));

    }

    @Test
    void differenceTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(firstSet, SetUtils.difference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.intersection(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 3);

        Assertions.assertEquals(Set.of(1), SetUtils.difference(firstSet, secondSet));
    }

    @Test
    void symmetricDifferenceTest() {
        var firstSet = Set.of(1, 2, 3);
        var secondSet = Set.of(4, 5);

        Assertions.assertEquals(Set.of(1, 2, 3, 4, 5), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 1);

        Assertions.assertEquals(Collections.emptySet(), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1);
        secondSet = Set.of(1, 2, 3);

        Assertions.assertEquals(Set.of(2, 3), SetUtils.symmetricDifference(firstSet, secondSet));

        firstSet = Set.of(1, 2);
        secondSet = Set.of(2, 3);

        Assertions.assertEquals(Set.of(1, 3), SetUtils.symmetricDifference(firstSet, secondSet));

    }
}
