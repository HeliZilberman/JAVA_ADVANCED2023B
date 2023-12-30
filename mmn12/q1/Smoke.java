
//Smoke extends Alarm
public class Smoke extends Alarm {
	private String operator_name;
	
	/**
	 * constructor
	 * @param address - the address of alarm location
	 * @param operator_name - the operator's name
	 * @throws BadAlarm
	 */
	public Smoke(String address, String operator_name) throws BadAlarm {
		super(address);
		this.operator_name = operator_name;
	}
	
	//prints alarm information; override abstract method action in Alarm
	public void action() {
		String str = (this instanceof Fire)?"":"Smoke Alarm: ";
		System.out.println(str + "address: " + address + super.toString() + " operator name:" + operator_name);
	}
}
