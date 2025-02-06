package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm implements PathFindingAlgorithm {
    private MazeNavigator solver;
    private int r; // Up and Down
    private int c; // Left and Right
    private int[] initial_pos;
    private String path;
    private StringHandler stringHandler;
    private static final Logger logger = LogManager.getLogger();

    public RightHandAlgorithm(MazeNavigator navigator) {
        stringHandler = new StringHandler();
        solver = navigator;
    }
    
    @Override
    public boolean explorer() {
        solver.set_path("");
        if (right_hand_algorithm()) {
            path = solver.get_path();
            logger.info(path);
            String factorized_path = stringHandler.factorize_path(path);
            System.out.println(factorized_path);
            return true;
        }
        else {
            System.out.println("No exit found in the maze.");
            return false;
        }
    }

    private boolean right_hand_algorithm() {
        while(true) {
            if (!solver.move_right()) {
                if (!solver.move_forward()) {
                    if(!solver.move_left()) {
                        solver.move_backward();
                    } 
                } 
            } 
            r = solver.get_row();
            c = solver.get_column();
            initial_pos = solver.get_initialPos();
            logger.info((r + ", " + c));
            if (r == initial_pos[0] && c == initial_pos[1]) {
                return false;
            }
            if (solver.check_exit()) {
                return true;
            }
        }
    }
}
