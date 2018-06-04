package com.yash.tcvm.daoimpl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;

public class ContainerDAOImplTest {

	private ContainerDAO containerDAO;

	@Before
	public void init() {
		containerDAO = ContainerDAOImpl.getInstance();
	}

	@Test(expected = NullFieldException.class)
	public void getContainer_IngredientGive_ShouldThrowNullFieldException() throws NullFieldException {
		Ingredient ingredient = null;
		containerDAO.getContainer(ingredient);
	}

	@Test(expected = NullFieldException.class)
	public void updateContainer_IngredientIsNullAndContainerIsNullGiven_ShouldReturnNullFieldException()
			throws NullFieldException {
		Ingredient ingredient = null;
		Container container = null;
		containerDAO.updateContainer(ingredient, container);
	}

	@Test
	public void updateContainer_IngredientAndContainerGiven_ShouldReturnNullFieldException() throws NullFieldException {
		Ingredient ingredient = Ingredient.COFFEE;
		Container container = new Container(ingredient, 2000.00, 1900.00);
		Container updatedContainer = containerDAO.updateContainer(ingredient, container);
		assertEquals(updatedContainer.getCurrentAvailability(), container.getCurrentAvailability(), 0.00);
	}

	@Test
	public void getListOfContainers_ShouldReturnListOfContainers() throws NullFieldException {
		List<Container> containers = containerDAO.getListOfContainers();
		int sizeOfContainers = containers.size();
		assertEquals(5, sizeOfContainers);
	}

}
