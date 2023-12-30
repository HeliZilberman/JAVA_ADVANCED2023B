import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ServerTrivia {
	private static final int NUM_OF_ANSWERS = 4; // every question consists of 5 lines - 1 question and 4 answers
	private ArrayList<Question> questions;
    private static final int QUESTION_COUNT = 20;
    public ServerTrivia() {
    	getQuestionsFromFile("Trivia");
    	Collections.shuffle(questions);
    }
    public boolean canAskQuestion(int i) {
    	return (i < QUESTION_COUNT);
    		
    }
    public synchronized Question getQuestion(int i) {
    	return questions.get(i);
    }
    private void getQuestionsFromFile(String filename) {
		questions = new ArrayList<>();
        ArrayList<String> answersList = new ArrayList<>();
        String currentQuestion = "";
        try (Scanner input = new Scanner(new File(filename + ".txt"))) {
            while (input.hasNext()) {
                currentQuestion = input.nextLine();
                System.out.println(currentQuestion);
                for (int i = 0; i < NUM_OF_ANSWERS; i++) {
                    answersList.add(input.nextLine());
                }
                this.questions.add(new Question(currentQuestion, answersList));
                answersList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
        }
	}
}
