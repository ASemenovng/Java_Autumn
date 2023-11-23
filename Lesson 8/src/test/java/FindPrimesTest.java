import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindPrimesTest {

    @Test
    public void justWorks() {
        Assertions.assertEquals(
                List.of(2, 3, 5, 7),
                Main.findPrimes(10)
        );

        Assertions.assertEquals(
                List.of(2, 3, 5, 7, 11),
                Main.findPrimes(11)
        );

        Assertions.assertEquals(
                Collections.emptyList(),
                Main.findPrimes(1)
        );
    }
}
