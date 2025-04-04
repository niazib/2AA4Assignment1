package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeTest {
    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/straight.maz.txt");
    }

    @Test
    void testGetStarterPosition() {
        int[] startPositions = maze.getStarterPosition();
        assertNotNull(startPositions, "Starter positions array is empty");
        assertEquals(4, startPositions.length, "Starter positions array doesn't have 4 elements");
        assertArrayEquals(new int[]{2,0,2,4}, startPositions, "Starter positions are not correct");
    }

    @Test
    void testCheckSpace() {
        assertTrue(maze.check_space(2, 1), "Position (1,1) should be an empty space");
        assertFalse(maze.check_space(0, 0), "Position (0,0) should not be an empty space");
        assertFalse(maze.check_space(-1, 0), "Position (-1,0) should be false due to out of bounds");
        assertFalse(maze.check_space(10, 10), "Position (0,0) should be false due to out of bounds");
    }
}

