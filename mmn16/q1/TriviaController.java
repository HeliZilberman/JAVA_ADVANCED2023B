
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//client use of trivia 
public class TriviaController {
	 
	@FXML
	private Text ans1;
	@FXML
	private Text ans2;
	@FXML
	private Text ans3;
	@FXML
	private Text ans4;
	@FXML
    private Button btnFour;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnThree;

    @FXML
    private Button btnTwo;

    @FXML
    private Text question;

    @FXML
    private Text result;
    

    @FXML
    private VBox vbox;
    
    private boolean buttonPressed;
    private Socket socket;
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3333;
    private static final int NUM_QUESTIONS = 20;
    private int score;
    private int questionNumber;
    private Question q;
    private ObjectInputStream objInputStream;
    private PrintWriter out;
    
    public void initialize() {
    	buttonPressed = false;
    	try {
    		socket = new Socket(SERVER_HOST, SERVER_PORT);
    		objInputStream = new ObjectInputStream(socket.getInputStream());
    		out = new PrintWriter(socket.getOutputStream(),true);
            questionNumber = 0;
            score = 0;
            new ClientThread(this).start(); // client thrad responsible of timer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getQuestion() {
    	
		try {
			
			q = (Question)objInputStream.readObject();
			System.out.println("question" + q.getQuestion());
			question.setText(q.getQuestion());
            result.setText(score +"");
            ArrayList<String> answers = q.getAnswers();
            ableAnsBtns();
            ans1.setText("" + answers.get(0));
            ans2.setText("" + answers.get(1));
            ans3.setText("" + answers.get(2));
            ans4.setText("" + answers.get(3));
			questionNumber++;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public int getQuestioNum() {
    	return questionNumber;
    }
    
    //handles answers click
    @FXML
    void handleAnswerClick(ActionEvent event) {
    	buttonPressed = true;
    	Button btn = (Button) event.getSource();
        String id = btn.getId();
        switch (id) {
            case "btnOne":
            	fixScore(ans1.getText());
                break;
            case "btnTwo":
            	fixScore(ans2.getText());
                break;
            case "btnThree":
            	fixScore(ans3.getText());
                break;
            case "btnFour":
            	fixScore(ans4.getText());
                break;
            default:
                break;
        }
        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");
        disableAnsBtns();
    }
    
    //fixes score
    private void fixScore(String str) {
    	if(q.isCorrectAnswer(str)) 
    		score+=10;
    	else
    		score-=5;
    }
    public void setButtonPressed(boolean b) {
    	buttonPressed = b;
    }
    
    public boolean getButtonPressed() {
    	return buttonPressed;
    }
    public void addScore(int add) {
    	score+=add;
    }
    public void endTrivia() {
    	question.setText("Game Over");
        result.setText("score: " + score );
        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");
    }
    private void disableAnsBtns() {
    	btnOne.setDisable(true);
    	btnTwo.setDisable(true);
    	btnThree.setDisable(true);
    	btnFour.setDisable(true);
    }
    
    private void ableAnsBtns() {
    	btnOne.setDisable(false);
    	btnTwo.setDisable(false);
    	btnThree.setDisable(false);
    	btnFour.setDisable(false);
    }
    public void closeResources() {
		try {
			if(objInputStream!=null)
				objInputStream.close();
			if(socket != null)
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void giveAnswer() {
    	out.println("we are in question" + questionNumber);
    }
    public void addClosingEvent() {
		Stage stage = (Stage)((Node) vbox).getScene().getWindow();
		stage.getScene().getWindow().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
			closeResources();
		});	
	}
   
}
