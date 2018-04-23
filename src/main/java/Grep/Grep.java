package Grep;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    public static void main(String[] args) {

        CmdArgs values = new CmdArgs(args);

        boolean r = values.regexp;
        boolean v = values.invert;
        boolean i = values.ignore;

        String word = values.word;

        File dir = new File(values.dir);

        if (!dir.exists()) throw new IllegalArgumentException("No such file or directory found");

        doMain(dir, word, r, v, i);

        System.out.println("Done");

    }

    private static void doMain(File dir, String word, boolean r, boolean v, boolean i) {

        try {
            FileInputStream stream = new FileInputStream(dir);
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);

            while (reader.readLine() != null) { // DOPILIT'
                String currentLine = reader.readLine();
                if (checker(currentLine, word, r, v, i)) {
                    System.out.println(currentLine);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checker(String cur, String w, boolean r, boolean v, boolean i) {
        String current = cur;
        String word = w;

        if (i) {
            current = cur.toLowerCase();
            word = w.toLowerCase();
        }

        boolean res = false;

        if (!r && current.contains(word)) {
            res = true;
        }

        if (r) {
            Pattern p = Pattern.compile(word);
            Matcher m = p.matcher(current);

            if (m.matches()) res = true;
        }

        if (v) res = !res;
        return res;
    }
}
