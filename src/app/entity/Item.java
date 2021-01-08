package app.entity;

import java.math.BigDecimal;

public class Item {
	
	// final cost = product.price * amount (without discount)
	private BigDecimal cost;
	private BigDecimal shippingFee;
	private BigDecimal taxAmount;
	private Integer amount;
	private Product product;
	
	public Item(BigDecimal cost, BigDecimal shippingFee, BigDecimal taxAmount, 
			Integer amount, Product product) {
		super();
		this.cost = cost;
		this.shippingFee = shippingFee;
		this.taxAmount = taxAmount;
		this.amount = amount;
		this.product = product;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((shippingFee == null) ? 0 : shippingFee.hashCode());
		result = prime * result + ((taxAmount == null) ? 0 : taxAmount.hashCode());
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
		Item other = (Item) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (shippingFee == null) {
			if (other.shippingFee != null)
				return false;
		} else if (!shippingFee.equals(other.shippingFee))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [cost=" + cost + ", shippingFee=" + shippingFee + ", taxAmount=" + taxAmount + "]";
	}
	
}
