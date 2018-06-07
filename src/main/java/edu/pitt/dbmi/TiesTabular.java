package edu.pitt.dbmi;

import edu.pitt.dbmi.tiestabular.TiesData;
import edu.pitt.dbmi.tiestabular.TiesParser;
import edu.pitt.dbmi.tiestabular.TiesWriter;
import org.apache.commons.cli.*;

import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * Hello world!
 */
public class TiesTabular {

    public static void main(String[] args) throws Exception {

        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("f", true, "File with id and cuis");
        options.addOption("d", true, "File with cui names (optional)");
        options.addOption("m", true, "Minimum number of records with a cui as integer (optional)");
        options.addOption("p", true, "Minimum number of records with a cui as fraction (optional)");
        options.addOption("o", true, "Output file (optional");

        CommandLineParser parser = new DefaultParser();

        Reader in = null;
        Reader cuiNameIn = null;
        Integer minRecords = null;
        Float minFractionRecords = null;
        PrintStream out = System.out;

        boolean isUseCuiName = false;
        try {
            CommandLine cmd = parser.parse(options, args);


            if (cmd.hasOption("f")) {
                in = new FileReader(cmd.getOptionValue("f"));
            } else {
                // automatically generate the help statement
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "TiesTabular", options );
                System.exit(0);
            }
            if (cmd.hasOption("d")) {
                cuiNameIn = new FileReader(cmd.getOptionValue("d"));
                isUseCuiName = true;
            }
            if (cmd.hasOption("m")) {
                minRecords = Integer.parseInt(cmd.getOptionValue("m"));
            }
            if (cmd.hasOption("p")) {
                minFractionRecords = Float.parseFloat(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("o")) {
                out = new PrintStream(cmd.getOptionValue("o"));
            }
        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        }


        TiesData data = new TiesData();
        if (in != null) TiesParser.parseCuiStatus(data, in);
        if (isUseCuiName) TiesParser.parseCuiName(data, cuiNameIn);
        TiesWriter.write(data, out, minRecords, minFractionRecords, isUseCuiName);

    }
}
