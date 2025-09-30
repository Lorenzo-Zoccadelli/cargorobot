package main.java.model;

import java.util.Objects;

public class Product {
	private Integer productId;
	private String name;
	private Double weight;
	
	public Product() {}
	
	public Product(Integer productId, String name, Double weight) {
		super();
		this.productId = productId;
		this.name = name;
		this.weight = weight;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", weight=" + weight + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, productId, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name) && Objects.equals(productId, other.productId)
				&& Objects.equals(weight, other.weight);
	}
	
	
	
}
