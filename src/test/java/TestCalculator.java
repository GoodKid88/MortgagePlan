import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCalculator {
    private static Customer customer;
    private static Calculator calculator;

    @BeforeClass
    public static void setUp() {
        customer = new Customer();
        calculator = new Calculator();
        customer.setName("Erkki Esimerkki");
        customer.setLoan(2000.0);
        customer.setInterest(2.5);
        customer.setYears(6);
    }

    @Test
    public void testCalculateMonthlyPayment() {
        double expected = 30.0;
        double actual = calculator.calculateMonthlyPayment(customer);
        Assert.assertEquals(expected, actual, 1);
    }

    @Test
    public void testPow(){
        int expected = 8;
        int actual = (int) calculator.pow(2,3);
        Assert.assertEquals(expected, actual);
    }
}
