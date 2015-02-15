import javax.swing.JFrame;
import javax.swing.JButton; 
import java.awt.GridLayout; 
//Joseph Santantasio and Brabim Baral


public class BoardGrid {
    JButton[][] boardgrid; // instance for Grid of Buttons
    JFrame frame=new JFrame(); // Instance for Frame
    
    //Creates a 9*9 Grid Board with frame size of 1000*1000
    public BoardGrid(int breadth, int length){

	//Set frame size to 1000*1000
	frame.setSize(1000,1000);
	
	//Create 9*9 Board Grid with each grid as a button  
	frame.setLayout(new GridLayout(breadth,length,10,10)); 
	
	boardgrid = new JButton[breadth][length];
	
	for(int i = 0; i < length; i++){
	    for(int j = 0; j< breadth; j++){
		boardgrid[j][i] = new JButton("("+j+","+i+")"); 
		
		//Adds buttons to frame as grids
		frame.add(boardgrid[j][i]); 
	    }
	}
	
	//Program closes on exiting window.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	frame.setVisible(true); 
    }
    
    // Creates the BoardGrid.
    public static void main(String[] args) {
	new BoardGrid(9,9);
    }
}


