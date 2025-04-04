package ca.mcmaster.se2aa4.mazerunner;

public interface MazeNavigator {
    int get_row();
    public int get_column();
    public int[] get_initialPos();
    public String get_path();
    public String get_direction_facing();
    public void set_path(String newPath);

    void swap_solver_start_end();

    boolean check_exit();

    boolean move_right();

    boolean move_left();

    boolean move_forward();

    boolean move_backward();

    void turn_right();

    void turn_left();

    boolean check_path(String given_path);

}
