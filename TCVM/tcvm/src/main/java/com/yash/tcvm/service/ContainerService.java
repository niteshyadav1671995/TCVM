package com.yash.tcvm.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;

public interface ContainerService {

	Container getContainerByIngredient(Ingredient ingredient) throws NullFieldException;

	List<Container> getContainers();

	Container updateContainer(Ingredient ingredient, Container container) throws NullFieldException;

	Integer refillContainers() throws NullFieldException;

	Integer containerStatus() throws FileNotFoundException;

}
