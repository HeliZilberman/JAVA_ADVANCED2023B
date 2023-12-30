package maman11Q1;

public class Product {
	private String name;
	private double price;
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public Product(String name) {
		this(name,0.0);
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public boolean equals(Product prod) {
		return (prod.name.equals(this.name) || prod.price == this.price);
	}
	public String toString() {
		return name + ": " + price;
	}
}
