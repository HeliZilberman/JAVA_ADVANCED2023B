
/**
 * fire is an extended version of smoke 
 * therefore, fire inherits from smoke with added active value.
 */
public class Fire extends Smoke {

	private boolean active;
	/**
	 * constructor
	 * @param address - the address of alarm location
	 * @param operator_name - the operator's name
	 * @throws BadAlarm
	 */
	public Fire(String address, String operator_name) throws BadAlarm {
		super(address,operator_name);
		this.active = true;
	}
	
	
	public void action() {
		active = false;
		System.out.print("Fire Alarm: ");
		super.action();
	}
}
