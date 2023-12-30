import java.util.Date;

//Alarm abstract super class 
public abstract class Alarm {
	private Date operating_time; // the hour that the alarm occurs
	protected String address; //the address is used in action method
	
	/**
	 * Constructor  
	 * @param address - the address of alarm location
	 * @throws BadAlarm when address is null 
	 */
	public Alarm(String address) throws BadAlarm {
		operating_time = new Date();
		if(address == null) 
			throw new BadAlarm("BadAlarm: Address must be entered, null address is not allowed.");
		else
			this.address = address;

	}
	//returns string of time - as requested 
	public String toString() {
		int find = operating_time.toString().indexOf(':'); //find location of : to return operating time
		return " operating time:" + operating_time.toString().substring(find -2, find + 3);
	}
	
	//abstract method must be overridden by concrete subclasses
	public abstract void action();
}
