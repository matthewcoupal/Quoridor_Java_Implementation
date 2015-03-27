
package main.java;
/**
 * The artificial intelligence working to beat its opponents. Extends board rather than player since the methods of the board are much more valuable.
 * @author Matthew Coupal
 *
 */
public class AI extends Board {
	/**
	 * Generates the board with the default player number of 2.
	 */
	public AI() {
		super(2);
	}

	/**
	 * Generates the board with the specified number of players.
	 * @param numberOfPlayers
	 */
	public AI(int numberOfPlayers) {
		super(numberOfPlayers);
	}

	/**
	 * Calculates the average number of walls every player has on hand.
	 * @return The average of walls left on the board (includes the AI itself)
	 */
	public int averageWallCount() {
		int Walls = 0;
		for(int i = 0; i < this.occupiedSpaces.size(); i++) {
			Walls = Walls + this.occupiedSpaces.get(i).getWalls();
		}
		return Walls/this.occupiedSpaces.size();
	}
}
