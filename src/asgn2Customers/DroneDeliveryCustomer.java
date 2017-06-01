package asgn2Customers;

import asgn2Exceptions.CustomerException;

/**
 * A class that represents a customer that has chosen to have their pizza
 * delivered by a drone. This class extends the abstract Customer class and
 * calculates the delivery distance as the Euclidean Distance between the
 * customer and the restaurant. A description of the class's fields and their
 * constraints is provided in Section 5.2 of the Assignment Specification.
 * 
 * @author Person B - Douglas
 *
 */
public class DroneDeliveryCustomer extends Customer {

    private static final double DECIMALS = 100.0;

    /**
     * This class represents a customer of the Pizza Palace restaurant that has
     * chosen to have their pizza delivered by a drone. A CustomerException is
     * thrown if the any of the constraints listed in Section 5.2 of the
     * Assignment Specification are violated.
     *
     * <P>
     * PRE: TRUE
     * <P>
     * POST: All field values are set
     *
     * @param name
     *            - The Customer's name
     * @param mobileNumber
     *            - The customer mobile number
     * @param locationX
     *            - The customer x location relative to the Pizza Palace
     *            Restaurant measured in units of 'blocks'
     * @param locationY
     *            The customer y location relative to the Pizza Palace
     *            Restaurant measured in units of 'blocks'
     * @throws CustomerException
     *             if supplied parameters are invalid
     * 
     */
    public DroneDeliveryCustomer(String name, String mobileNumber, int locationX, int locationY)
            throws CustomerException {
        super(name, mobileNumber, locationX, locationY, "Drone Delivery");
        // Person A edit - Calum
        if (locationX == 0 && locationY == 0)
            throw new CustomerException("Cannot deliver to the same location as the restaurant");
    }

    /**
     * Returns the Euclidean Distance between the instance of
     * DroneDeliveryCustomer and the restaurant. Overrides getDeliveryDistance()
     * in Customer.
     * 
     * @return The distance between the restaurant and the customer in Euclidean
     *         distance.
     */
    @Override
    public double getDeliveryDistance() {
        double euclideanDistance = Math
                .sqrt((this.getLocationX() * this.getLocationX()) + (this.getLocationY() * this.getLocationY()));
        return Math.round(euclideanDistance * DECIMALS) / DECIMALS;
    }

}
