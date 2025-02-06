package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlagHandler {
    private Options options;
    private CommandLineParser parser;
    private String[] args;
    private CommandLine cmd;

    private static final Logger logger = LogManager.getLogger();

    public FlagHandler(String[] args) {
        this.args = args;  
        options = new Options();
        parser = new DefaultParser();
        options.addOption("i", true, "Maze Location");
        options.addOption("p", true, "Verify Maze Path");
        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            logger.error("/!\\ An error has occured during initialization /!\\");
        }
    }

    public String get_i_flag() {
        logger.info("**** Reading Command-Line i Flag");
        try {
            String maze_location = cmd.getOptionValue("i","");
            return maze_location;

        } catch(Exception e) {
            logger.error("/!\\ An error has occured reading i flag /!\\");
            return "";
        }
    }

    public boolean check_p_flag() {
        logger.info("**** Checking Command-Line p Flag");
        try {
            if (cmd.hasOption("p")) {
                return true;
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured checking p flag /!\\");
        }
        return false;
    }

    public String get_p_flag() {
        logger.info("**** Reading Command-Line p Flag");
        try {
            String maze_path = cmd.getOptionValue("p");
            return maze_path;

        } catch(Exception e) {
            logger.error("/!\\ An error has occured reading p flag /!\\");
            return "";
        }
    }
}
