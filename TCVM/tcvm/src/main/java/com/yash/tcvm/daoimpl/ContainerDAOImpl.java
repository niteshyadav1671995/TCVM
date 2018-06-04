package com.yash.tcvm.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.util.JSONUtil;

public class ContainerDAOImpl implements ContainerDAO {

	private List<Container> containers;
	private static ContainerDAOImpl containerDAOImpl = new ContainerDAOImpl();
	private JSONUtil jsonUtil = new JSONUtil();

	public ContainerDAOImpl() {
		containers = new ArrayList<Container>();
		containers.add(new Container(Ingredient.COFFEE, 2000, 2000));
		containers.add(new Container(Ingredient.MILK, 10000, 10000));
		containers.add(new Container(Ingredient.TEA, 2000, 2000));
		containers.add(new Container(Ingredient.WATER, 15000, 15000));
		containers.add(new Container(Ingredient.SUGAR, 8000, 8000));
		saveContainers();
	}

	private void saveContainers() {
		jsonUtil.writeObjectToJSON(containers);
	}

	public static ContainerDAOImpl getInstance() {
		return containerDAOImpl;
	}

	public Container getContainer(Ingredient ingredient) throws NullFieldException {
		if (ingredient == null) {
			throw new NullFieldException("Ingredient Field can not be null");
		}

		return null;
	}

	public Container updateContainer(Ingredient ingredient, Container container) throws NullFieldException {
		if (ingredient == null && container == null) {
			throw new NullFieldException("Ingredient And Container values cannot null");
		}
		Container updatedContainer = null;
		for (Container updateContainer : containers) {
			if (container.getIngredient() == ingredient) {
				updateContainer = container;
				updatedContainer = updateContainer;
				break;
			}
		}

		updateContainerToFile();
		return updatedContainer;

	}

	public void updateContainerToFile() {
		jsonUtil.writeObjectToJSON(containers);
	}

	public List<Container> getListOfContainers() {
		containers = jsonUtil.readObjectFromJson();
		return containers;
	}

}
