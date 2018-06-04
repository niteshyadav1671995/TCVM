package com.yash.tcvm.builder;

import java.util.List;
import java.util.Map;

import com.yash.tcvm.config.AbstractDrinkConfigurer;
import com.yash.tcvm.config.IDrinkConfigurer;
import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;

public abstract class AbstractDrinkBuilder implements IDrinkBuilder {

	IDrinkConfigurer drinkConfigurer;
	ContainerService containerService = ContainerServiceImpl.getInstance();

	public void setDrinkConfigurer(IDrinkConfigurer drinkConfigurer) {
		this.drinkConfigurer = drinkConfigurer;
	}

	public void setContainerService(ContainerServiceImpl containerServiceImpl) {
		this.containerService = containerServiceImpl;
	}

	public void prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		checkUnderFlow(order);
		updateContainers(order);
		order.setStatus(true);
	}

	private void checkUnderFlow(Order order) throws NullFieldException {
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<Ingredient, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<Ingredient, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		for (Map.Entry<Ingredient, Double> entry : consumption.entrySet()) {
			double qtyWasted = wastage.get(entry.getKey());
			double qtyConsumed = entry.getValue();
			double qtyAvailableInContainer = containerService.getContainerByIngredient(entry.getKey()).getCurrentAvailability();
			int noOfCups = order.getQuantity();
			if (isUnderFlowCondition(qtyWasted, qtyConsumed, qtyAvailableInContainer, noOfCups)) {
				order.setStatus(false);
				throw new ContainerUnderflowException(entry.getKey() + " Insufficient");
			}
		}
	}

	private boolean isUnderFlowCondition(double qtyWasted, double qtyConsumed, double qtyAvailableInContainer,
			int noOfCups) {
	
		return (noOfCups * (qtyConsumed + qtyWasted)) > qtyAvailableInContainer;
	}

	public void updateContainers(Order order) throws NullFieldException {
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<Ingredient, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<Ingredient, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		List<Container> updatedContainer = containerService.getContainers();
		System.out.println("Making "+order.getDrink()+".....");
		for (Map.Entry<Ingredient, Double> entry : consumption.entrySet()) {
			Container container = containerService.getContainerByIngredient(entry.getKey());
			double qtyWasted = wastage.get(entry.getKey());
			double qtyConsumed = entry.getValue();
			double qtyAvailableInContainer = container.getCurrentAvailability();
			int noOfCups = order.getQuantity();
			container.setCurrentAvailability(qtyAvailableInContainer - (noOfCups * (qtyConsumed + qtyWasted)));
			order.setTotalAmount(abstractDrinkConfigurer.getDrinkRate()*noOfCups);
			containerService.updateContainer(entry.getKey(),container);
		}
		System.out.println("Tea successfully Prepared..");
	}

}
