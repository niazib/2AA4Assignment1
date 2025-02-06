package ca.mcmaster.se2aa4.mazerunner;

public interface MazeInterface {
    void print_maze();

    boolean check_space(int row, int column);

    boolean check_outofbounds(int row, int column);

    int[] getStarterPosition();
}