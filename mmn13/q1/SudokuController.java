import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private GridPane grid;
    
    private TextField [][] cells;
    private final int BOARD_SIZE = 9;
    private final int CELL_SIZE = 44;
    
    
   //build sudoku in grid
    public void initialize() {
		cells = new TextField[BOARD_SIZE][BOARD_SIZE];
		grid.getRowConstraints().remove(0);
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				cells[i][j] = new TextField("");
				cells[i][j].setPrefSize(CELL_SIZE, CELL_SIZE);
				cells[i][j].setMaxWidth(CELL_SIZE);
				cells[i][j].setAlignment(javafx.geometry.Pos.CENTER);
				if(((i<3 || i>5) && (j<3 ||j>5)) || (i>2 && i<6 && j>2 && j<6)) 
					cells[i][j].setStyle("-fx-border-color: gray; -fx-background-color: #00000022;" + " -fx-text-fill: black;");
				grid.add(cells[i][j], i, j);
				final Integer innerI = new Integer(i);
				final Integer innerJ = new Integer(j);
				cells[i][j].setOnKeyPressed( event -> { //in case enter is pressed
					  if( (KeyCode)event.getCode() == KeyCode.ENTER) {
						  handleInput(innerI,innerJ); //check key 
					  } 
					} );
			}
		}
    }
    //checks input is correct after entered in text field 
    private void handleInput(int i, int j) {
    	try {
    		int number = Integer.parseInt(cells[i][j].getText());
    		System.out.println("number:" + number);
    		if(number < 1 || number > 9) {
    			cells[i][j].setText("");
    			JOptionPane.showConfirmDialog(null, "number out of range", "Error", JOptionPane.CLOSED_OPTION);
    		} else if(!checkSudokuLaw(number,i,j)) {
    			cells[i][j].setText("");
    		}
    	}catch (NumberFormatException e) {
    		JOptionPane.showConfirmDialog(null, "not a number", "Error", JOptionPane.CLOSED_OPTION);
    		cells[i][j].setText("");
    	}
    }
    // checks input is correct by sudoku laws 
    private boolean checkSudokuLaw(int number,int i,int j)
    {
    	for(int col = 0; col < BOARD_SIZE; col++) {
    		try {
        		int temp = Integer.parseInt(cells[i][col].getText());
        		if (temp == number && col!=j) {
        			JOptionPane.showConfirmDialog(null, "number exists in same colum", "Error", JOptionPane.CLOSED_OPTION);
        			return false;
        		}
        		
    		} catch (NumberFormatException e){	
    		}
    	}
    	for(int row = 0; row < BOARD_SIZE; row++) {
    		try {
        		int temp = Integer.parseInt(cells[row][j].getText());
        		if (temp == number && row!=i) {
        			JOptionPane.showConfirmDialog(null, "number exists in same row", "Error", JOptionPane.CLOSED_OPTION);
        			return false;
        		}
        		
    		} catch (NumberFormatException e){	
    		}
    	}
    	int row = (i/3)*3,col = (j/3)*3;
    	int endRow = row+3,endCol = col+3;
    	for (;row < endRow && col < endCol;row++,col++) {
    		if(row != i && col != j) {
    			try {
            		int temp = Integer.parseInt(cells[row][col].getText());
            		if (temp == number) {
            			JOptionPane.showConfirmDialog(null, "number exists in same block", "Error", JOptionPane.CLOSED_OPTION);
            			return false;
            		}
            		
        		} catch (NumberFormatException e){	
        		}
    		}
    	}
    	return true;
    	
    }
    @FXML
    void clearPressed(ActionEvent event) { 
    	for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				cells[i][j].setText("");
				cells[i][j].setEditable(true);
				cells[i][j].setStyle(cells[i][j].getStyle() + " -fx-text-fill: black;");
			}
    	}
    }

    @FXML
    void setPressed(ActionEvent event) {
    	for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				if(this.cells[i][j].getText().compareTo("") != 0) {
					cells[i][j].setEditable(false);
       				cells[i][j].setStyle(cells[i][j].getStyle() + " -fx-text-fill: blue;");
				}
					
			}
    	}
    }
 

}
