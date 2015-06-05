package main.java;

/**
 * Interface for controlling the flow of the game--anything dealing with moves and player turns.
 * 
 * @author Matthew Coupal
 *
 */

public interface MasterInterface {

  public void makeMove(Player player, Space potentialPosition) throws Exception;

  public void placeWall(Space starting1, Space starting2) throws Exception;

  public void bootPlayer(Player player);

  public void setCurrentPlayer(int playerNumber) throws Exception;

  public Player currentPlayer();
}
