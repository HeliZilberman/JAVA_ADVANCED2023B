
//Elevator class extends Alarm
public class Elevator extends Alarm {
	
	private int floor; 
	
	/**
	 * constructor
	 * @param address - the address of alarm location
	 * @param floor - the floor the elevator is in
	 * @throws BadAlarm when address is null
	 */
	public Elevator(String address, int floor) throws BadAlarm {
		super(address);
		this.floor = floor;
	}
	// prints alarm information; override abstract method action in Alarm
	public void action() {
		System.out.println("Elevator Alarm: " + "address: " + address  + super.toString() + " floor: " + floor);
	}
	//sets elevator floor to 0 
	public void reset() {
		floor = 0;
	}
}
