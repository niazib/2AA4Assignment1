package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            maze = new Maze(maze_location);
            solver = new Solver(maze);
            logger.info("**** Computing path");
            algorithm = new RightHandAlgorithm(solver);
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
