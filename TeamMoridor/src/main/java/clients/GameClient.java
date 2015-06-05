package main.java.clients;

import java.awt.*;
import java.lang.System;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import main.java.Board;
import main.java.Space;
import main.java.UI.GameBoard;
import main.java.Player;
import java.util.NoSuchElementException;

// looping client Prof. Ladd gave us for networking that i'm recoding/gutting to be our client!
/**
 * This is a first client program. It demonstrates how to create a client socket and how to read and
 * write information to it. This program determines what machine and port to connect to by reading
 * the command-line. This is the model that will be followed throughout this semester.
 */
public class GameClient {

  private String machineName[];
  private int ports[];
  private int numPlay;
  private int startNumPlay;
  private Socket players[];
  private PrintStream sout[];
  private Scanner sin[];
  private String playerNames[];
  private Board board;
  private GameBoard gui;
  public int currplayer;

  /**
   * Constructor for the Display client
   * 
   * @param machineName The names for all the machines connecting to the client
   * @param ports The ports to listen to for each of the players
   * @param numPlay The number of players
   */
  public GameClient(String machineName[], int ports[], int numPlay) {
    this.startNumPlay = numPlay;
    this.numPlay = numPlay;
    this.machineName = machineName;
    this.ports = ports;
    this.gui = new GameBoard(startNumPlay, "Client");
  }


  /**
   * Collects information regarding the number of players
   * 
   * @param args Array in the form machineName:port (the number of machines given represent the
   *        total number of players)
   */
  public static void main(String[] args) {
    if (args.length <= 1 || args.length == 3 || args.length > 4) {
      // should only accept 2 or 4 players
      System.err.println("Invalid amount of players(2 or 4)");
      usage();
      System.exit(1);
    }
    int argNdx = 0;

    // to make sockets later on
    String[] machineNames = new String[args.length];
    int[] ports = new int[args.length];

    // better reference in all methods
    int numPlay = args.length;

    // notice args.length is number of players
    while (argNdx < args.length) {
      String curr = args[argNdx];

      // store all the players ports and machine names
      // args should be of the form machineName:port
      String serverInfo[] = curr.split(":");
      if (serverInfo.length == 2) {
        machineNames[argNdx] = serverInfo[0];
        ports[argNdx] = Integer.parseInt(serverInfo[1]);
      } else {
        System.err.println("invalid format, correct example localhost:9090");
        System.exit(1);
      }
      ++argNdx;
    }
    GameClient fc = new GameClient(machineNames, ports, args.length);
    fc.run();
  }

