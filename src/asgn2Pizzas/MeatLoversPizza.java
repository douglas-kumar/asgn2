package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;

/**
 * 
 *  A class that represents a meat lovers pizza made at the Pizza Palace restaurant. 
 *  The meat lovers pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Person A - Calum
 *
 */
public class MeatLoversPizza extends Pizza {
	private static final String MEAT_LOVERS = "Meat Lovers";
	private static final double MEAT_LOVERS_PRICE = 12;

	/**
	 * 
	 *  This class represents a meat lovers pizza made at the  Pizza Palace restaurant. The meat lovers pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
 	 * <P> PRE: TRUE
 	 * <P> POST: All field values including the cost per pizza are set
     *
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 *
	 */
	public MeatLoversPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		super(quantity, orderTime, deliveryTime, MEAT_LOVERS,  MEAT_LOVERS_PRICE);
		listOfToppings.add(PizzaTopping.TOMATO);
		listOfToppings.add(PizzaTopping.CHEESE);
		listOfToppings.add(PizzaTopping.BACON);
		listOfToppings.add(PizzaTopping.PEPPERONI);
		listOfToppings.add(PizzaTopping.SALAMI);
		this.calculateCostPerPizza();
	}

}
