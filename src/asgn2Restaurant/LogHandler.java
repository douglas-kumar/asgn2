package asgn2Restaurant;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;

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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // TODO: Check file validity - throwing exceptions (see method comment)

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
        // TO DO
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

        // TODO: Check line validity - throwing exceptions (see method comment)

        Customer customer;
        customer = CustomerFactory.getCustomer(data[4], data[2], data[3], Integer.parseInt(data[5]),
                Integer.parseInt(data[6]));
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
        // TO DO
    }

}
