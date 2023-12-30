import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GraphController {

    @FXML
    private Button btn;

    @FXML
    private Canvas canv;
    
    private GraphicsContext gc;
    
    private Data data; //class data that contains graph data
    
    public void initialize() {
    	gc=canv.getGraphicsContext2D();
    	data = new Data();
    }
  
    
    @FXML
    void buttonPressed(ActionEvent event) {
    	gc.clearRect(0, 0 , canv.getWidth(), canv.getHeight()); 
        gc.setLineWidth(0.5);
        double x1 = 30, x2 = canv.getWidth()-40 , y1 = canv.getHeight()-40, y2 = 30 ;
        gc.strokeLine(x1, y2, x1 ,y1 ) ;
        gc.strokeLine(x1, y1 , x2 , y1) ;
        gc.strokeText(Integer.toString(data.getCurrYear()),canv.getWidth() / 2 ,30 );
        double jumpY = (y1-y2)/11.0, curr_y = y1;
        
        //temperature lines
        for(int temp = 0; temp <= data.getMaxCelsius();temp += 10) {
        	gc.strokeText(Integer.toString(temp),x1 - 15 ,curr_y);
        	curr_y -= jumpY;
        }
   
        double jumpX = (x2-x1)/(data.getMonths() + 1) , curr_x = x1 + jumpX;
        for(int month = 1; month <= data.getMonths();month++) {
        	gc.strokeText(Integer.toString(month),curr_x ,y1 + 20);
        	curr_x += jumpX;
        }
        gc.setFill(Color.GRAY);
        
        curr_y += jumpY;
        curr_x = x1 + jumpX;
        for(int month = 1; month <= data.getMonths(); month++, curr_x += jumpX) {
        	if(data.minMonth() == month)
        		gc.setFill(Color.BLUE);
        	else if(data.maxMonth() == month)
        		gc.setFill(Color.RED);
        	else
        		gc.setFill(Color.GRAY);
        	curr_y = jumpY*(data.GetMonthData(month)/10); //the aquetion temapture in graph
        	gc.fillRect(curr_x, y1 - curr_y, jumpX/2, curr_y);
        }
        data.nextYear();
        
        
    }

}
