package clients;

import java.awt.*;
import java.lang.System;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import main.java.Board;
import main.java.Space;
import main.java.UI.BoardGrid;
import main.java.Player;
import java.util.NoSuchElementException;
//looping client Prof. Ladd gave us for networking that i'm recoding/gutting to be our client!
/**
 * This is a first client program. It demonstrates how to create a
 * client socket and how to read and write information to it. This
 * program determines what machine and port to connect to by reading
 * the command-line. This is the model that will be followed
 * throughout this semester.
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
    private BoardGrid gui;

   public GameClient(String machineName[], int ports[],int  numPlay) {
      this.startNumPlay = numPlay;
      this.numPlay = numPlay;
      this.machineName = machineName;
      this.ports = ports;
      this.gui = new BoardGrid(9,9);
   }


  public static void main(String[] args) {
    if (args.length <= 1 || args.length == 3 || args.length > 4){
        //should only accept 2 or 4 players
        System.err.println("Invalid amount of players(2 or 4)");
        usage();
        System.exit(1);
    }
    int argNdx = 0;
    //  to make sockets later on
    String[] machineNames = new String[args.length];
    int[] ports = new int[args.length];
    // better reference in all methods
    int numPlay = args.length;
    //notice args.length is number of players
    while (argNdx < args.length) {
        String curr = args[argNdx];
      //store all the players ports and machine names
      // args should be of the form  machineName:port
      String serverInfo[] = curr.split(":");
      if (serverInfo.length == 2){
          System.out.println(serverInfo[0]);
          machineNames[argNdx] = serverInfo[0];
          System.out.println(serverInfo[1]);
          ports[argNdx] = Integer.parseInt(serverInfo[1]);
      }else{
          if (serverInfo.length == 4){
              System.out.println(serverInfo[0]);
              machineNames[argNdx] = serverInfo[0];
              System.out.println(serverInfo[1]);
              ports[argNdx] = Integer.parseInt(serverInfo[1]);
              System.out.println(serverInfo[2]);
              machineNames[argNdx] = serverInfo[2];
              System.out.println(serverInfo[3]);
              ports[argNdx] = Integer.parseInt(serverInfo[3]);
          }else {
              // if there is an unknown parameter, give usage and quit
              // not doing it right
              System.err.println("Unknown parameter \"" + curr + "\"");
              usage();
              System.exit(1);
          }
      }
      ++argNdx;
    }
    GameClient fc = new GameClient(machineNames, ports, args.length);
    fc.run();
  }

  /**
   * Client program opens a socket to the server machine:port
   * pair. It then sends the message "Hello", reads the line the
   * server is expected to respond with, and then sends
   * "Goodbye". After sending the final message it closes the socket
   * stream.
   */
  public void run() {
    this.players = new Socket[startNumPlay];
    this.sout = new PrintStream[startNumPlay];
    this.sin = new Scanner[startNumPlay];
	this.playerNames = new String[startNumPlay];
    this.board = new Board(startNumPlay);
    try {
      //creates Move-Server sockets and their respective printstream and scanner for communitcation
        // reference for the list of all commnication 'links'
        //PrintStream sout = new PrintStream(socket.getOutputStream());
        //Scanner sin = new Scanner(socket.getInputStream());
        // note it Might be more effiecent if everything was on a stack in groups of three
        // but i know array better then  stack so i'll stick with my strong suit.
        for (int i =0; i < this.numPlay; i++) {
          players[i] = new Socket(machineName[i], ports[i]);
          System.out.println(machineName[i]+ports[i]);
          sout[i]= new PrintStream(players[i].getOutputStream());
          sin[i] = new Scanner(players[i].getInputStream());
          sout[i].println("Move");
          String name = sin[i].nextLine();
		  System.out.println("name:" + name);
		  playerNames[i] = name;
          }
		System.out.println("names added");

        int turn[]= new int[startNumPlay];
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

        //players will not break until all players are ready thus once all are ready we may play
        boolean ready = players(startNumPlay,sout,sin);
		System.out.println("we're ready");
        while(numPlay > 1 && ready){
            boolean legalMove = true;
            //cause !machineName[turn[currTurn % this.startNumPlay]].equal("null") is just messy
            int currplayer = turn[currTurn % startNumPlay];

            //sets curr player for board
            //mat needs to check this
            board.setCurrentPlayer(currplayer);


            if (!machineName[currplayer].equals("null")){
                  //to notify moverserver to accept a move
                  sout[currplayer].println("GO?");
                  //receives movde from that player
                  String moveString = sin[currplayer].nextLine();
                  //check to see if legal move
                  legalMove = isLegal(moveString);
                System.out.println(legalMove);
                if(legalMove){
                      //victor?
                      victor = board.isWinner(board.currentPlayer());
                      if (victor){
                          winnerIs(playerNames[currplayer],sout,sin);
                      }else {
                          //if no then went
                          //will be a landing cor
                          //walls (start  , end)
                          went(playerNames[currplayer], moveString,sout);
                      }
                  }else{
                      // your a cheater
                      dasBoot(playerNames[currplayer],sout, sin);
                      machineName[currplayer] = "null";
                      numPlay--;
                  }
              }
              //This was player kicked or this player is done with their turn
              currTurn++;
        }
        if (numPlay == 1){
            //player won cause everyone else was a cheater
            int i =0;
            while(machineName[i].equals("null")){
                i++;
            }
            winnerIs(playerNames[i],sout, sin);
        }
    } catch (UnknownHostException uhe) {

      // the host name provided could not be resolved
      uhe.printStackTrace();
      System.exit(1);
    } catch (IOException ioe) {

      // there was a standard input/output error (lower-level)
      ioe.printStackTrace();
      System.exit(1);
    }catch(IndexOutOfBoundsException iobe){
        iobe.printStackTrace();
        System.exit(1);
    }
  }

   //Print the usage message for the program on standard error stream.
    private static void usage() {
    System.err.format("usage: java Client required (machineName:port) 2 times for 2 players 4 for 4 players");
    }

    //sends a line of players that will be playing also singnals that the player is ready to play
    private boolean players(int startNumPlay, PrintStream sout[], Scanner sin[]) {
      String players = "Players ";

      for (int i = 0; i < startNumPlay; i++){
        players += playerNames[i] + " ";
      }
      int n = 0;
      while(n < startNumPlay) {
              String currentName = playerNames[n];
              PrintStream currSout = sout[n];
              Scanner currSin = sin[n];
              currSout.println(players);
              String serverResponse = currSin.nextLine();
              if(serverResponse.equals(currentName)){
                  n++;
              }
          }
      return true;
  }

    //ends game  by sending to each player who the winner is then disconnects and shuts down
    private void winnerIs(String winner, PrintStream sout[], Scanner sin[]){
       System.out.println("winner:" + winner);
      int i = 0;
      while(i < startNumPlay){
            if(!playerNames[i].equals("null")){
                sout[i].println("Winner is:" + winner);
                sout[i].close();
                sin[i].close();
            }
          i++;
      }
      System.exit(0);
  }

    //this move is legal let everyone know to update thheir boardand gui and update our board and gui
    private void went(String player, String moveString ,PrintStream sout[]){
      int i = 0;
      while(i < numPlay){
          //
          if(!playerNames[i].equals("null")){
            sout[i].println("Went" + moveString);
          }
          i++;
      }
      //update client board code here
      String moveInfo[] = moveString.split(" ");
      String cords = moveInfo[1].replace("(", "");
      cords = cords.replace(")","");
      Space potentialPosition = board.StringtoCoordinates(cords);
      try{
        board.makeMove(board.currentPlayer(),potentialPosition);
      //update gui code

      }catch (NoSuchElementException nsee){
          nsee.printStackTrace();
          System.exit(1);
      }

    }

    //passes to board to check legallity of move
    public boolean isLegal(String moveString){
     //something something, not my(dale's) task =P
     //board.isLegal(moveString);
     // playerName (X-Y)
     if(moveString.contains("-")){String moveInfo[] = moveString.split(" ");
        String cords = moveInfo[1].replace("(", "");
        cords = cords.replace(")","");
        Space potentialPosition = board.StringtoCoordinates(cords);
        boolean islegal = board.isLegalMove(board.currentPlayer(), potentialPosition);
        return islegal;
     }else{
             //no cords so invalid
             return false;
    }
 }

   //kicks player
   public void dasBoot(String cheater,PrintStream sout[], Scanner sin[]){
       for(int i = 0;i < machineName.length; i++){
         //tells who the dirty cheater is
         sout[i].println("Boot " + cheater);
         //oh wait its you, time for dasBoot
         if(playerNames[i].equals(cheater)){
            sout[i].close();
            sin[i].close();
         }
     }
     board.bootPlayer(board.currentPlayer());
 }

   //sets names list
   public void namesList(String playerNames[]){
     this.playerNames = playerNames;
 }
}








