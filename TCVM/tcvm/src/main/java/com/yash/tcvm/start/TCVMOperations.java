package com.yash.tcvm.start;

import java.util.Scanner;

import com.yash.tcvm.builder.BlackCoffeeBuilder;
import com.yash.tcvm.builder.BlackTeaBuilder;
import com.yash.tcvm.builder.CoffeeBuilder;
import com.yash.tcvm.builder.IDrinkBuilder;
import com.yash.tcvm.builder.TeaBuilder;
import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.dao.OrderDao;
import com.yash.tcvm.daoimpl.ContainerDAOImpl;
import com.yash.tcvm.daoimpl.OrderDaoImpl;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.NullFieldException;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;
import com.yash.tcvm.serviceimpl.OrderServiceImpl;
import com.yash.tcvm.util.TCVMMenu;

public class TCVMOperations {
	public void tcvmMenuOptions() {
		String filePath = "src/main/resources/Menu/TCVM.txt";
	
		Scanner inputScanner = new Scanner(System.in);
		ContainerDAO containerDAO = ContainerDAOImpl.getInstance();
		ContainerService containerService = new ContainerServiceImpl(containerDAO);
		OrderDao orderDAO = new OrderDaoImpl();
		OrderService orderService = new OrderServiceImpl(orderDAO);
		int choice;
		int noOfCups;
		String continueChoice;
		do {
			TCVMMenu.getTcvmMenu(filePath);
			choice = inputScanner.nextInt();
			try {
				switch (choice) {
				case 1:
					System.out.println("Enter no. of Cups");
					noOfCups = inputScanner.nextInt();
					orderService.addOrder(new Order(noOfCups, Drink.TEA));
					break;
				case 2:
					System.out.println("Enter no. of Cups");
					noOfCups = inputScanner.nextInt();
					orderService.addOrder(new Order(noOfCups, Drink.COFFEE));
					break;
				case 3:
					System.out.println("Enter no. of Cups");
					noOfCups = inputScanner.nextInt();
					orderService.addOrder(new Order(noOfCups, Drink.BLACK_TEA));
					break;
				case 4:
					System.out.println("Enter no. of Cups");
					noOfCups = inputScanner.nextInt();
					orderService.addOrder(new Order(noOfCups, Drink.BLACK_COFFEE));
					break;
				case 5:
					try {
						containerService.refillContainers();
					} catch (NullFieldException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					containerService.containerStatus();
					break;
				case 7:
					System.out.println("Showing Total Sales of TCVM");
					orderService.showTotalSales();		
					break;
				case 8:
					System.exit(0);
					System.out.println("Application Terminated successfully");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Enter Y/N to continue?");
			continueChoice = inputScanner.next();
		} while (continueChoice.equalsIgnoreCase("y"));
	}

}