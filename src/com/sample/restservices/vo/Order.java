package com.sample.restservices.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class Order {
	private int id;
	private BigDecimal amount;

	public Order() {
	}

	public Order(int id, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", amount=" + amount + '}';
	}
}