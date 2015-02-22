package main.java;

public interface MasterInterface {

	public void makeMove(Player player, Space potentialPosition) throws Exception;
	
	public void placeWall(Player player, Space starting1, Space starting2, Space ending1, Space ending2) throws Exception;
	
	public void bootPlayer(Player player);
	
	public void setCurrentPlayer(Player player);
}
