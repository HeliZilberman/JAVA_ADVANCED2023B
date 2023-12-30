package maman11Q1;

import java.util.Scanner;

public class Main {
	//handles all register's actions for the cashier
	public static void RegisterHandler() {
		Scanner scan = new Scanner(System.in);
		CashRegister register;
		System.out.println("Hello cashier,\nplease enter money amount if you'd like to reset register's money otherwise enter 0");
		double reg_set = scan.nextDouble();
		if(reg_set > 0) 
			register = new CashRegister(reg_set);
		else
			register = new CashRegister();
		int command = 0;  // command from cashier
		System.out.println("Press y to start cash registers actions, otherwise press n: ");
		char c = scan.next().charAt(0);
		if(c != 'y')
			command = -1;
		
		while(command != -1) {
			System.out.println("Choose the action: \n1 add product \n2 show check lines \n3 show check money ovaerall \n4 pay \n5 show register cash fund");
			command = scan.nextInt();
			switch(command) {
			case 1: 
				register.print_products();
				System.out.print("Please enter product name: ");
				String prod_name = scan.next(); 
				System.out.print("Please enter product quantity: ");
				int q = scan.nextInt();
				register.AddProduct(new Product(prod_name), q);
				break;
			case 2: 
				System.out.println("check:\n" + register.GetCheckStr() + "overrall:" + register.GetCheckSum());
				break;
			case 3:
				System.out.println("Overall charge is: " + register.GetCheckSum());
				break;
			case 4: //user enters payment check closes and check and change is printed
				System.out.println("Please enter payment: ");
				double pay = scan.nextDouble();
				System.out.println("check:\n" + register.GetCheckStr()+ "\nOverrall:" + register.GetCheckSum() + "\nChange:" + register.Payment(pay));
				break;
			case 5:
				System.out.println("Register cash is: " + register.getReg_money());
				break;
			default:
				System.out.println("Undenefied action number");
				break;
			}
			System.out.println("Press y if you'd like to countinue cash register actions otherwise enter n");
			c = scan.next().charAt(0);
			if(c != 'y')
				command = -1;
		}
		scan.close();
	}
	public static void main(String[] args) {
		RegisterHandler();
	}

}
