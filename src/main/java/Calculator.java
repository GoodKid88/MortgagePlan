public class Calculator {
    private final int PAYMENTS_PER_YEAR = 12;

    public double calculateMonthlyPayment(Customer customer) {
        double b = customer.getInterest() / 100 / 12;
        double U = customer.getLoan();
        int p = customer.getYears() * PAYMENTS_PER_YEAR;

        return (U * (b * pow((1 + b), p))) / (pow((1 + b), p) - 1);
    }

    /*exponentiation method because we can't use Math()*/
    public double pow(double value, int powValue) {
        if (powValue == 1) {
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }
}
