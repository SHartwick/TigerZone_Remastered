![](http://i68.tinypic.com/2yyz9u9.jpg)
#Welcome to the TigerZone_Remastered wiki!

##Team Members
__(FirstName LastName -> GitHub Username)__
* Edward Wu -> aznxed
* Spencer Hartwick -> SHartwick
* Ryan Burroughs -> rburrou
* Marisa Fernandez -> marisagfernandez
* Skyler Burgoyne -> smburgoyne
* Luc Olsthoorn -> Luc-Olsthoorn

##Architecture/Design
__"...architecture is the decisions that you wish you could get right early in a project" - Martin Fowler__

###UML Activity Diagram
The following is a UML activity diagram that was developed prior to writing code.
![](http://i66.tinypic.com/v3dk3s.png) 

###UML Structural Diagram
The following is a UML structure diagram that was developed prior to writing code.
![](http://i63.tinypic.com/34qv11v.png)

The entire application is written in Java. 

The game was built using behavior-driven design principles. Functions and methods were written with ubiquitous language. For example, Deck.getTop() gets the top tile in the deck. Deck.isEmpty() checks if the deck is empty. Variables are assigned as they would be while playing an actual game. For example, while holding a tile, it does not have a coordinate or rotation, we can only see what's on the tile. Only when we place the tile on the board, does the coordinate and rotation become significant.

The UI was developed in it's own class and is independent of the game. According to Robert Martin, a good architecture hangs the UI off application like an appendix. So, that it can be removed if it doesn't work. 


The TCP client/server

The tcp code was designed to be modular enough to allow for seperate client and server executables as well as a protocolTest which can be used to simulate a tigerzone game on a single machine. tcpClient is the main file used to connect to the tournament server. It accepts the following arguments: <host> <port> <serverPassword> <PlayerID> <PlayerPassword>. tcpServer can be used as a fake server with args <port>. protocolTest can be used without arguments to simulate messages sent and received from the server.


##How to Build the client
//Spencer, do you mind?

##How to Run the Client
//Spencer, do you mind?

##Unit and Acceptance Tests
__"You could test all your business rules, all your presenters...without the application server, the web running, anything running, at 30,000 feet, while sipping a gin and tonic" - Robert C. Martin__

For unit testing, we decided to use JUnit. JUnit is a unit testing framework for Java programming language. Our Junit test file can be found under src/Game/Junit.java. We have set up the tests so they run in alphabetical order. 
 
* TestA: tests that a tile is being placed on the board
* TestB: tests tiles that are placed on a board can be retrieved
* TestC: tests all tiles successfully placed on the board are being placed into the placedTiles list
* TestD: tests that the isValid method is returning true for a valid move and false for an invalid move
* TestE: tests that the getNeighbors method returns the correct number of neighbors for a tile
* TestF: tests that the rotateTile method is properly rotating tiles
* TestG: tests that the getValidOrients and getPossibleMoves methods are returning the correct number of valid orientations and possible moves for a tile respectively
 
These unit tests are testing the core game logic that our AI depends on to make valid moves.  

##How to Run Tests
To run individual tests, simply highlight the method and run it as a Junit test.

##Difficulties and Challenges

# Process
## User Stories
As a player, I want to play against an AI 
As a player, I want to place a tile with/without a rotation on the board so that I can make a move.  
As a player, I want to check the score and number of meeples so that I can see who’s winning.
As a player, I want to see the remaining tiles so that I can calculate my odds.
As a player, I want to see the valid board placements for a tile so that I can make my move easier. 
As a server, I want to send tiles to the players.
As a server, I want to receive moves from the players. 
As a server, I want to check valid placement of tiles.
As a server, I want to score completed tile chains. 
As a server, I want to declare a victor for the game. 
## Sprints
![](http://i66.tinypic.com/23pm5u.png)
![](http://i65.tinypic.com/33jmydd.png)
![](http://i64.tinypic.com/2uxwco8.png)
![](http://i67.tinypic.com/24v04xz.png)
![](http://i67.tinypic.com/33kdh5l.png)

