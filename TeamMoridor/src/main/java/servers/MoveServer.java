package servers;
import java.io.IOException;
import java.io.PrintStream;

import java.lang.System;
import java.net.ServerSocket;
import java.net.Socket;
import main.java.Board;
import main.java.Space;
import java.util.Scanner;
import UI.BoardGrid;
import main.java.Player;

/**
 * This server starts with default name, machinename and port
 * needs to handle
 * MOVE -Message returned by the move-server to a PLAYERS message. Indicates
 *       that it is ready to play.
 *
 *GO <move-string>- Message returned by a move-server after it is queried for a
 *                  move. The move-string is encoded as described in coordinates
 *                  above.
 *needs to accept
 *Victor- prints winner to screan then exits
 *
 *BOOT <player-id> - Message sent by game-client to all move-servers informing them
 *                   that <player-id> made an illegal move and is no longer in the
 *                   game. The pawn for <player-id> should be removed from the game
 *                   board and any remaining walls are lost. Note: sent as the very
 *                   last message to the offending move-server.
 *
 *
 */

/**
 * A simple, single-threaded echo server with sequential servicing of
 * clients. That is, when a client connects, it is serviced to
 * completion before another client connection can be accepted. The
 * server cannot be stopped gracefully: use Ctrl-C to break the
 * running program in a terminal.
 *
 * "Echo server" means that it listens for a connection (on a
 * user-specifiable port), reads in the input on the wire, modifies
 * the information, and writes the modified information back to the
 * client, echoing the input it was given.
 */
public class MoveServer {


    //commands on startUp
    public final static String ARG_NAME ="--player";
    public final static String ARG_PORT = "--port";
    public final static String ARG_MACHINE = "--machine";

    public static String DEFAULT_PLAYER_NAME = "POPO"  ;
    public static String DEFAULT_MACHINE_NAME = "localhost";
    public static int port = 9090;

    public final static String MY_TURN ="GO?";
    public String[] opponents;
    public String opponent;
    public int playNum;
    public int numPlayers;
    public Board board;
  /**
   * Creates a new <code>TCPServer</code> instance. TCPServer is
   * a listening echo server (it responds with a slightly modified
   * version of the same message it was sent).
   *
   * @param portNumber required port number where the server will
   * listen.
   */
  public MoveServer(int portNumber, String machineName, String playerName ) {
      this.port = portNumber;
      this.DEFAULT_MACHINE_NAME = machineName;
      this.DEFAULT_PLAYER_NAME = playerName;
  }

  /**
   * Processes the command-line parameters and then create and run
   * the TCPServer object.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
      int argNdx = 0;

      while(argNdx < args.length) {
          String curr = args[argNdx];
          //seperates args into the repective command
          if (curr.equals(ARG_MACHINE)){
              argNdx++;
              DEFAULT_MACHINE_NAME = args[argNdx];
          }else {
              if (curr.equals(ARG_PORT)){
                  argNdx++;
                  port = Integer.parseInt(args[argNdx]);
              }else{
                  if (curr.equals(ARG_NAME)) {
                      argNdx++;
                      DEFAULT_PLAYER_NAME = args[argNdx];
                  }
              }
          }
          argNdx++;
      }


    // create a server object and run it
    MoveServer s = new MoveServer(port,DEFAULT_MACHINE_NAME,DEFAULT_PLAYER_NAME);
    s.run();
  }

  /**
   * Primary method of the server: Opens a listening socket on the
   * given port number (specified when the object was
   * constructed). It then loops forever, accepting connections from
   * clients.
   *
   * When a client connects, it is assumed to be sending messages, one per line. The server will process
   */
  public void run() {
    System.out.println("TCPServer begins " + port);

    try {
      ServerSocket server = new ServerSocket(port);
        System.out.println(DEFAULT_MACHINE_NAME + "Accepting connections on " + port);

      Socket gameClient;
      //will create a listening port first to grab gameserver commands.
      while ((gameClient = server.accept()) != null) {
         //MoveServer();
        Scanner cin = new Scanner(gameClient.getInputStream());
        PrintStream cout = new PrintStream(gameClient.getOutputStream());

        String clientMessage;
          //where commands will be desided
        while (cin.hasNextLine()) {
			
          clientMessage = cin.nextLine();
            //pass name
            if(clientMessage.equals("Move")){
                cout.printf("%s\n", DEFAULT_PLAYER_NAME);
            }else{
                //Your turn
                if(clientMessage.equals("GO?")){
                   String move= MyMove(cin);
                    cout.printf("%s\n", move);
                }else{
                    //ready?
                    if (clientMessage.startsWith("Players")){
						playermsg(clientMessage,cout);
                    }else {
                        //someone booted
                        if(clientMessage.startsWith("Boot")){
                            weGotACheater(clientMessage, cout,cin);
                            //we have aa winner
                        }else {
                            if (clientMessage.startsWith("Winner")) {
                                System.out.println(clientMessage);
                                System.exit(0);
                            }else {
                                if (clientMessage.startsWith("Went")) {
                                    aPlayerWent(clientMessage);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Server closing connection from " + gameClient);
        //cout.close();
        cin.close();
      }
    } catch (IOException ioe) {

      // there was a standard input/output error (lower-level from uhe)
      ioe.printStackTrace();
      System.exit(1);
    }

    System.out.println("TCPServer terminates.");
  }

 public void playermsg(String clientMessage, PrintStream cout){
     // Players player1name player2name ... etc
    System.out.println(clientMessage);
     String msg[] = clientMessage.split(" ");
     int numPlayers = msg.length ;
     String opponents[] = new String[msg.length ];
         //players is slot 1 in msg
         for (int i = 1; i < msg.length; i++){
             opponents[i]=msg[i];
            if(msg[i].equals(DEFAULT_PLAYER_NAME)){
                playNum = i-1;
            }
         }

     addOpponents(opponents, (playNum -1));
	 cout.printf("%s\n", DEFAULT_PLAYER_NAME);
 }

 public static String MyMove(Scanner cin){
    String movesString = "";
     //grabs  move from board

    return DEFAULT_PLAYER_NAME + " "+ movesString;
 }

 public void addOpponents(String opponents[], int playNum){
     this.opponents = opponents;
     this.numPlayers = opponents.length;
     this.playNum = playNum;
     this.board = new Board(numPlayers);
 }

 public void  weGotACheater(String clientMessage,PrintStream sout, Scanner sin) {
     //if we have more then one opponent then do this
     System.out.println(clientMessage);
     String cheater[] = clientMessage.split(" ");
     //client message it "Cheater player"
     System.out.println("kicking"+cheater[1]);
     if (cheater[1].equals(DEFAULT_PLAYER_NAME)){
         System.out.println("you're a CHEATER");
         sin.close();
         sout.close();
         System.exit(0);
     }
     if(playNum > 1) {
         for (int i = 0; i < numPlayers; i++) {
             if (cheater[i].equals(opponents)) {
                opponents[i] = "Booted";
                playNum--;
             }
         }
     }else{
         opponent = "Booted";
     }


 }

 public void aPlayerWent(String moveString){
     //update board
	//note that all players are in oppenents it was a bad choice for a name
     int i;
     System.out.println(moveString);
    String moveInfo[] = moveString.split(" ");
    for(i=0; i< opponents.length;i++){
        if(moveInfo[0].equals(opponents[i]))
            break;
    }
    String cords = moveInfo[1].replace("(", "");
    cords = cords.replace(")","");
    Space potentialPosition = board.StringtoCoordinates(cords);

     board.setCurrentPlayer(i);

     board.makeMove(board.currentPlayer(),potentialPosition);
    //update gui code

 }
}
