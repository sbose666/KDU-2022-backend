import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilitiesTest {

    @Test
    void add() {
        assertAll(() -> assertEquals(10, MathUtilities.add(5, 5)),
                () -> assertEquals(18, MathUtilities.add(10, 8)),
                () -> assertEquals(99, MathUtilities.add(66, 33))
        );
    }

    @Test
    void multiply() {
        assertAll(() -> assertEquals(25, MathUtilities.multiply(5, 5)),
                () -> assertEquals(80, MathUtilities.multiply(10, 8)),
                () -> assertEquals(2178, MathUtilities.multiply(66, 33))
        );
    }
}
