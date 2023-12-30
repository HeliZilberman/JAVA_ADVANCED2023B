package maman11Q1;

// class saves one check line 
public class CheckLine {
	private Product prod; 
	private int quantity;
	private double price_amount;
	
	public CheckLine(Product prod,int quantity) {
		this.prod = prod;
		this.quantity = quantity;
		this.price_amount = (double)this.quantity * prod.getPrice();
	}
	
	public Product getProd() {
		return prod;
	}
	//adds quantity to an existing check line
	public void addQuantity(int quantity) {
		this.quantity += quantity;
		this.price_amount = (double)this.quantity * prod.getPrice();
	}
	//returns price of check line
	public double getPrice_amount() {
		return price_amount;
	}
	//returns string of check line 
	public String toString()
	{
		return prod.getName() + ": quantity: " + this.quantity + " price: " + this.price_amount; 
	}
	
}
