package main.java;

public interface RulesInterface {

	public boolean isOutOfBounds(Space potentialPosition);
	
	public boolean isMoveDiagonal(Space currentPosition, Space potentialPosition);
	
	public boolean isMoveLegalDiagonal(Space currentPosition, Space potentialPosition);
	
	public boolean isPlayerHere(Space potentialPosition);
	
	public boolean canReachEnd(Player player);
	
	public boolean canPlaceWall(Space startingSpace1, Space startingSpace2, Space endingSpace3, Space endingSpace4);
	
	public boolean isWallHere(Space startingSpace1, Space startingSpace2);
	
	public boolean isLegalDoubleMove(Space currentPosition, Space potentialPosition);
	
	public boolean isLegalMove(Space currentPosition, Space potentialPosition);
	
	public boolean isWinner(Player player);
}
