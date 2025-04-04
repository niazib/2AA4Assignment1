package ca.mcmaster.se2aa4.mazerunner.Factory;

import ca.mcmaster.se2aa4.mazerunner.MazeInterface;
import ca.mcmaster.se2aa4.mazerunner.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.Solver;

public class NavigatorFactory {

    public static MazeNavigator createNavigator(MazeInterface maze) {
        return new Solver(maze);
    }
}
