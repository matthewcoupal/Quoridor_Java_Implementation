1. Go to the directory holding "TeamMoridor.jar" in the terminal.

2. Open 3-5 terminals in the same directory as step 1.

3. In the first terminal type, "java -cp 
TeamMoridor.jar main.java.servers.MoveServer"

4. In the second terminal type, "java -cp TeamMoridor.jar main.java.servers.MoveServer --player Ladd --port 9091"

//4a. In the third terminal type, "java -cp TeamMoridor.jar main.java.servers.MoveServer --player Pancake --port 9092"

//4b. In the fourth terminal type, "java -cp TeamMoridor.jar main.java.servers.MoveServer --player Joe --port 9093"

If playing a two player game:
 5. In the third terminal type, "java -cp 
 TeamMoridor.jar main.java.clients.GameClient localhost:9090 localhost:9091"

Else, if playing a four player game:
 5. In the fifth terminal type, "java -cp TeamMoridor.jar main.java.clients.GameClient localhost:9090 localhost:9091 localhost:9092 localhost:9093"

THINGS TO NOTE:

* The game might hang due to running four players on one machine; we believe this is due to the number of buttons listening at one time. We will try to get this issue resolved.

* DO NOT TOUCH THE THIRD GUI (CLIENT SCREEN)!

* Walls are implemented in this release, but with a few bugs.

* The Blue player begins first.

* Four players are supported in this version.

* BUG: Making a T surrounding a player with cause the player placing the wall to be kicked.

* Diagonal jumping is still unsupported.
