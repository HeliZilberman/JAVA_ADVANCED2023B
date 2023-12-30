package maman11Q1;

import java.util.ArrayList;
import java.util.HashMap;
public class CashRegister{
	//hash map contains products
	private static HashMap<String, Double> products = new HashMap<String, Double>();
	
	private double reg_money;
	
	private ArrayList<CheckLine> check;
	
	//initialize products,regsiter's cash and check 
	public CashRegister(double reg_money) {
		products.put("apple",3.4);
		products.put("bread",11.0);
		products.put("mango" ,9.7);
		products.put("milk",10.4);
		products.put("chicken",25.4);
		products.put("egg",0.8);
		products.put("meat",40.9);
		products.put("lemon",3.2);
		this.reg_money = reg_money;
		check = new ArrayList<CheckLine>();
	}
	
	public CashRegister() {
		this(0);
	}
	
	// adds quantity products to clients check 
	public void AddProduct(Product prod, int quantity) {
		
		//setting price of product - assuming input is valid
		prod.setPrice(products.get(prod.getName())); 
		
		int i;
		//check if product already exists in check
		for(i = 0; i < check.size(); i++) {
			if (check.get(i).getProd().equals(prod)) {
				check.get(i).addQuantity(quantity); //adds the quantity of product
				break;
			}
		}
		if(i == check.size())
			check.add(new CheckLine(prod,quantity)); //if product does'nt exist in check
	}
	
	// returns string of clients check 
	public String GetCheckStr() {
		String CheckStr = "";
		for(int i = 0; i < check.size(); i++) {
			CheckStr += check.get(i).toString() + "\n";
		} // without las /n
		return CheckStr;
	}
	
	// returns current client's check sum 
	public double GetCheckSum() {
		double sum = 0;
		for(int i = 0; i < check.size(); i++) {
			sum += check.get(i).getPrice_amount();
		}
		return sum;
	}
	/** current client pay, returns change **/
	public double Payment(double pay) {
		double sum = this.GetCheckSum();
		this.reg_money += sum;
		check.clear();
		return (pay - sum);
	}
	
	public double getReg_money() {
		return reg_money;
	}
	
	public void print_products() {
		for (String i : products.keySet()) {
		      System.out.println(i + " " + products.get(i));
		 }
	}
}