  /**
   * Client program opens a socket to the server machine:port pair. It then sends the message
   * "Hello", reads the line the server is expected to respond with, and then sends "Goodbye". After
   * sending the final message it closes the socket stream.
   */
  public void run() {
    this.players = new Socket[startNumPlay];
    this.sout = new PrintStream[startNumPlay];
    this.sin = new Scanner[startNumPlay];
    this.playerNames = new String[startNumPlay];
    System.out.println("board starts with:" + startNumPlay + "players");
    this.board = new Board(startNumPlay);

    try {
      // Creates Move-Server sockets and their respective printstream and scanner for communication
      // reference for the list of all communication 'links'

      // PrintStream sout = new PrintStream(socket.getOutputStream());
      // Scanner sin = new Scanner(socket.getInputStream());

      // Note, it might be more efficient if everything was on a stack in groups of three
      // but i know array better then stack so i'll stick with my strong suit.
      for (int i = 0; i < this.numPlay; i++) {
        players[i] = new Socket(machineName[i], ports[i]);
        System.out.println(machineName[i] + ports[i]);
        sout[i] = new PrintStream(players[i].getOutputStream());
        sin[i] = new Scanner(players[i].getInputStream());
        // sout[i].println("MOVER_SERVER");
        String name = sin[i].nextLine();
        System.out.println("name:" + name);
        String[] names = name.split(" ");
        playerNames[i] = names[1];
      }
      System.out.println("names added");

      int turn[] = new int[startNumPlay];
      if (startNumPlay == 2) {
        turn[0] = 0;
        turn[1] = 1;
      } else {
        turn[0] = 0;
        turn[1] = 3;
        turn[2] = 1;
        turn[3] = 2;
      }
      int currTurn = 0;

      namesList(playerNames);
      System.out.println("turn set");
      boolean victor = false;

      // Players will not break until all players are ready, thus once all are ready we may play
      // boolean ready = players(startNumPlay,sout,sin);
      players(startNumPlay, sout, sin);
      System.out.println("we're ready");
      while (numPlay > 1) {
        boolean legalMove = true;
        currplayer = turn[currTurn % startNumPlay];

        // Sets current player for board
        // Matt needs to check this.
        System.out.println(currplayer);
        board.setCurrentPlayer(currplayer);
        gui.setCurrentPlayer(currplayer);

        if (!machineName[currplayer].equals("null")) {

          // To notify move server to accept a move
          sout[currplayer].println("GO?");

          // receives move from that player
          String moveString = sin[currplayer].nextLine();
          // check to see if legal move
          System.out.println(moveString);
          String moveInfo[] = moveString.split(" ");
          // remove GO from movestring
          moveString = moveInfo[1];
          System.out.println(moveString);
          legalMove = isLegal(moveString);
          System.out.println("Is move legal?" + legalMove);
          if (legalMove) {
            // Victor?
            // GO + " " + moveStirng
            // moveString:
            // 1-1
            // (1-1,1-2)
            System.out.println(moveInfo.length);
            if (!moveString.contains(",")) {
              try {
                String cords = moveString;
                System.out.println("Making Move:" + moveString);
                Space potentialPosition = board.StringtoCoordinates(cords);
                board.makeMove(board.currentPlayer(), potentialPosition);
                // Update the GUI's Board Logically
                gui.makeMove(gui.currentPlayer(), potentialPosition);
              } catch (NoSuchElementException nsee) {
                nsee.printStackTrace();
                System.exit(1);
              }
            } else {
              String wallParts[] = moveString.split(",");
              String cords = wallParts[0];
              String cords2 = wallParts[1];
              cords = cords.replace("(", "");
              cords2 = cords2.replace(")", "");
              System.out.println(cords2);
              Space potentialPosition = board.StringtoCoordinates(cords);
              Space potentialPosition2 = board.StringtoCoordinates(cords2);
              try {
                board.placeWall(potentialPosition, potentialPosition2);
                gui.placeWall(potentialPosition, potentialPosition2);
              } catch (NoSuchElementException nsee) {
                nsee.printStackTrace();
                System.exit(1);
              } catch (IllegalArgumentException e) {
                dasBoot(playerNames[currplayer], sout, sin);
                machineName[currplayer] = "null";
                numPlay--;
              }
            }
            // Update the GUI's Board Visually
            System.out.println("gui update now!");
            gui.updatePositions();
            // Check if the current player has won the game after their move.
            victor = board.isWinner(board.currentPlayer());
            if (numPlay == 1) {
              for (int i = 0; i < machineName.length; i++) {
                if (machineName[i] != "null") {
                  winnerIs(playerNames[i], sout, sin);
                }
              }
            } else if (victor) {
              winnerIs(playerNames[currplayer], sout, sin);
            } else {
              // If no then went
              // Will be a landing cor
              // Walls (start , end)
              went(playerNames[currplayer], moveString, sout);
            }
          } else {
            // You are a cheater
            dasBoot(playerNames[currplayer], sout, sin);
            machineName[currplayer] = "null";
            numPlay--;
          }
        }
        // This player was kicked or this player is done with their turn
        currTurn++;
      }
      if (numPlay == 1) {
        // Player won because everyone else was a cheater
        int i = 0;
        while (machineName[i].equals("null")) {
          i++;
        }
        winnerIs(playerNames[i], sout, sin);
      }
    } catch (UnknownHostException uhe) {

      // the host name provided could not be resolved
      uhe.printStackTrace();
      System.exit(1);
    } catch (IOException ioe) {

      // there was a standard input/output error (lower-level)
      ioe.printStackTrace();
      System.exit(1);
    } catch (IndexOutOfBoundsException iobe) {
      iobe.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Print the usage message for the program on standard error stream.
   */
  private static void usage() {
    System.err
        .format("usage: java Client required (machineName:port) 2 times for 2 players 4 for 4 players");
  }

  /**
   * Sends a line of players that will be playing also signals that the player is ready to play
   * 
   * @param startNumPlay The starting number of Players
   * @param sout The output stream
   * @param sin The input stream
   * @return
   */
  private void players(int startNumPlay, PrintStream sout[], Scanner sin[]) {
    String players = "PLAYERS ";

    for (int i = 0; i < startNumPlay; i++) {
      players += playerNames[i] + " ";
    }
    int n = 0;
    for (int i = 0; i < startNumPlay; i++) {
      String currentName = playerNames[i];
      System.out.println("sending " + players + " to : " + currentName);
      PrintStream currSout = sout[i];
      Scanner currSin = sin[i];
      currSout.println(players);
      String confirm = sin[i].nextLine();
    }
  }

  /**
   * Ends game by sending to each player who the winner is then disconnects and shuts down
   * 
   * @param winner The winner of the game
   * @param sout The output stream
   * @param sin The input stream
   */
  private void winnerIs(String winner, PrintStream sout[], Scanner sin[]) {
    System.out.println("winner:" + winner);
    int i = 0;
    while (i < startNumPlay) {
      if (!playerNames[i].equals("null")) {
        sout[i].println("VICTOR " + winner);
        sout[i].close();
        sin[i].close();
      }
      i++;
    }
    System.exit(0);
  }

  /**
   * Lets everyone know to update their board and GUI and update our board and GUI if the move given
   * is legal.
   * 
   * @param player
   * @param moveString The position the player moved to
   * @param sout The output stream
   */
  private void went(String player, String moveString, PrintStream sout[]) {
    for (int i = 0; i < startNumPlay; i++) {
      if (!playerNames[i].equals("null")) {
        sout[i].println("WENT " + player + " " + moveString);
      }
    }
  }

  /**
   * Passes to board to check legality of move
   * 
   * @param moveString The formal coordinates that the current player is going to move to.
   * @return True if the move is legal; False otherwise
   */
  public boolean isLegal(String moveString) {
    boolean islegal = false;
    System.out.println("Checking: " + moveString);
    String moveInfo[] = moveString.split(",");
    // cord1
    if (moveInfo.length == 1) {
      String cords = moveInfo[0];
      cords = cords.replace("(", "");
      cords = cords.replace(")", "");
      System.out.println(cords);
      Space potentialPosition = board.StringtoCoordinates(cords);
      islegal = board.isLegalMove(board.currentPlayer(), potentialPosition);
    }
    // cord1 cord2 (wall)
    if (moveInfo.length == 2) {
      String cords = moveInfo[0];
      String cords2 = moveInfo[1];
      cords = cords.replace("(", "");
      cords2 = cords.replace(")", "");
      Space potentialPosition = board.StringtoCoordinates(cords);
      Space potentialPostion2 = board.StringtoCoordinates(cords);
      islegal = board.canPlaceWall(potentialPosition, potentialPostion2);
    }
    return islegal;
  }

  /**
   * Kicks player
   * 
   * @param cheater The name of the player getting booted
   * @param sout The output stream
   * @param sin The print stream
   */
  public void dasBoot(String cheater, PrintStream sout[], Scanner sin[]) {
    for (int i = 0; i < machineName.length; i++) {
      // tells who the dirty cheater is
      sout[i].println("BOOT " + cheater);
      // oh wait its you, time for dasBoot
      if (playerNames[i].equals(cheater)) {
        sout[i].close();
        sin[i].close();
      }
    }
    board.bootPlayer(board.currentPlayer());
    gui.bootPlayer(gui.currentPlayer());
  }

  // sets names list
  public void namesList(String playerNames[]) {
    this.playerNames = playerNames;
  }
}
