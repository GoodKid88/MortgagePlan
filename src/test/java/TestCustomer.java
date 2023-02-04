import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCustomer {
    private static Customer customer;

    @BeforeClass
    public static void setUp() {
        customer = new Customer();
        customer.setName("Erkki Esimerkki");
        customer.setLoan(2000.0);
        customer.setInterest(2.5);
        customer.setYears(6);
    }

    @Test
    public void testToString() {
        String expected = "Erkki Esimerkki,2000.0,2.5,6";
        Assert.assertEquals(expected, customer.toString());
    }
}
