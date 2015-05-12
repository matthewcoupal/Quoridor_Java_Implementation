package main.java.UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.java.Board;
import main.java.Space;
import main.java.Wall;

/**
 *@author: Brabim Baral, Joseph Santantasio, Matthew Coupal
 */
public class GameBoard extends Board{

	private JFrame frame;
	private JPanel panel;
	private final int BUTTON_SIZE = 7; //size of intersection button
	private final int BOARD_SIZE = 9; 	//number of spaces that the board is wide
	private final int BUTTON_SCALE = 7; //ratio of intersection button : playerbutton
	public String currentMove = "";
	public static PlayerButton[][] boardGrid = new PlayerButton[9][9];
	public static WallButton_Vertical[][] vertWalls = new WallButton_Vertical[8][9];
	public static WallButton_Horizontal[][] horzWalls = new WallButton_Horizontal[9][8];
	public Space wallString = null;
	public int playNum;
	private int[] turn;
	private int currTurn = -1;
	private int currentPlayerNum;

	/**
	 * Constructs the GameBoard
	 * @param The number of players in the game
	 * @param The name of the player for whom the GUI belongs
	 */
	public GameBoard(int numPlayers, String player){
		super(numPlayers);
		//Set up the turn order
		turn = new int[numPlayers];
		setPlayNum(player);
		if (numPlayers == 2) {
				turn[0] = 0;
				turn[1] = 1;
			} else {
				turn[0] = 0;
				turn[1] = 3;
				turn[2] = 1;
				turn[3] = 2;
        }
        //Create the gui itself
		frame = new JFrame(player);
		panel = new JPanel();
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 0, 0);
		panel.setLayout(flow);
		panel.setPreferredSize( new Dimension(( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE)
				+ (BUTTON_SIZE*(BOARD_SIZE-1)) ) ,
				( ((BUTTON_SIZE*BUTTON_SCALE)*BOARD_SIZE) +
						(BUTTON_SIZE*(BOARD_SIZE-1)) )) );
		addRow_PlayerB_WallBvert(0);
		for( int i=1; i<=BOARD_SIZE-1; i++){
			addRow_WallBHorizont_IntersectionB(i);
			addRow_PlayerB_WallBvert(i);
		}
		frame.add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setResizable( false );
		frame.setLocation( 100, 100 );
		frame.setVisible( true );
		frame.setJMenuBar( getJMenuBar());
		frame.pack();
		updatePositions();
		
	}

    /**
    * Set the player number
    * @param The player name
    */
	private void setPlayNum(String player) {
        if(player.equals("Client"))
            this.playNum = -1;
        else
            this.playNum = Integer.parseInt(player)-1;
	}

	/**
	* Adds buttons for space nodes and vertical walls in the panel
	* @param the row to place the buttons on in the grid
	*/
	public void addRow_PlayerB_WallBvert(int index){
		int i = 0;
		boardGrid[i][index] =  new PlayerButton( BUTTON_SIZE, BUTTON_SCALE, i, index, action(i, index) );
		panel.add(boardGrid[i][index]);
		for( i=1; i<=BOARD_SIZE-1; i++){
			vertWalls[i-1][index] = new WallButton_Vertical( BUTTON_SIZE, BUTTON_SCALE, i-1, index, wallPlacement(i-1, index) );
			panel.add(vertWalls[i-1][index]) ;
			boardGrid[i][index] =  new PlayerButton( BUTTON_SIZE, BUTTON_SCALE, i, index, action(i, index) );
			panel.add(boardGrid[i][index]);
		}
	}

    /**
	* Adds buttons for Horizontal wall  and intersection buttons in the panel
	* @param the row to place the buttons on in the grid
	*/
	public void addRow_WallBHorizont_IntersectionB(int index){
		int i = 0;
		horzWalls[i][index-1] = new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE, index-1, i, wallPlacement(i, index-1));
		panel.add(horzWalls[i][index-1]);
		for( i=1; i<= BOARD_SIZE -1; i++){
			panel.add( new IntersectionButton( BUTTON_SIZE ) );
			if(index != 9) {
				horzWalls[i][index-1] = new WallButton_Horizontal( BUTTON_SIZE, BUTTON_SCALE, index-1, i, wallPlacement(i, index-1));
				panel.add(horzWalls[i][index-1]);
			}
		}
	}

    /**
    * Generates the action listener for the regular space buttons
    * @param the x value of the given space
    * @param the y value of the given space
    * @return an action listener which will set currentMove to the coordinates of the space
    */
	private ActionListener action(int x, int y) {
		final int xl = x;
		final int yl = y;
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if (currentPlayerNum == playNum) {
                    Space space = new Space(xl, yl);
                    Board board = new Board();
                    System.out.print(board.spaceToString(space));
                    currentMove = board.spaceToString(space);
			    }
			}
		};
	}

	/**
	 * Gets the move made by the player and resets the current player's move to empty.
	 * @return the move string
	 */
	public String getMove() {
		String move = this.currentMove;
		this.currentMove = "";
		return move;
	}

	/**
	 * Sets the text of a given button.
	 * @param Button The jButton whose text is to change
	 * @param The number of the player whose info will be placed on the button
	 */
	private void setBackground (JButton button, int player) {
		if(player == 0) {
			button.setText("P1    " + occupiedSpaces.get(player).getWalls());
		}
		else if(player == 1) {
			button.setText("P2    " + occupiedSpaces.get(player).getWalls());
		}
		else if(player == 2) {
			button.setText("P3    " + occupiedSpaces.get(player).getWalls());
		}
		else if(player == 3) {
			button.setText("P4    " + occupiedSpaces.get(player).getWalls());
		}
	}

    /**
    * Generates the action listener for the wall buttons
    * @param the x value of the given wall
    * @param the y value of the given wall
    * @return an action listener which will set currentMove to the coordinates of the wall
    */
	public ActionListener wallPlacement(int x, int y){
		final int xl = x;
		final int yl = y;

		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if(currentPlayerNum == playNum) {
                    Board board = new Board();
                    Space space = new Space(xl, yl);
                    if(wallString == null){
                        wallString = space;
                    }else{
                        Wall wall = new Wall(space, wallString);
                        currentMove = board.wallToString(wall);
                        wallString = null;
                    }
			    }
			}
		};
	}

	public JMenuBar getJMenuBar(){
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu( "File" );

		JMenuItem jmi = new JMenuItem( "Start" );
		jm.add( jmi );

		jmi = new JMenuItem( "Hello" );
		jm.add( jmi );

		jmi = new JMenuItem( "Exit" );
		jm.add( jmi );

		jmb.add( jm );

		return jmb;
	}
	/**
	 * Checks the occupied spaces on the board and sets the background of the
	 * space occupied by the current player to RED and sets the text on all occupied spaces
	 */
	public void updatePositions () {
		update();
		//This method was called means someone took a turn so increment currTurn
		currTurn++;
		int xCoordinate = 0;
		int yCoordinate = 0;
		for(int i = 0; i < this.occupiedSpaces.size(); i++) {
			if(this.occupiedSpaces.get(i) != null) {
			xCoordinate = this.occupiedSpaces.get(i).getX();
			yCoordinate = this.occupiedSpaces.get(i).getY();
			setBackground(boardGrid[xCoordinate][yCoordinate], i);
			}
		}
		currentPlayerNum = turn[currTurn % this.occupiedSpaces.size()];
		while(this.occupiedSpaces.get(currentPlayerNum) == null) {
			currTurn++;
			currentPlayerNum = turn[currTurn % this.occupiedSpaces.size()];
		}
		xCoordinate = this.occupiedSpaces.get(currentPlayerNum).getX();
        yCoordinate = this.occupiedSpaces.get(currentPlayerNum).getY();
        boardGrid[xCoordinate][yCoordinate].setBackground(Color.RED);
		for(int i = 0; i < this.placedWalls.size(); i++) {
			Wall temp = this.placedWalls.get(i);
			if(temp.isVertical()) {
				vertWalls[temp.getS0().getX()][temp.getS0().getY()].setBackground(Color.BLACK);
				vertWalls[temp.getS1().getX()][temp.getS1().getY()].setBackground(Color.BLACK);
			} else if(temp.isHorizontal()) {
				horzWalls[temp.getS0().getX()][temp.getS0().getY()].setBackground(Color.BLACK);
				horzWalls[temp.getS1().getX()][temp.getS1().getY()].setBackground(Color.BLACK);
			}
		}
	}

	/**
	 * Resets the spaces' background color to the default: LIGHT_GRAY
	 * and sets all buttons' text to be the empty string
	 */
	public void update () {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				boardGrid[i][j].setBackground(Color.LIGHT_GRAY);
				boardGrid[i][j].setText("");
			}
		}
	}
}

