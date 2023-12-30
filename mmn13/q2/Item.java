

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;


//item class contains a menu item of costumer
public class Item implements Comparable<Item> { 
	private final static String [] courses = {"First Course","Main Course","Last Course","Drink"}; 
	private String name;
	private int course;
	private double price;
    private ComboBox<Integer> quantity_box; //quantity that costumer chooses
    private final int MAX_QUANTITY = 10;
    private CheckBox check_box; // did costumer choose this item 
	public Item(String name,int course,double price) {
		this.name = name;
		this.course = course;
		this.price = price;
		this.check_box = new CheckBox();
		this.quantity_box = new ComboBox<Integer>();
		for (int j = 1; j <= MAX_QUANTITY; j++)
			quantity_box.getItems().add(j);
		quantity_box.setValue(0);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return courses[course];
	}
	public int getCourseIdx() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ComboBox<Integer> getQuantityBox() {
		return this.quantity_box;
	}
	public CheckBox getCheckBox() {
		return check_box;
	}
	public static int checkCourse(String course) {
		for (int i = 0; i < courses.length ;i++ ) {
			if(courses[i].equals(course))
				return i;
		}
		return -1;
	}

	public int compareTo(Item other) {
		if(this.course == other.course)
			return 0;
		return (this.course > other.course)? 1:-1;
	}
	
	public String toString() {
		return (name + " price:" + String.format("%.2f",price) + " quantity " + quantity_box.getValue());
	}
	//returns the price of quantity of item or -1 if item wasnt chosen
	public double getItemBillPrice() {
		if(check_box.isSelected()) {
			return ((double)quantity_box.getValue() * price);
		}
		return -1;
	}
	
	public void cancelOrderInitialize() {
		quantity_box.setValue(0);
		check_box.setSelected(false);
	}
	
}
