import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//server class
public class Server extends Application{ 
	private static String[] appArgs = null; 
	
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("Server.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("System manager msg"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	public static void main(String[] args) { 
		appArgs = args;
		launch(args); 
		System.out.println();
	} 
	public static String[] getAppArgs() {
        return appArgs; // Access the application arguments from other classes
    }
}
