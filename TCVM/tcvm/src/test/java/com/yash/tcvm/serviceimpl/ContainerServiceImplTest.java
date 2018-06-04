package com.yash.tcvm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImplTest {
	ContainerService containerService;

	ContainerDAO containerDAO = mock(ContainerDAO.class);

	@Before
	public void init() {
		containerService = new ContainerServiceImpl(containerDAO);
	}

	@Test(expected = NullFieldException.class)
	public void getContainerByIngredient_IngredientIsNullGiven_ThrowNullFieldException() throws NullFieldException {
		Ingredient ingredient = null;
		containerService.getContainerByIngredient(ingredient);
	}

	@Test
	public void getContainerByIngredient_IngredientIsGiven_ShouldReturnContainerOfIngredient()
			throws NullFieldException {
		Ingredient ingredient = Ingredient.COFFEE;
		Container container = new Container(ingredient, 2000.00, 2000.00);
		when(containerDAO.getContainer(ingredient)).thenReturn(container);
		Container requiredContainer = containerService.getContainerByIngredient(ingredient);
		assertEquals(requiredContainer.getIngredient(), container.getIngredient());
	}

	@Test(expected = NullFieldException.class)
	public void updateContainer_IngredientIsNullAndContainerIsNullGiven_ThrowNullFieldException()
			throws NullFieldException {
		Ingredient ingredient = null;
		Container container = null;
		containerService.updateContainer(ingredient, container);
	}

	@Test
	public void updateContainer_IngredientAndContainerGiven_ShouldReturnUpdatedContainer() throws NullFieldException {
		Ingredient ingredient = Ingredient.COFFEE;
		Container container = new Container(ingredient, 2000.00, 1900.00);
		Container updateContainer = new Container(ingredient, 2000.00, 1900.00);
		when(containerDAO.updateContainer(ingredient, container)).thenReturn(updateContainer);
		Container returnedContainer = containerService.updateContainer(ingredient, container);
		assertEquals(returnedContainer.getCurrentAvailability(), container.getCurrentAvailability(), 0.00);
	}

	@Test
	public void refillContainers_ShouldReturnSizeOfContainersList() throws NullFieldException {
		when(containerDAO.refillContainer()).thenReturn(4);
		int rowsAffected = containerService.refillContainers();
		assertEquals(4, rowsAffected);
	}

	@Test
	public void containerStatus_ShouldReturnSizeOfContainersList() throws NullFieldException, FileNotFoundException {
		List<Container> containers = Arrays.asList(new Container(Ingredient.COFFEE, 2000, 2000),
				new Container(Ingredient.MILK, 10000, 10000), new Container(Ingredient.TEA, 2000, 2000),
				new Container(Ingredient.WATER, 15000, 15000), new Container(Ingredient.SUGAR, 8000, 8000));
		when(containerDAO.getListOfContainers()).thenReturn(containers);
		int rowsAffected = containerService.containerStatus();
		assertEquals(5, rowsAffected);
	}

}
