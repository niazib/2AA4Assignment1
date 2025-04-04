package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringHandlerTest {
    private StringHandler stringHandler;

    @BeforeEach
    void setUp() {
        stringHandler = new StringHandler();
    }

    @Test
    void testFactorizePath() {
        String expanded = "FFFRRFLLFLFFR";
        String factorized = "3F 2R F 2L F L 2F R";
        assertEquals(factorized, stringHandler.factorize_path(expanded), "Path should be factorized correctly");
    }

    @Test
    void testExpandPath() {
        String expanded = "FFFFRRFLLFLFFR";
        String factorized = "3F F 2R F 2L F L 2F R";
        assertEquals(expanded, stringHandler.expand_path(factorized), "Path should be expanded correctly");
    }
}
