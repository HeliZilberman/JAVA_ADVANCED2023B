
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class CalanderController {
    @FXML
    private ComboBox<String> dayComboBox;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private TextArea textArea;
    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private VBox vBox; //use when file needs to be saved
    private HashMap<Date, String> h;

    
    public void initialize()
    {
    	initializeComboBox();
    	loadFromFile();
    }
    
    private void initializeComboBox() {
    	final int DAYS = 31 , MONTHS = 12, START_YEAR = 2000, END_YEAR = 2023;
    	h = new HashMap<Date, String>();
    	for (int i = 1; i <= DAYS; i++)
			dayComboBox.getItems().add(String.valueOf(i));
		dayComboBox.setValue("1");

		for (int i = 1; i <= MONTHS; i++)
			monthComboBox.getItems().add(String.valueOf(i));
		monthComboBox.setValue("1");

		for (int i = START_YEAR; i <= END_YEAR; i++)
			yearComboBox.getItems().add(String.valueOf(i));
		yearComboBox.setValue("2000");
    }
    
    private void loadFromFile() {
    	File file = getFile();
		if (file != null) {
			try {

				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fi);
				h = (HashMap<Date, String>)ois.readObject();
				ois.close();
				fi.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch(ClassCastException e) {
				e.printStackTrace();
			}
		}
    }
    
    /**
     * saves to file the saved information saved by user 
     * either to new file that user enters or existing file user chooses
     */
    private void saveToFile() {
    	File file;

    	String[] options = { "existing file", "new file"};
        int selection = JOptionPane.showOptionDialog(null, "Save file", "Save to:", 
                                                          0, 2, null, options, options[0]);
        if (selection == 0) {
        	file = getFile();
        	saveToFile(file);
        }
        if (selection == 1) { 
        	String file_name = JOptionPane.showInputDialog("enter file name");
    		if (file_name.length() == 0) {
    			JOptionPane.showConfirmDialog(null, "Error", "File name missing.", JOptionPane.CLOSED_OPTION);
    		} else {
    			try {
    			      file = new File(file_name);
    			      if (file.createNewFile()) {
    			        System.out.println("File created: " + file.getName());
    			      
    			      } else {
    			    	  JOptionPane.showConfirmDialog(null, "Error", "File already exists.", JOptionPane.CLOSED_OPTION);
    			      }
    			      saveToFile(file);
    			} catch (IOException e) {
    			      JOptionPane.showConfirmDialog(null, "Error", "An error occurred creating file.", JOptionPane.CLOSED_OPTION);
    			      e.printStackTrace();
    			}
    		}
        }
    }
    //saves in file info
    private void saveToFile(File file) {
    	try {
			FileOutputStream fo = new FileOutputStream(file); //filenotfound exeption
			ObjectOutputStream out = new ObjectOutputStream(fo); //io exeption
			out.writeObject(h);
			out.close();
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    //returns file user chose in directory
    private File getFile()   {
    	FileChooser fileChooser = new FileChooser(); 
		fileChooser.setTitle("Select file: "); 
		fileChooser.setInitialDirectory(new File(".")); //the location of the project
		return fileChooser.showOpenDialog(null);
    }
    
    
    @FXML 
    void savePressed(ActionEvent event) {
    	Date d = new Date(Integer.parseInt(dayComboBox.getValue()), 
				Integer.parseInt(monthComboBox.getValue()),
				Integer.parseInt(yearComboBox.getValue()));
    	h.put(d,textArea.getText());
    	userSaveFileEvent(); //if user saved new information the app will suggest to save to file
    }

    @FXML
    void showPressed(ActionEvent event) {
    	Date d = new Date(Integer.parseInt(dayComboBox.getValue()), 
				Integer.parseInt(monthComboBox.getValue()),
				Integer.parseInt(yearComboBox.getValue()));

    	textArea.setText(h.get(d));
    }
    
    // when user closes program - the program suggests saving to file 
	private void userSaveFileEvent() {
		Stage stage = (Stage)((Node)vBox).getScene().getWindow();
		stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
			saveToFile();
			stage.close();
		});	
	}
}