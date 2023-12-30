import java.util.Scanner;
public class Main {
	
	/*
	 * inputs a number in string of digits that starts with sign from user
	 * if the number is incorrect : there is an exception, inputs number until number is correct 
	 */
	private static BigInt inputString()
	{
		Scanner scan = new Scanner(System.in);
		BigInt num_bi = null;
		boolean illegalBigInt = true;
		
		//loops until a legal BigInt is entered by user
		while(illegalBigInt) {
			System.out.println("Hello please enter number start with - or +");
			String num = scan.next();
			try {
				num_bi = new BigInt(num);
				illegalBigInt = false;
			} 
			catch (IllegalArgumentException e) { 
				System.out.println(e.getMessage() + "\nplease try again.");
			}
		}
		return num_bi;
	}
	/**
	 * Receives from user 2 numbers and prints the results of all public methods in BigInt
	 */
	public static void handle_input()
	{
		BigInt num1 = inputString();
		BigInt num2 = inputString();
		
		int compare = num1.compareTo(num2);
		switch(compare) {
		case 1:
			System.out.println(num1.toString() + " > " +num2.toString());
			break;
		case -1: 
			System.out.println(num1.toString() + " < " +num2.toString());	
			break;
		default:
			System.out.println(num1.toString() + " = " +num2.toString());	
			break;
		}
		System.out.println(num1.toString() + " + " +num2.toString() + " = " + num1.plus(num2).toString());
		System.out.println(num1.toString() + " - " +num2.toString() + " = " + num1.minus(num2).toString());
		System.out.println(num1.toString() + " * " +num2.toString() + " = " + num1.mult(num2).toString());
		try {
			System.out.println(num1.toString() + " / " +num2.toString() + " = " + num1.divide(num2).toString());
		}
		catch (ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		String equal =  (num1.equals(num2))?"=":"!=";
		System.out.println(num1.toString() + equal + num2.toString() );
	}
	
	public static void main(String[] args) {
		handle_input();
	}

}
