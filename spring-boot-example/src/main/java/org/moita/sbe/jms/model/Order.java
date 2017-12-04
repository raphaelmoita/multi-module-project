package org.moita.sbe.jms.model;

import java.io.Serializable;
import java.util.Objects;

import org.moita.sbe.model.Item;

public class Order implements Serializable {

	private static final long serialVersionUID = 8344829531472181L;

	private String orderId;

	private String productName;

	private int quantity;

	private OrderStatus status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productName, quantity, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order that = (Order) obj;
		return orderId == that.orderId 
			&& Objects.equals(productName, that.productName)
			&& Objects.equals(quantity, that.quantity)
			&& Objects.equals(status, that.status);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productName=" + productName + ", quantity=" + quantity + ", status="
				+ status + "]";
	}

}