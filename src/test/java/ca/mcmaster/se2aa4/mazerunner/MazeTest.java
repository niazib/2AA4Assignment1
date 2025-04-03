package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MazeTest {
    private Maze maze;

    @BeforeEach
    void setUp() {
        // Creating a sample maze file for testing
        String mazeContent = 
                "#####\n" +
                "\n" +
                "#####";
        File testMazeFile = new File("test_maze.txt");
        try (FileWriter writer = new FileWriter(testMazeFile)) {
            writer.write(mazeContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        maze = new Maze("test_maze.txt");
    }

    @Test
    void testGetStarterPosition() {
        int[] startPositions = maze.getStarterPosition();
        assertNotNull(startPositions, "Starter positions array is empty");
        assertEquals(4, startPositions.length, "Starter positions array doesn't have 4 elements");
        assertArrayEquals(new int[]{1,0,1,4}, startPositions, "Starter positions are not correct");
    }
}
