package com.yash.tcvm.serviceimpl;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.daoimpl.ContainerDAOImpl;
import com.yash.tcvm.enums.Ingredient;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImpl implements ContainerService {

	private List<Container> containers;

	private static ContainerServiceImpl containerServiceImpl = new ContainerServiceImpl();
	private ContainerDAO containerDAO;
	private Logger logger = Logger.getLogger("ContainerServiceImpl.class");

	public ContainerServiceImpl(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}

	public ContainerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public static ContainerServiceImpl getInstance() {
		return containerServiceImpl;
	}

	public Container getContainerByIngredient(Ingredient ingredient) throws NullFieldException {
		containerDAO = ContainerDAOImpl.getInstance();
		if (ingredient == null) {
			throw new NullFieldException("Ingredient Can not Be null");
		}
		Container selectedContainer = containerDAO.getContainer(ingredient);
		return selectedContainer;
	}

	public List<Container> getContainers() {
		logger.info("Getting all containers ");
		return containers;
	}

	public Container updateContainer(Ingredient ingredient, Container container) throws NullFieldException {
		if (ingredient == null && container == null) {
			throw new NullFieldException("Ingredient And Container values cannot null");
		}
		Container updatedContainer = containerDAO.updateContainer(ingredient, container);
		return updatedContainer;
	}

	public Integer refillContainers() throws NullFieldException{
		int rowsAffected = 0;
		rowsAffected = containerDAO.refillContainer();
		System.out.println("Container ReFilled Successfully");
		return rowsAffected;
	}

	public Integer containerStatus() throws FileNotFoundException {
		containers = containerDAO.getListOfContainers();
		int rowsAffected = 0;
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\tIngredient\t|\tMax Quatity\t|\tCurrent Availablity\t|");
		System.out.println("---------------------------------------------------------------------------------");

		for (Container container : containers) {
			System.out.println("|\t" + container.getIngredient() + "\t\t|\t" + container.getMaxCapacity() + "\t\t|\t"
					+ container.getCurrentAvailability() + "\t\t\t|");
		}
		System.out.println("---------------------------------------------------------------------------------");
		rowsAffected = containers.size();
	
		return rowsAffected;
	}

}
