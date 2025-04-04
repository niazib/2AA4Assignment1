package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Factory.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.Factory.NavigatorFactory; 
import ca.mcmaster.se2aa4.mazerunner.Factory.PathFindingFactory; 

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        MazeInterface maze;
        MazeNavigator solver;
        PathFindingAlgorithm algorithm;
        FlagHandler flaghandler;
        logger.info("** Starting Maze Runner");

        try {
            flaghandler = new FlagHandler(args);
            String maze_location = flaghandler.get_i_flag();

            maze = MazeFactory.createMaze(maze_location);
            solver = NavigatorFactory.createNavigator(maze);
            logger.info("**** Computing path");

            String algorithmChosen = flaghandler.get_a_flag();
            algorithm = PathFindingFactory.createAlgorithm(solver, algorithmChosen);
            
            if (flaghandler.check_p_flag()) {
                String maze_path = flaghandler.get_p_flag();
                if (solver.check_path(maze_path)) {
                    System.out.println("correct path");
                }
                else {
                    solver.swap_solver_start_end();
                    if (solver.check_path(maze_path)) {
                        System.out.println("correct path");
                    }
                    else{
                        System.out.println("incorrect path");
                    }
                }
            } 
            else {
                algorithm.explorer();
            }

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
        logger.info("** End of MazeRunner");
    }
}