//Constructs buttons for as board tiles with ActionListener
class PlayerButton extends JButton{

	public int xl;
	public int yl;
	public String text = "";

	public PlayerButton( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
		super();
		this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE*BUTTON_SCALE ));
		this.xl = x;
		this.yl = y;
		this.setText(text);
		this.addActionListener(action);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		this.setBackground( Color.GRAY);
	}
}

//Constructs buttons for vertical wall with ActionListener
class WallButton_Vertical extends JButton{

	public int xl;
	public int yl;

	public WallButton_Vertical( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
		super();
		this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE*BUTTON_SCALE ));
		this.xl = x;
		this.yl = y;
		this.addActionListener(action);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		this.setBackground( Color.WHITE );

	}
}

/*
 *Constructs buttons for Horizontal Wall with ActionListener
 */
class WallButton_Horizontal extends JButton{

	public int xl;
	public int yl;

	public WallButton_Horizontal( int BUTTON_SIZE, int BUTTON_SCALE, int x, int y, ActionListener action ){
		super();
		this.setPreferredSize( new Dimension( BUTTON_SIZE*BUTTON_SCALE, BUTTON_SIZE ));
		this.xl = x;
		this.yl =y;
		this.addActionListener(action);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		this.setBackground( Color.WHITE );

	}
}

/**
 * Construct instersection of Horizontal and Vertical wall as a button.
 */

class IntersectionButton extends JButton{

	public IntersectionButton( int BUTTON_SIZE ){
		super();
		this.setPreferredSize( new Dimension( BUTTON_SIZE, BUTTON_SIZE ));
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		this.setBackground( Color.BLACK );
	}
}
