package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Maze maze;
        Solver solver;
        logger.info("** Starting Maze Runner");
        logger.info("**** Reading Command-Line Arguments");
        Options options = new Options();
        options.addOption("i", true, "Maze Path");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String maze_path = cmd.getOptionValue("i","");
            maze = new Maze(maze_path);
            solver = new Solver(maze);
            logger.info("**** Computing path");
            maze.print_maze();
            solver.explorer();
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        
        logger.info("** End of MazeRunner");
    }
}
