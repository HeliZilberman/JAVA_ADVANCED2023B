
/**
 * Maman12
 *
 * @author Heli Zilberman id: 322767005
 * @version 
 */
import java.util.ArrayList;


public class BigInt implements Comparable<BigInt>{
	private ArrayList<Byte> big_int; //save the numbers in an array list of bytes
	
	private boolean minus; //saves the sign of bigint - true when minus
	
	/**
     * constructor for object BigNumber, receives a String value and saves 
     * every single-digit number in big_int arraylist from end to start.
     * @param: str_big_int - the digits we save to our arraylist from and sign
     */
	public BigInt(String str_big_int) {
		big_int = new ArrayList<Byte>();
		if(str_big_int.length() > 1)  {
			if(str_big_int.charAt(0) == '-')
				minus = true;
			else if(str_big_int.charAt(0) == '+')
				minus = false;
			else
				throw new IllegalArgumentException("Big int has to start with sign - or +");
			for (int i = str_big_int.length() - 1; i > 0 ; i--) {
				if(str_big_int.charAt(i) >= '0' && str_big_int.charAt(i) <= '9') 
					big_int.add((byte) (str_big_int.charAt(i) - '0'));
				else 
					throw new IllegalArgumentException("All charcaters after sign must be digits");
			}
		}
		else {
			throw new IllegalArgumentException("Length of big int should be at least 2 with sign - or + and a digit");
		}
		this.removeZero();
	}
	
	
	
	/**
     * constructor for object BigNumber, receives a arraylist and minus 
     * to make a BigInt with params.
     * @param: big_int - the arraylist we save
     * 		   minus - the sign of bigInt
     */
	private BigInt(ArrayList<Byte> big_int,boolean minus) {
		this.minus = minus;
		this.big_int = big_int;
	}
	
	 /**
     * returns a string of the number
     * Overrides: toString in class java.lang.Object
     * returns: string of BigInt
     */
	public String toString() {
		String str = "";
		if(this.minus)
			str += '-';
		else
			str += '+';
		for (int i = this.big_int.size()-1; i >= 0; i--)
			str += this.big_int.get(i);
		return str;
			
	}
	
	 /**
     * The function recives a BigInt parameter and returns which BigNum
     * is bigger : 1 if this is bigger, -1 if other is bigger and 0 if equals
     * @param other - the bigNumber that is compared to this number
     *  Implements: compareTo in interface Comparable
     * returns: 0 if the numbers are equal/ 1 if this is bigger,-1 if other is biggeer
     */
	public int compareTo(BigInt other) {
		if(this.minus != other.minus)
			return (!this.minus)?1:-1;
		if(this.big_int.size() > other.big_int.size()) 
			return (!this.minus)?1:-1;
		if(this.big_int.size() < other.big_int.size()) 
			return (this.minus)?1:-1;
		for(int i = this.big_int.size()-1; i >= 0 ; i--) {
			if(this.big_int.get(i) != other.big_int.get(i))
				return (this.big_int.get(i) > other.big_int.get(i) && !(this.minus))? 1:-1;
		}
		return 0;
	}
	

	 /**
     * The function receives a BigInt parameter and returns the result
     * of the this BigInt added to the received BigInt in a BigInt parameter
     * @param other - the BigInt that is added to this BigIt
     * returns: a result of the addition of this BigInt and the other BigInt in a BigInt parameter
     */
	public BigInt plus(BigInt other) {
		BigInt result;
		if(other.minus) {
			other.minus = false;
			result = minus(other);
			other.minus = true;
		} else if (this.minus) {
			this.minus = false;
			result = other.minus(this);
			this.minus = true;
		} else {
			ArrayList<Byte> result_list = new ArrayList<Byte>();
			byte carry = 0, add = 0;
			for(int index = 0 ;index < Math.max(this.big_int.size(),other.big_int.size()); index++) {
				add = carry;
				add += (index < this.big_int.size())? this.big_int.get(index):0;
				add += (index < other.big_int.size())? other.big_int.get(index):0;
				carry = (byte) (add / 10);
				result_list.add((byte) (add%10));
			}
			if (carry > 0) {
				result_list.add(carry);
			}
			result = new BigInt(result_list,false);
			result.removeZero();
		}
		return result;
	}
	
