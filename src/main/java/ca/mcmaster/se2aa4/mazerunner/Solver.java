package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solver {
    private int r; // Up and Down
    private int c; // Left and Right
    int[] initial_pos;
    int[] all_positions;
    private String direction_facing;
    private String path;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    public Solver(Maze input_maze) {
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
    }

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

    private boolean check_exit() {
        if (!(r == initial_pos[0] && c == initial_pos[1])) {
            if (maze.check_outofbounds(r, c+1) || maze.check_outofbounds(r, c-1) || maze.check_outofbounds(r+1, c) ||  maze.check_outofbounds(r-1, c)) {
                return true;
            }
        }
        return false;
    }

    private boolean move_right() {
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

    private boolean move_left() {
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

    private boolean move_forward() {
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

    private boolean move_backward() {
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

    private void turn_right() {
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

    private void turn_left() {
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

    private boolean right_hand_algorithm() {
        while(true) {
            if (!move_right()) {
                if (!move_forward()) {
                    if(!move_left()) {
                        move_backward();
                    } 
                } 
            } 
            logger.info((r + ", " + c));
            if (r == initial_pos[0] && c == initial_pos[1]) {
                return false;
            }
            if (check_exit()) {
                return true;
            }
        }
    }

    private String factorize_path(String path) {
        String result = "";
        int count = 1;

        for (int i = 0; i < path.length(); i++) {
            if (i+1<path.length() && path.charAt(i) == path.charAt(i+1)) {
                count = count+1;
            }
            else {
                if (count > 1) { // Repeating character
                    result = result + count + "" + path.charAt(i) + " ";
                }
                else { // Single character
                    result = result + path.charAt(i) + " ";
                }
                count = 1;
            }
        }
        return result.trim();
    }

    private String expand_path(String path) {
        String result = "";
        int i = 0;
        int int_length;
        String the_int;

        while (i < path.length()) {
            char c = path.charAt(i);

            if (Character.isDigit(c)) {
                i = i+1;
                the_int = "" + c;
                while (i < path.length()) {
                    if (Character.isDigit(path.charAt(i))) {
                        the_int = the_int + path.charAt(i);
                    }
                    else {
                        break;
                    }
                    i = i + 1;
                }
                int count = Integer.parseInt(the_int);
                if (i < path.length() && (path.charAt(i) == 'F' || path.charAt(i) == 'L' || path.charAt(i) == 'R')) {
                    for (int j = 0; j < count; j++) {
                        result = result + path.charAt(i);
                    }
                }
            }
            else if (c == 'F' || c == 'L' || c == 'R') {
                result = result + c;
            }
            i=i+1;
        }
        return result;
    }

    public boolean explorer() {
        path = "";
        if (right_hand_algorithm()) {
            logger.info(path);
            String factorized_path = factorize_path(path);
            System.out.println(factorized_path);
            return true;
        }
        else {
            System.out.println("No exit found in the maze.");
            return false;
        }
    }

    public boolean check_path(String given_path) {
        given_path = expand_path(given_path);
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
