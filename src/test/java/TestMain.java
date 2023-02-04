import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {
    private static File file;
    private static FileHandler fileHandler;
    private static Scanner scanner;
    public static final String PATH = new File("").getAbsolutePath();


    @BeforeClass
    public static void setUp() {
        fileHandler = new FileHandler();
        file = new File(PATH + "/src/test/resources/test_prospects.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        fileHandler.start();
    }

    @Test
    public void testMain() {
        String expected = "Prospect 1:Juha wants to borrow 1000.0" + "\u20ac" +
                " for a period of 2 years and pay 43,87" + "\u20ac" + " each month";
        String actual = fileHandler.writeResult().get(0);
        Assert.assertEquals(expected, actual);
    }
}
