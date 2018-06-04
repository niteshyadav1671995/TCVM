package com.yash.tcvm.dao;

import java.util.List;

import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;

public interface ContainerDAO {

	public Container getContainer(Ingredient ingredient) throws NullFieldException;
	public Container updateContainer(Ingredient ingredient, Container container) throws NullFieldException;
	public List<Container> getListOfContainers();
	
	
}
