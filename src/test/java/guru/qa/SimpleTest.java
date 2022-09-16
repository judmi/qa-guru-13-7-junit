package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @DisplayName("Checking three is greater than two")
    @Disabled("TICKET-1234")
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }
}
