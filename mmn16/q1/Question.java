import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

// question represents a question in the quiz.
public class Question implements Serializable {

    private String question;
    private ArrayList<String> answers;
    private String correct_ans;

    public Question(String question, ArrayList<String> answers) {
        this.question = question;
        this.correct_ans = answers.get(0); //the first is the answer
        this.answers = answers;
    }

    // get question 
    public String getQuestion() {
        return question;
    }

    // get the list of possible answers
    public ArrayList<String> getAnswers() {
        Collections.shuffle(answers); // randomize the order of the answers
        return answers;
    }

    // get the correct answer
    public String getCorrectAnswer() {
        return correct_ans;
    }
    
    public boolean isCorrectAnswer(String str) {
    	if(str.compareTo(correct_ans) == 0) {
    		return true;
    	} return false;
    }
}