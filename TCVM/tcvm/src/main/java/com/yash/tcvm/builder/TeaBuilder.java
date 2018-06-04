package com.yash.tcvm.builder;

import com.yash.tcvm.config.TeaConfiguration;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Order;

public class TeaBuilder extends AbstractDrinkBuilder {

	public TeaBuilder() {
		setDrinkConfigurer(TeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public void prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (order.getDrink() == Drink.TEA) {
			super.prepareDrink(order);
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.TEA + " and found " + order.getDrink());
		}
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new TeaBuilder();
	}

}
