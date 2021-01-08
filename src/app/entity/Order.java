package app.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Order {
	
	private String customerName;
	private String customerContact;
	private String shippingAddress;
	private BigDecimal grandTotal;
	private LocalDateTime dateOrder;
	
	private Set<Item> itens;

	public Order(String customerName, String customerContact, String shippingAddress, BigDecimal grandTotal,
			LocalDateTime dateOrder, Set<Item> itens) {
		super();
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.shippingAddress = shippingAddress;
		this.grandTotal = grandTotal;
		this.dateOrder = dateOrder;
		this.itens = itens;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public LocalDateTime getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(LocalDateTime dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerContact == null) ? 0 : customerContact.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerContact == null) {
			if (other.customerContact != null)
				return false;
		} else if (!customerContact.equals(other.customerContact))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [customerName=" + customerName + ", customerContact=" + customerContact + ", shippingAddress="
				+ shippingAddress + ", grandTotal=" + grandTotal + ", dateOrder=" + dateOrder + "]";
	}

}
