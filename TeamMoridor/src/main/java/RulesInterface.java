package main.java;

/**
 * Interface that deals with the various rules of the game.
 * @author Matthew Coupal
 *
 */
public interface RulesInterface {

	public boolean isOutOfBounds(Space potentialPosition);
	
	public boolean isMoveLegalDiagonal(Space currentPosition, Space potentialPosition);
	
	public boolean isPlayerHere(Space potentialPosition);
	
	public boolean canReachEnd(Player player);
	
	public boolean canPlaceWall(Space startingSpace1, Space startingSpace2);
	
	public boolean isWallHere(Space startingSpace1, Space startingSpace2);
	
	public boolean isDoubleJumpLegal(Player player, Space potentialPosition);
	
	public boolean isLegalMove(Player currentPlayer, Space space);
	
	public boolean isLegalSingleMove(Player currentPlayer, Space potentialPosition);
	
	public boolean isWinner(Player player);
}
