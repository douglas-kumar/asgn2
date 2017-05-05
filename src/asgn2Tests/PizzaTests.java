package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import asgn2Pizzas.*;
import asgn2Exceptions.*;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	private MargheritaPizza mp;
	private String expectedString;
	
	@Before
	public void setup() throws PizzaException {
		mp = new MargheritaPizza(2, LocalTime.of(20, 10, 00), LocalTime.of(20, 30, 00));
	}
	
	@Test
	public void xXX() {
		expectedString = "Margherita";
		assertEquals(expectedString, mp.getPizzaType());
	}
	
	
}
