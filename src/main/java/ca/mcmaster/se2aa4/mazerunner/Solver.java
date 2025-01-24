package ca.mcmaster.se2aa4.mazerunner;

public class Solver {
    private int r; // Up and Down
    private int c; // Left and Right
    private String direction_facing;
    private String path;
    private Maze maze;

    public Explorer(Maze input_maze) {
        maze = input_maze;
        int[] pos = maze.getStarterPosition();
        if (pos == null) {
            System.out.println("No starting position found in the maze. Please ensure there is a start.");
            System.exit(1);
        }
        direction_facing = "right";
        path = "";
        r = pos[0];
        c = pos[1];
    }

    private void move() {
        if (maze.check_space(r, c-1)) { // Left
            c = c-1;
            switch (direction_facing) {
                case "right":
                    path = path + "LLF";
                    break;
                case "left":
                    path = path + "F";
                    break;
                case "up":
                    path = path + "LF";
                    break;
                case "down":
                    path = path + "RF";
                    break;
            }
            direction_facing = "left";
        }
        else if (maze.check_space(r, c+1)) { // Right
            c = c+1;
            switch (direction_facing) {
                case "right":
                    path = path + "F";
                    break;
                case "left":
                    path = path + "RRF";
                    break;
                case "up":
                    path = path + "RF";
                    break;
                case "down":
                    path = path + "LF";
                    break;
            }
            direction_facing = "right";
        }
        else if (maze.check_space(r-1, c)) { // Down
            r = r-1;
            switch (direction_facing) {
                case "right":
                    path = path + "RF";
                    break;
                case "left":
                    path = path + "LF";
                    break;
                case "up":
                    path = path + "LLF";
                    break;
                case "down":
                    path = path + "F";
                    break;
            }
            direction_facing = "down";
        }
        else if (maze.check_space(r+1, c)) { // Up
            r = r+1;
            switch (direction_facing) {
                case "right":
                    path = path + "LF";
                    break;
                case "left":
                    path = path + "RF";
                    break;
                case "up":
                    path = path + "F";
                    break;
                case "down":
                    path = path + "LLF";
                    break;
            }
            direction_facing = "down";
        }
    }

    private void explore() {
        path = "";
        for (int i=0; i<10; i++) {
            move();
        }
    }
}
