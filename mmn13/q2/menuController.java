
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;


import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class menuController {

    @FXML
    private VBox vbox;
    
    private ArrayList<Item> menu; //the menu and choice of current costumer
    
    //initializes the menu for costumers
    public void initialize() {
    	vbox.getChildren().clear();
    	menu = GetMenuFromFile("menu");
    	ColumnConstraints col = new ColumnConstraints();
    	col.setPercentWidth(25);
    	int course_grid = -1;
    	for(int i = 0; i < menu.size(); i++) {
    		if(menu.get(i).getCourseIdx() > course_grid) {
    			//prints the course type : first,main etc.
    			Text c = new Text(menu.get(i).getCourse());
    			c.setStyle("-fx-font: 16 arial;");
    			vbox.getChildren().add(c);
    			course_grid = menu.get(i).getCourseIdx();
    		}
    		GridPane grid = new GridPane();
    		
    		grid.addColumn(0,new Text(menu.get(i).getName()));
    		grid.addColumn(1,new Text(menu.get(i).getPrice() + ""));
    		grid.addColumn(2, menu.get(i).getCheckBox());
    		grid.addColumn(3,menu.get(i).getQuantityBox());
    		grid.getColumnConstraints().addAll(col,col,col,col);
			vbox.getChildren().add(grid);
    	}
    	Button order = new Button("order");
    	order.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	orderPressed(e);
		    }
		});
    	vbox.getChildren().add(order);
    }
    
    //gets menu from file and saves in array list for costumer
    private ArrayList<Item> GetMenuFromFile(String fileName){
    	ArrayList<Item> menu = new ArrayList<Item>();
		 try (Scanner input = new Scanner(new File(fileName + ".txt"))) {
	            while (input.hasNext()) {
	            	try {
		                String name = input.nextLine();
		                String courseKind = input.nextLine();
		                double price = Double.parseDouble(input.nextLine());
		                int courseIdx = Item.checkCourse(courseKind);
		                System.out.println(name + " " + price + " " + courseKind);
		                if(courseIdx != -1) {
		                	menu.add(new Item(name,courseIdx,price));
		                }
		                else {
		                	//error
		                }
	            	}catch (NumberFormatException e) {
	            		
	            	}
	            }
	            Collections.sort(menu, new Comparator<Item>(){
	                public int compare(Item i1, Item i2) {
	                    return i1.compareTo(i2);
	                }
	            });
	        } catch (FileNotFoundException e) {
	        }
		 
		 return menu;
	}
    @FXML
    void orderPressed(ActionEvent event) { //when buttom is pressed
    	StringBuilder bill = new StringBuilder("The bill:\n");
    	double overall = 0;
    	for(int i = 0; i < menu.size(); i++) {
    		double price = menu.get(i).getItemBillPrice();
    		if(price > -1) { 
    			bill.append(menu.get(i).toString());
    			bill.append(" price of quantity: ");
    			bill.append(price);
    			bill.append("\n");
    			overall += price;
    		}
    	}
    	bill.append("overall: ");
		bill.append(String.format("%.2f",overall));
  
    	
    	JTextArea jta = new JTextArea(bill.toString());
        JScrollPane jsp = new JScrollPane(jta){
             @Override
             public Dimension getPreferredSize() {
                 return new Dimension(750, 320);
             }
         };
 

    	
    	String[] options = { "Approve order", "Change order" ,"Cancel order"};
    	int selection = JOptionPane.showOptionDialog(null,jsp, "Bill", 
    	                                                      0, 3, null, options, options[0]);
    	
    	if(selection == 0) {
    		CreateFile(bill);
    		IntializeOrder();
    	} if(selection == 2) {
    		IntializeOrder();
    	}
    	
    }
    
    //Initializes the costumer order when ends order or canceles order
    private void IntializeOrder () {
    	for(int i = 0; i < menu.size(); i++) 
    		menu.get(i).cancelOrderInitialize();
    }
    
    //Creates and writes into file for costumer that ordered
    private void CreateFile(StringBuilder bill) {
    	String name_id = JOptionPane.showInputDialog("please enter name and ID");
		if (name_id.length() == 0) {
			JOptionPane.showConfirmDialog(null, "Error", "File name missing.", JOptionPane.CLOSED_OPTION);
		} else {
			try {
			      File bill_file = new File(name_id + ".txt");
			      if (bill_file.createNewFile()) {
			        System.out.println("File created: " + bill_file.getName());
			        try {
			            FileWriter myWriter = new FileWriter(name_id + ".txt");
			            myWriter.write(bill.toString());
			            myWriter.close();
			          } catch (IOException e) {
			        	  JOptionPane.showConfirmDialog(null, "Error", "An error occurred writing to file.", JOptionPane.CLOSED_OPTION);
			            e.printStackTrace();
			          }
			      } else {
			    	  JOptionPane.showConfirmDialog(null, "Error", "File already exists.", JOptionPane.CLOSED_OPTION);
			      }
			} catch (IOException e) {
			      JOptionPane.showConfirmDialog(null, "Error", "An error occurred creating file.", JOptionPane.CLOSED_OPTION);
			      e.printStackTrace();
			}
		}
    }
}