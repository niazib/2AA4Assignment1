package ca.mcmaster.se2aa4.mazerunner;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolverTest {
    private Solver solver;
    private Maze maze;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/small.maz.txt");
        solver = new Solver(maze);
    }

    @Test
    void testSwapStartEnd() {
        int[] startPositions = maze.getStarterPosition();
        int[] Positions = solver.get_initialPos();
        assertArrayEquals(new int[]{startPositions[0], startPositions[1]}, Positions, "Initial position should match start position before swapping");
        solver.swap_solver_start_end();
        Positions = solver.get_initialPos();
        assertArrayEquals(new int[]{startPositions[2], startPositions[3]}, Positions, "Initial position should match end position after swapping");
    }

    @Test
    void testPathGetting() {
        String path = solver.get_path();
        assertEquals(null, path, "Path should be null before setting");
    }

    @Test
    void testPathSettingAndGetting() {
        solver.set_path("testPath");
        String path = solver.get_path();
        assertEquals("testPath", path, "Path is not correct");
    }

    @Test
    void testTurns() {
        solver.turn_left();
        solver.turn_left();
        solver.turn_right();
        assertEquals("nullLLR", solver.get_path(), "Path should be 'LLR' after two lefts and a right");
        assertEquals("up", solver.get_direction_facing(), "Direction should be up after two lefts and a right");
    }

    @Test
    void testLeftRightMovement() {
        assertEquals(false, solver.move_right(), "You should not be able to move right itno a wall");
        assertEquals(false, solver.move_left(), "You should not be able to move left into a wall");
        solver.move_forward();
        
        assertEquals(true, solver.move_left(), "You should be able to move left");
        assertEquals("up", solver.get_direction_facing(), "Direction should be up after moving left facing right");

        assertEquals(true, solver.move_right(), "You should be able to move right");
        assertEquals("right", solver.get_direction_facing(), "Direction should be right after moving right facing up");

        assertEquals("nullFLFRF", solver.get_path(), "Path should be 'FLFRF' after a forward left and right");
    }

    @Test
    void testForwardBackwardMovement() {
        assertEquals(false, solver.move_backward(), "You should not be able to move backwards at the start");

        assertEquals(true, solver.move_forward(), "You should be able to move forward");
        assertEquals("right", solver.get_direction_facing(), "Direction should remain right after moving forward");

        assertEquals(false, solver.move_forward(), "You should not be able to move forward again into a wall");
        assertEquals("right", solver.get_direction_facing(), "Direction should be right");

        assertEquals(true, solver.move_backward(), "You should be able to move backwards now");
        assertEquals("left", solver.get_direction_facing(), "Direction should be left after moving backward");

        assertEquals("nullFRRF", solver.get_path(), "Path should be 'FRRF' after two forward and one backward");
    }


}

