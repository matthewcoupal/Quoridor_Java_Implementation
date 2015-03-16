1. Go to the directory holding "TeamMoridor.jar" in the terminal.

2. Open 3 terminals in the same directory as step 1.

3. In the first terminal type, "java -cp 
TeamMoridor.jar main.java.servers.MoveServer"

4. In the second terminal type, "java -cp TeamMoridor.jar main.java.servers.MoveServer --player Ladd --port 9091"

5. In the third terminal type, "java -cp 
TeamMoridor.jar main.java.clients.GameClient localhost:9090 localhost:9091"

THINGS TO NOTE:

* The GUIs will appear on top of one another The first GUI is player two (RED) 
and the second GUI is player one (BLUE).

* DO NOT TOUCH THE THIRD GUI (CLIENT SCREEN)!

* Walls are not implemented in this release.

* The Blue player begins first.

* Only two players are supported in this version.

* BUG: Players can be on the same space

* Diagonal jumping disabled until walls are implemented.