	 /**
     * The function receives a BigInt parameter and returns the result
     * of the this BigInt minus the received BigInt in a BigInt parameter
     * @param other - the BigInt that is subtracted to this BigIt
     * returns: a result of the substruction of this BigInt and the other BigInt in a BigInt parameter
     */
	public BigInt minus(BigInt other) {
		BigInt result;
		if(other.minus) { //other is minus -- = +
			other.minus = false;
			result = plus(other);
			other.minus = true;
		}
		else if (this.minus) { //if this minus than -(this+other) = -this-other
			this.minus = false;
			result = plus(other);
			this.minus = true;
			result.minus = true;
		}
		else if(this.compareTo(other) < 0) { //if this < other than -(other-this)  = this-other
			result = other.minus(this);
			result.minus = true;
		}
		else { //this is bigger or equal to other
			ArrayList<Byte> result_list = new ArrayList<Byte>();
			byte minus = 0, carry = 0;
			for(int index = 0 ;index < this.big_int.size(); index++) {
				minus = (byte) (this.big_int.get(index) - carry);
				minus -= (index < other.big_int.size())? other.big_int.get(index):0;
				if (minus < 0) {
					minus += 10;
					carry = 1;
				}
				else 
					carry = 0;
				result_list.add(minus);
			}
			result = new BigInt(result_list,false);
			result.removeZero();
		}
		return result;
	}
	
	/**
     * The function receives a BigInt parameter and returns the result
     * of the this BigInt multiplied with the received BigInt in a BigInt parameter
     * @param other - the BigInt that is multiplied to this BigIt
     * returns: a result of the multiplication of this BigInt and the other BigInt in a BigInt parameter
     */
	public BigInt mult(BigInt other) {
		BigInt result = new BigInt("+0");
		int zero_counter = 0;
		boolean this_minus = this.minus ,other_minus = other.minus;
		this.minus = false;
		other.minus = false;
		for (int i = 0; i < this.big_int.size();i++) {
			BigInt temp = new BigInt("+0");
			for(int digit = this.big_int.get(i); digit > 0 ;digit--) 
				temp = temp.plus(other);
			for(int counter = zero_counter; counter > 0; counter-- ) {
				temp.big_int.add(0, (byte)0);
			}
			result = result.plus(temp);
			zero_counter++;
		}
		result.minus = (this_minus == other_minus)? false:true;
		this.minus = this_minus;
		other.minus = other_minus;
		result.removeZero();
		return result;
	}
	//private method that removes unnecessary zero at the start of number
	private void removeZero() {
		for(int i = big_int.size() - 1; i > 0 ;i--) {
			if(big_int.get(i) > 0)
				break;
			big_int.remove(i);
		}
		if(this.isZero())
			this.minus = false; //consider zero a positive
	}
	
	//private method returns if BigInt is zero
	private boolean isZero() {
		return this.big_int.get(this.big_int.size() - 1) == 0;
	}
	
	/**
     * The function receives a BigInt parameter and returns the result
     * of the this BigInt divided by the received BigInt in a BigInt parameter
     * @param other - the BigInt that divides this BigIt
     * returns: a result of the divide of this BigInt and the other BigInt in a BigInt parameter
     */
	public BigInt divide(BigInt other) {
		BigInt result = new BigInt("+0");
		if(other.isZero()) {
			throw new ArithmeticException("Cant divide with zero");
		}
		else{
			boolean this_minus = this.minus ,other_minus = other.minus;
			this.minus = false;
			other.minus = false;
			BigInt addOne = new BigInt("+1");
			BigInt temp = new BigInt(other.toString());
			while(temp.compareTo(this) <= 0) {
				result = result.plus(addOne);	
				temp = temp.plus(other);
			}
			result.minus = (this_minus == other_minus || result.isZero())? false:true;
			this.minus = this_minus;
			other.minus = other_minus;
		}
		return result;
	}
	
	/**
     * The function receives a BigInt parameter and returns if equals
     * @param o - the object that is compared to this BigIt
     * Overrides: equals in class java.lang.Object
     * returns: true if this equal to o
     */
	public boolean equals(Object o) {
		return (o instanceof BigInt) && (this.compareTo((BigInt)o)) == 0;
	}
	
}
