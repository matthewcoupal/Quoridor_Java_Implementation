package main.java;

public interface MasterInterface {

	public void makeMove(Player player, Space potentialPosition);
	
	public void placeWall(Player player, Space starting1, Space starting2, Space ending1, Space ending2);
	
	public void bootPlayer(Player player);
	
	public void setCurrentPlayer(Player player);
}
