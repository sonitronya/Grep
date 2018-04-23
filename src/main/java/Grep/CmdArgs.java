package Grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CmdArgs {

    @Option(name = "-r", usage = "sets regexp")
    boolean regexp;

    @Option(name = "-v", usage = "inverts filter rule")
    boolean invert;

    @Option(name = "-i", usage = "ignores words' capitalisation")
    boolean ignore;

    @Argument
    String word;

    @Argument
    String dir;

    CmdArgs(String[] args) {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (word != null && regexp) {
                System.err.println("setting word and regexp is not allowed at the same time");
            }

            if (dir == null) {
                System.err.println("no directory is set");
            }

        } catch (CmdLineException e) {
            System.out.println("wrong input");
        }

        if (regexp) System.out.println("-r is set");
        if (invert) System.out.println("-v is set");
        if (ignore) System.out.println("-i is set");

    }
}
