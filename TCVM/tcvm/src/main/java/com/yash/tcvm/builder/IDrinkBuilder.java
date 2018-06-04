 package com.yash.tcvm.builder;

import com.yash.tcvm.config.IDrinkConfigurer;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Order;

public interface IDrinkBuilder {

	void setDrinkConfigurer(IDrinkConfigurer drinkConfigurer);

	void prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException;

	void updateContainers(Order order) throws NullFieldException;
}
