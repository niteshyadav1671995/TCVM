package com.yash.tcvm.model;

import java.util.Date;

import com.yash.tcvm.enums.Drink;

public class Order {

	private int quantity;

	private Drink drink;

	private Date orderDate = new Date();

	private Boolean status = false;

	private Double totalAmount;

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order(int quantity, Drink drink) {
		super();
		this.quantity = quantity;
		this.drink = drink;
	}

	public Order(int quantity, Drink drink, Boolean status) {
		super();
		this.quantity = quantity;
		this.drink = drink;
		this.status = status;
	}

	public Order() {
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [quantity=" + quantity + ", drink=" + drink + ", orderDate=" + orderDate + ", status=" + status
				+ ", totalAmount=" + totalAmount + "]";
	}

}
