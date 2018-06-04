package com.yash.tcvm.builder;

import com.yash.tcvm.config.CoffeeConfiguration;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Order;

public class CoffeeBuilder extends AbstractDrinkBuilder {

	public CoffeeBuilder() {
		setDrinkConfigurer(CoffeeConfiguration.getDrinkConfigurer());
	}

	@Override
	public void prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (order.getDrink() == Drink.COFFEE) {
			super.prepareDrink(order);
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.COFFEE + " and found " + order.getDrink());
		}
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new CoffeeBuilder();
	}

}
