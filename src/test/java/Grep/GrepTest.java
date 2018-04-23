package Grep;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.io.FileUtils;
import java.util.function.BooleanSupplier;

class GrepTest {
    @Test
    void test1() throws IOException{
        String[] command = "мама input1.txt.\\files".split(" ");
        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\FilesOutput\\output1.txt"));
        System.setOut(toFile);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        File file1 = new File(".\\FilesOutput\\output1.txt");
        File file2 = new File(".\\ExpectedOutput\\expect1");
        assertTrue(FileUtils.contentEquals(file1,file2));
    }

}