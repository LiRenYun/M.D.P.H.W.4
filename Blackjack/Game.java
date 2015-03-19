import java.util.*;

class Game
{
	ArrayList<Player> players;
	Dealer dealer;
	
	public Game(int playerCount) {
		players = new ArrayList<Player>();
		for (int i = 0; i < playerCount; i++) {
			players.add(new Player(""));
		}
		dealer = new Dealer("");
	}

}