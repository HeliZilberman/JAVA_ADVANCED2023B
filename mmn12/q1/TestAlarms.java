import java.util.ArrayList;

public class TestAlarms {
	
	/**
	 * Receives an ArrayList of alarms and calls every alarm's method action
	 * counts and prints the number of smoke alarms
	 * Initializes to 0 the floor of elevator alarm
	 * @param alarms
	 */
	public static void process(ArrayList<Alarm> alarms) {
		int smoke_count = 0;
		for (int i = 0; i < alarms.size(); i++) {
			alarms.get(i).action();
			if(alarms.get(i) instanceof Elevator) {
				((Elevator)alarms.get(i)).reset();
				
			} else if(alarms.get(i) instanceof Smoke && !(alarms.get(i) instanceof Fire)) {
				smoke_count++;
			}
		}
		System.out.println("number of smoke Alarms: " + smoke_count);
	}
	 
	public static void main(String[] args) {
		ArrayList<Alarm> alarms = new ArrayList<Alarm>();
		try { 
			alarms.add(new Smoke("Beer Yakov","Heli"));
			alarms.add(new Fire("Evergreen Terrace","Simpson"));
			alarms.add(new Smoke("New York","Alex"));
			alarms.add(new Elevator("Jerusalem",8));
			alarms.add(new Smoke("Tel Aviv","Loren"));
			alarms.add(new Fire("Miami","Nimrod"));
			alarms.add(new Smoke("London","Lisa"));
			alarms.add(new Elevator("Beer Sheva",1));
			alarms.add(new Smoke("sesame street","elmo"));
			alarms.add(new Fire("Lazy Town","Steffani"));
			alarms.add(new Elevator("Elm Street",3));
			alarms.add(new Smoke("Jump Street 21","Jeff"));
			alarms.add(new Fire("Wisteria Lane","Bri"));
			alarms.add(new Elevator("Paper Street",9));
			process(alarms);
		}catch(BadAlarm e) { //bad alarm exception only when address is null
			System.out.println(e.getMessage());
		}
		
	}

}
