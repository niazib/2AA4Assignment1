package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solver implements MazeNavigator {
    private int r; // Up and Down
    private int c; // Left and Right
    private int[] initial_pos;
    private int[] all_positions;
    private String direction_facing;
    private String path;
    private MazeInterface maze;
    private StringHandler stringHandler;
    private static final Logger logger = LogManager.getLogger();

    public Solver (MazeInterface input_maze) {
        maze = input_maze;
        all_positions = maze.getStarterPosition();
        if (all_positions == null) {
            System.exit(1);
        }
        initial_pos = new int[]{all_positions[0], all_positions[1]};
        if (initial_pos[1] == 0) {
            direction_facing = "right";
        }
        else {
            direction_facing = "left";
        }
        r = initial_pos[0];
        c = initial_pos[1];
        stringHandler = new StringHandler();
    }

    @Override
    public int get_row() {
        return r; 
    }

    @Override
    public int get_column() {
        return c; 
    }

    @Override
    public int[] get_initialPos() {
        return initial_pos;
    }

    @Override
    public String get_direction_facing() {
        return direction_facing;
    }

    @Override
    public String get_path() {
        return path;
    }

    @Override
    public void set_path(String newPath) {
        path = newPath;
    }

    @Override
    public void swap_solver_start_end() {
        if (initial_pos[0] == all_positions[0]) {
            initial_pos[0] = all_positions[2];
            initial_pos[1] = all_positions[3];
        }
        else {
            initial_pos[0] = all_positions[0];
            initial_pos[1] = all_positions[1];
        }
        if (initial_pos[1] == 0) {
            direction_facing = "right";
        }
        else {
            direction_facing = "left";
        }
        r = initial_pos[0];
        c = initial_pos[1];
        
    }

    @Override
    public boolean check_exit() {
        if (!(r == initial_pos[0] && c == initial_pos[1])) {
            if (maze.check_outofbounds(r, c+1) || maze.check_outofbounds(r, c-1) || maze.check_outofbounds(r+1, c) ||  maze.check_outofbounds(r-1, c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move_right() {
        String path_append = "RF";
        switch (direction_facing) {
            case "right":
                if (maze.check_space(r+1, c)) { // Down
                    r = r+1;
                    path = path + path_append;
                    direction_facing = "down";
                    return true;
                }
                break;
            case "left":
                if (maze.check_space(r-1, c)) { // Up
                    r = r-1;
                    path = path + path_append;
                    direction_facing = "up";
                    return true;
                }
                break;
            case "up":
                if (maze.check_space(r, c+1)) { // Right
                    c = c+1;
                    path = path + path_append;
                    direction_facing = "right";
                    return true;
                }
                break;
            case "down":
                if (maze.check_space(r, c-1)) { // Left
                    c = c-1;
                    path = path + path_append;
                    direction_facing = "left";
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean move_left() {
        String path_append = "LF";
        switch (direction_facing) {
            case "right":
                if (maze.check_space(r-1, c)) { // Up
                    r = r-1;
                    path = path + path_append;
                    direction_facing = "up";
                    return true;
                }
                break;
            case "left":
                if (maze.check_space(r+1, c)) { // Down
                    r = r+1;
                    path = path + path_append;
                    direction_facing = "down";
                    return true;
                }
                break;
            case "up":
                if (maze.check_space(r, c-1)) { // Left
                    c = c-1;
                    path = path + path_append;
                    direction_facing = "left";
                    return true;
                }
                break;
            case "down":
                if (maze.check_space(r, c+1)) { // Right
                    c = c+1;
                    path = path + path_append;
                    direction_facing = "right";
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean move_forward() {
        String path_append = "F";
        switch (direction_facing) {
            case "right":
                if (maze.check_space(r, c+1)) { // Right
                    c = c+1;
                    path = path + path_append;
                    direction_facing = "right";
                    return true;
                }
                break;
            case "left":
                if (maze.check_space(r, c-1)) { // Left
                    c = c-1;
                    path = path + path_append;
                    direction_facing = "left";
                    return true;
                }
                break;
            case "up":
                if (maze.check_space(r-1, c)) { // Up
                    r = r-1;
                    path = path + path_append;
                    direction_facing = "up";
                    return true;
                }
                break;
            case "down":
                if (maze.check_space(r+1, c)) { // Down
                    r = r+1;
                    path = path + path_append;
                    direction_facing = "down";
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean move_backward() {
        String path_append = "RRF";
        switch (direction_facing) {
            case "right":
                if (maze.check_space(r, c-1)) { // Left
                    c = c-1;
                    path = path + path_append;
                    direction_facing = "left";
                    return true;
                }
                break;
            case "left":
                if (maze.check_space(r, c+1)) { // Right
                    c = c+1;
                    path = path + path_append;
                    direction_facing = "right";
                    return true;
                }
                break;
            case "up":
                if (maze.check_space(r+1, c)) { // Down
                    r = r+1;
                    path = path + path_append;
                    direction_facing = "down";
                    return true;
                }
                break;
            case "down":
                if (maze.check_space(r-1, c)) { // Up
                    r = r-1;
                    path = path + path_append;
                    direction_facing = "up";
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public void turn_right() {
        switch (direction_facing) {
            case "right":
                direction_facing = "down";
                break;
            case "left":
                direction_facing = "up";
                break;
            case "up":
                direction_facing = "right";
                break;
            case "down":
                direction_facing = "left";
                break;
        }
        path = path + "R";
    }

    @Override
    public void turn_left() {
        switch (direction_facing) {
            case "right":
                direction_facing = "up";
                break;
            case "left":
                direction_facing = "down";
                break;
            case "up":
                direction_facing = "left";
                break;
            case "down":
                direction_facing = "right";
                break;
        }
        path = path + "L";
    }

    @Override
    public boolean check_path(String given_path) {
        given_path = stringHandler.expand_path(given_path);
        for (int i = 0; i < given_path.length(); i++) {
            if (given_path.charAt(i) == 'F') {
                move_forward();
            }
            else if (given_path.charAt(i) == 'R') {
                turn_right();
            }
            else if (given_path.charAt(i) == 'L') {
                turn_left();
            }
            logger.info((r + ", " + c));
        }
        if (check_exit()) {
            return true;
        }
        else {
            return false;
        }
    }
}
