package asgn2Restaurant;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to
 * return Pizza and Customer object - either as an individual Pizza/Customer
 * object or as an ArrayList of Pizza/Customer objects.
 * 
 * @author Person A (Calum) and Person B (Douglas)
 *
 */
public class LogHandler {
    private static final int ITEMS_PER_LINE = 9;
    private static final int ORDER_TIME = 0;
    private static final int DELIVERY_TIME = 1;
    private static final int NAME = 2;
    private static final int MOBILE_NUMBER = 3;
    private static final int CUSTOMER_CODE = 4;
    private static final int LOCATION_X = 5;
    private static final int LOCATION_Y = 6;
    private static final int PIZZA_CODE = 7;
    private static final int QUANTITY = 8;

    /**
     * Returns an ArrayList of Customer objects from the information contained
     * in the log file ordered as they appear in the log file.
     * 
     * @param filename
     *            The file name of the log file
     * @return an ArrayList of Customer objects from the information contained
     *         in the log file ordered as they appear in the log file.
     * @throws CustomerException
     *             If the log file contains semantic errors leading that violate
     *             the customer constraints listed in Section 5.3 of the
     *             Assignment Specification or contain an invalid customer code
     *             (passed by another class).
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Customer> populateCustomerDataset(String filename)
            throws CustomerException, LogHandlerException {

        ArrayList<Customer> customers = new ArrayList<Customer>();

        // Read in the specified file
        Path file = FileSystems.getDefault().getPath("logs", filename);
        List<String> customerList = null;

        try {
            customerList = Files.readAllLines(file);
        } catch (IOException e) {
            throw new LogHandlerException("Could not read the file " + filename + ", check the file");
        }

        for (String customer : customerList) {
            Customer next;
            next = createCustomer(customer);
            customers.add(next);
        }

        return customers;
    }

    /**
     * Returns an ArrayList of Pizza objects from the information contained in
     * the log file ordered as they appear in the log file. .
     * 
     * @param filename
     *            The file name of the log file
     * @return an ArrayList of Pizza objects from the information contained in
     *         the log file ordered as they appear in the log file. .
     * @throws PizzaException
     *             If the log file contains semantic errors leading that violate
     *             the pizza constraints listed in Section 5.3 of the Assignment
     *             Specification or contain an invalid pizza code (passed by
     *             another class).
     * @throws LogHandlerException
     *             If there was a problem with the log file not related to the
     *             semantic errors above
     * 
     */
    public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException {

        ArrayList<Pizza> listOfPizzas = new ArrayList<Pizza>();

        Path file = FileSystems.getDefault().getPath("logs", filename);
        List<String> pizzaList = null;

        try {
            pizzaList = Files.readAllLines(file);
        } catch (IOException e) {
            throw new LogHandlerException("Cannot read: " + filename + " Please check it is in the right directory");
        }

        for (String pizza : pizzaList) {
            Pizza nextPizza;
            nextPizza = createPizza(pizza);
            listOfPizzas.add(nextPizza);
        }

        return listOfPizzas;

    }

    /**
     * Creates a Customer object by parsing the information contained in a
     * single line of the log file. The format of each line is outlined in
     * Section 5.3 of the Assignment Specification.
     * 
     * @param line
     *            - A line from the log file
     * @return- A Customer object containing the information from the line in
     *          the log file
     * @throws CustomerException
     *             - If the log file contains semantic errors leading that
     *             violate the customer constraints listed in Section 5.3 of the
     *             Assignment Specification or contain an invalid customer code
     *             (passed by another class).
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
        String[] data = line.split(",");
        String customerCode;
        String name;
        String mobileNumber;
        int locationX;
        int locationY;

        if (data.length != ITEMS_PER_LINE) {
            throw new LogHandlerException("The line does not contain the correct number of fields");
        }

        try {
            locationX = Integer.parseInt(data[LOCATION_X]);
            locationY = Integer.parseInt(data[LOCATION_Y]);
        } catch (NumberFormatException e) {
            throw new LogHandlerException(
                    "Customer X,Y location values could not be parsed as integer, check value in log file");
        }

        customerCode = data[CUSTOMER_CODE];
        name = data[NAME];
        mobileNumber = data[MOBILE_NUMBER];

        Customer customer;
        customer = CustomerFactory.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
        return customer;
    }

    /**
     * Creates a Pizza object by parsing the information contained in a single
     * line of the log file. The format of each line is outlined in Section 5.3
     * of the Assignment Specification.
     * 
     * @param line
     *            - A line from the log file
     * @return- A Pizza object containing the information from the line in the
     *          log file
     * @throws PizzaException
     *             If the log file contains semantic errors leading that violate
     *             the pizza constraints listed in Section 5.3 of the Assignment
     *             Specification or contain an invalid pizza code (passed by
     *             another class).
     * @throws LogHandlerException
     *             - If there was a problem parsing the line from the log file.
     */
    public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
        String[] data = line.split(",");
        String pizzaCode;
        int quantity;
        LocalTime orderTime, deliveryTime;
        Pizza pizza;

        if (data.length != ITEMS_PER_LINE)
            throw new LogHandlerException("log file does not contain correct amount of data or valid data");
        try {
            quantity = Integer.parseInt(data[QUANTITY]);
            orderTime = LocalTime.parse(data[ORDER_TIME], DateTimeFormatter.ISO_TIME);
            deliveryTime = LocalTime.parse(data[DELIVERY_TIME], DateTimeFormatter.ISO_TIME);
        } catch (NumberFormatException eN) {
            throw new LogHandlerException("Order quantity could not be parsed as integer, check value in log file");
        } catch (DateTimeParseException eD) {
            throw new LogHandlerException("Times could not be parsed as integer, check values in log file");
        }
        pizzaCode = data[PIZZA_CODE];

        pizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
        return pizza;
    }

}
