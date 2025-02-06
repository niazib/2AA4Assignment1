package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Maze {
    private char[][] maze;
    private int row_size;
    private int column_size;
    private static final Logger logger = LogManager.getLogger();

    public Maze(String maze_path) {
        maze = get_maze(maze_path);
    }

    private char[][] get_maze(String maze_path) {
        char[][] maze_grid;
        String maze_string = "";
        row_size = 0;
        column_size = 0;
        if (maze_path != "") {
            try {
                logger.info("**** Reading the maze from file " + maze_path);
                BufferedReader reader;
                reader = new BufferedReader(new FileReader(maze_path));
                String line;
                while ((line = reader.readLine()) != null) {
                    row_size = row_size+1;
                    if (line.length() > column_size) {
                        column_size = line.length();
                    }
                }
                reader.close();
                maze_grid = new char[row_size][column_size];
                reader = new BufferedReader(new FileReader(maze_path));
                int row = 0;
                while ((line = reader.readLine()) != null) {
                    for (int col = 0; col < line.length(); col++) {
                        maze_grid[row][col] = line.charAt(col);
                    }
                    if (line.length() < column_size) {
                        for (int i = 0; i < column_size - line.length(); i++) {
                            maze_grid[row][line.length()+i] = ' ';
                        } 
                    }
                    row = row+1;
                }
                reader.close();
                return maze_grid;
            } catch(Exception e) {
                logger.error("/!\\ An error has occured: Maze location not found /!\\");
            }
        }
        logger.info("**** Using default maze");
        row_size = 3;
        column_size = 3;
        return new char[][]{{'#','#','#'},
                {' ',' ',' '},
                {'#','#','#'}};
    }

    public void print_maze() {
        System.out.println("Maze:");
        for (int i = 0; i < row_size; i++) {
            for (int j = 0; j < column_size; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }


    public boolean check_space(int row, int column) {
        if (row>=row_size || row<0 || column>=column_size || column<0) {
            return false;
        }
        char symbol_at_position = maze[row][column];
        if (symbol_at_position == ' ') {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean check_outofbounds(int row, int column) {
        if (row>=row_size || row<0 || column>=column_size || column<0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int[] getStarterPosition() {
        int count = 0;
        int[] positions = new int[4];

        // Check left column
        for (int i = 0; i < row_size; i++) {
            if (check_space(i, 0)) {
                positions[count] = i;
                positions[count + 1] = 0;
                count = count + 2;
                break;
            }
        }
        // Check right column
        for (int i = 0; i < row_size; i++) {
            if (check_space(i, column_size - 1)) {
                positions[count] = i;
                positions[count + 1] = column_size - 1;
                count += 2;
                break;
            }
        }
        // No starting or ending position
        if (count < 4) {
            logger.error("/!\\ An error has occurred: Less than two entrances/exits in maze /!\\");
            return null;
        }
        return positions;
    }
}