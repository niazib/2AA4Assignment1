package ca.mcmaster.se2aa4.mazerunner.Factory;

import ca.mcmaster.se2aa4.mazerunner.LeftHandAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.MazeNavigator;
import ca.mcmaster.se2aa4.mazerunner.PathFindingAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.RightHandAlgorithm;

public class PathFindingFactory {

    public static PathFindingAlgorithm createAlgorithm(MazeNavigator solver, String algorithm) {
        if (algorithm.equalsIgnoreCase("LEFT_HAND")) {
            return new LeftHandAlgorithm(solver);
        }
        else {
            return new RightHandAlgorithm(solver);
        }
    }
}
