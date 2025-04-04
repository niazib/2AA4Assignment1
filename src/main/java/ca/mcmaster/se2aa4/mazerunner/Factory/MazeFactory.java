package ca.mcmaster.se2aa4.mazerunner.Factory;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeInterface;

public class MazeFactory {

    public static MazeInterface createMaze(String maze_location) {
        return new Maze(maze_location);
    }
}
