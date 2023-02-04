import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestFileHandler {
    private static File file;
    private static Scanner scanner;

    private static FileHandler fileHandler;
    @ClassRule
    public static TemporaryFolder myFolder = new TemporaryFolder();

    @BeforeClass
    public static void setUp() {
        File tempFolder;
        PrintWriter pw;
        fileHandler = new FileHandler();

        try {
            tempFolder = myFolder.newFolder("folder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file = new File(tempFolder, "prospects.txt");
        try {
            pw = new PrintWriter(file);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.print("Erkki Esimerkki,3000.0,5.0,3");
        pw.close();
        fileHandler.readDataFromFile(file);
    }

    @Test
    public void testReadDataFromFile() {
        String expected = "Erkki Esimerkki,3000.0,5.0,3";
        String actual = fileHandler.readDataFromFile(file).get(0).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWriteResult() {
        String expected = "Prospect 1:Erkki Esimerkki wants to borrow 3000.0" + "\u20ac" + " for a period of 3 years and pay " +
                "30,00" + "\u20ac" + " each month";
        Customer customer = fileHandler.readDataFromFile(file).get(0);
        customer.setFixedMonthlyPayment(30.00);
        String actual = fileHandler.writeResult().get(0);
        Assert.assertEquals(expected, actual);
    }
}
