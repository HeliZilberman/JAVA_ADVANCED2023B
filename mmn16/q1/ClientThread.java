import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ClientThread extends Thread{
	private TriviaController cont;
	 private static final int NUM_QUESTIONS = 20;
	 private int TIME_LIMIT_SECONDS = 10;
	 private Timer timer;
	 public ClientThread(TriviaController cont) {
		 this.cont=cont;
	 }
	 
	 //runs on questions and move to next question after time ends
	 public void run() {
		 cont.getQuestion();
         ActionListener listener = new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 //cont.giveAnswer();
            	 cont.addClosingEvent();
             	if(cont.getButtonPressed()) {
             		cont.setButtonPressed(false);
             	} else 
             		cont.addScore(-5);
             	if(cont.getQuestioNum() >= NUM_QUESTIONS) {
                 	 cont.endTrivia();
                 	 cont.closeResources();
                     timer.stop();
                 } else {
                 	cont.getQuestion();
                 }
             }
         };
         timer = new Timer(TIME_LIMIT_SECONDS * 1000, listener);
         timer.start();
	 }
}
