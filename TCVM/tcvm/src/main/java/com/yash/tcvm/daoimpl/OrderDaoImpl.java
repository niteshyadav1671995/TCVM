package com.yash.tcvm.daoimpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.yash.tcvm.dao.OrderDao;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.util.JSONUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> getOrders() throws FileNotFoundException, EmptyException {
		List<Order> orderList = JSONUtil.readOrderJSONFromFile();
		return orderList;
	}

	@Override
	public int insertOrder(Order order) throws EmptyException, FileNotFoundException {
		int rowsAffected = 0;
		if (order == null) {
			throw new NullPointerException();
		}
		List<Order> orders = null;
		try {
			orders = getOrders();
		} catch (EmptyException e) {
			orders = null;
		}
		if (orders == null) {
			orders = new ArrayList<>();
			orders.add(order);
		} else {
			orders.add(order);
		}
		JSONUtil.writeJSONToFile(orders);
		rowsAffected = 1;
		return rowsAffected;
	}

	@Override
	public List<Order> getOrdersByDrink(Drink drink) throws FileNotFoundException {
		List<Order> selectedOrders = new ArrayList<>();
		List<Order> orders = null;
		try {
			orders = getOrders();
		} catch (EmptyException e) {
			orders = null;
		}
		if (orders != null || orders.size() > 0) {
			for (Order order : orders) {
				if (order.getDrink() == drink) {
					selectedOrders.add(order);
				}
			}
		}
		return selectedOrders;
	}

}
