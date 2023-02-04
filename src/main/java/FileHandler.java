import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private static Calculator calculator;
    private static List<Customer> customersList;
    private static final String PATH = new File("").getAbsolutePath();

    public void start() {
        File data = new File(PATH + "/src/main/resources/prospects.txt");
        readDataFromFile(data);
        setMonthlyPayment();
        writeResult();
    }

    public List<Customer> readDataFromFile(File file) {
        Scanner scanner;
        calculator = new Calculator();
        customersList = new ArrayList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            Customer customer = new Customer();
            String line = scanner.nextLine();
            String[] data = line.split(",");
            if (data.length > 4) {
                customer.setName(data[0] + "," + data[1]);
            } else {
                customer.setName(data[0]);
            }
            try {
                customer.setLoan(Double.parseDouble(data[data.length - 3]));
                customer.setInterest(Double.parseDouble(data[data.length - 2]));
                customer.setYears(Integer.parseInt(data[data.length - 1]));

                customersList.add(customer);
            } catch (Exception e) {
                //we don't have to do anything
            }
        }
        return customersList;
    }

    private void setMonthlyPayment() {
        for (Customer value : customersList) {
            value.setFixedMonthlyPayment(calculator.calculateMonthlyPayment(value));
        }
    }

    public List<String> writeResult() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < customersList.size(); i++) {
            list.add("Prospect " + (i + 1) + ":" + customersList.get(i).getName()
                    + " wants to borrow " + customersList.get(i).getLoan() + "\u20ac" + " for a period of "
                    + customersList.get(i).getYears() + " years and pay " + String.format("%.2f", customersList.get(i).getFixedMonthlyPayment()) +
                    "\u20ac" + " each month");
        }
        return list;
    }

    public void print() {
        for (String customer : writeResult()) {
            System.out.println(customer);
        }
    }
}
