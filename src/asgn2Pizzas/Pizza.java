package asgn2Pizzas;

import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant.
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza
 * and MeatLoversPizza. Each of these subclasses have a different set of
 * toppings. A description of the class's fields and their constraints is
 * provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Person A
 *
 */
public abstract class Pizza {
    private static final double MARGHERITA_PRICE = 8, VEGETARIAN_PRICE = 10, MEAT_LOVERS_PRICE = 12;
    private int quantity;
    private double price, costs;
    private LocalTime orderTime, deliveryTime;
    private String type;
    protected ArrayList<PizzaTopping> listOfToppings;

    /**
     * This class represents a pizza produced at the Pizza Palace restaurant. A
     * detailed description of the class's fields and parameters is provided in
     * the Assignment Specification, in particular in Section 5.1. A
     * PizzaException is thrown if the any of the constraints listed in Section
     * 5.1 of the Assignment Specification are violated.
     *
     * PRE: TRUE POST: All field values except cost per pizza are set
     * 
     * @param quantity
     *            - The number of pizzas ordered
     * @param orderTime
     *            - The time that the pizza order was made and sent to the
     *            kitchen
     * @param deliveryTime
     *            - The time that the pizza was delivered to the customer
     * @param type
     *            - A human understandable description of this Pizza type
     * @param price
     *            - The price that the pizza is sold to the customer
     * @throws PizzaException
     *             if supplied parameters are invalid
     * 
     */
    public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price)
            throws PizzaException {
        if (quantity <= 0)
            throw new PizzaException("Cannot order less than one pizza");
        if (quantity > 10)
            throw new PizzaException("Cannot order over the maximum limit (10 pizzas)");
        if (orderTime.isBefore(LocalTime.of(19, 00)))
            throw new PizzaException("Cannot order before 7:00pm");
        if (orderTime.isAfter(LocalTime.of(23, 00)))
            throw new PizzaException("Cannot order after 11:00pm");
        if (deliveryTime.isBefore(LocalTime.of(19, 00)))
            throw new PizzaException("Cannot deliver order before 7:00pm");
        // “A pizza takes at least 10 minutes to cook and is thrown out after 1
        // hour (including delivery time)” to "A pizza takes at least 10 minutes
        // to cook and is thrown out after 1 hour (including delivery time) to
        // from the time that a pizza was ordered." (i.e. I added the final
        // phrase)
        // ^^^^ TO DO: ^^^^

        this.quantity = quantity;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.type = type;
        this.price = price;
        listOfToppings = new ArrayList<PizzaTopping>();
    }

    /**
     * Calculates how much a pizza would could to make calculated from its
     * toppings.
     * 
     * <P>
     * PRE: TRUE
     * <P>
     * POST: The cost field is set to sum of the Pizzas's toppings
     */
    public final void calculateCostPerPizza() {
        for (PizzaTopping topping : listOfToppings) {
            costs += topping.getCost();
        }
    }

    /**
     * Returns the amount that an individual pizza costs to make.
     * 
     * @return The amount that an individual pizza costs to make.
     */
    public final double getCostPerPizza() {
        return costs;
    }

    /**
     * Returns the amount that an individual pizza is sold to the customer.
     * 
     * @return The amount that an individual pizza is sold to the customer.
     */
    public final double getPricePerPizza() {
        return price;
    }

    /**
     * Returns the amount that the entire order costs to make, taking into
     * account the type and quantity of pizzas.
     * 
     * @return The amount that the entire order costs to make, taking into
     *         account the type and quantity of pizzas.
     */
    public final double getOrderCost() {
        return (costs * quantity);
    }

    /**
     * Returns the amount that the entire order is sold to the customer, taking
     * into account the type and quantity of pizzas.
     * 
     * @return The amount that the entire order is sold to the customer, taking
     *         into account the type and quantity of pizzas.
     */
    public final double getOrderPrice() {
        return (price * quantity);
    }

    /**
     * Returns the profit made by the restaurant on the order which is the order
     * price minus the order cost.
     * 
     * @return Returns the profit made by the restaurant on the order which is
     *         the order price minus the order cost.
     */
    public final double getOrderProfit() {
        return (this.getOrderPrice() - this.getOrderCost());
    }

    /**
     * Indicates if the pizza contains the specified pizza topping or not.
     * 
     * @param topping
     *            - A topping as specified in the enumeration PizzaTopping
     * @return Returns true if the instance of Pizza contains the specified
     *         topping and false otherwise.
     */
    public final boolean containsTopping(PizzaTopping topping) {
        for (PizzaTopping toppingIndex : listOfToppings) {
            if (topping.equals(toppingIndex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the quantity of pizzas ordered.
     * 
     * @return the quantity of pizzas ordered.
     */
    public final int getQuantity() {
        return quantity;
    }

    /**
     * Returns a human understandable description of the Pizza's type. The valid
     * alternatives are listed in Section 5.1 of the Assignment Specification.
     * 
     * @return A human understandable description of the Pizza's type.
     */
    public final String getPizzaType() {
        return type;
    }

    /**
     * Compares *this* Pizza object with an instance of an *other* Pizza object
     * and returns true if if the two objects are equivalent, that is, if the
     * values exposed by public methods are equal. You do not need to test this
     * method.
     * 
     * @return true if *this* Pizza object and the *other* Pizza object have the
     *         same values returned for getCostPerPizza(), getOrderCost(),
     *         getOrderPrice(), getOrderProfit(), getPizzaType(),
     *         getPricePerPizza() and getQuantity().
     * 
     */
    @Override
    public boolean equals(Object other) {
        Pizza otherPizza = (Pizza) other;
        return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza())
                && (this.getOrderCost()) == (otherPizza.getOrderCost()))
                && (this.getOrderPrice()) == (otherPizza.getOrderPrice())
                && (this.getOrderProfit()) == (otherPizza.getOrderProfit())
                && (this.getPizzaType() == (otherPizza.getPizzaType())
                        && (this.getPricePerPizza()) == (otherPizza.getPricePerPizza())
                        && (this.getQuantity()) == (otherPizza.getQuantity()));
    }

}
