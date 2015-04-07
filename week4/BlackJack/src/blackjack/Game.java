package blackjack;
import java.lang.*;
import static java.lang.System.*;
import java.util.*;
public class Game {
    Deck deck;
    String []Status = {"Nothing","BlackJack","Busted","Winner","Loser"};
    ArrayList<Player> players;
    Dealer dealer;
    int playerCount = 0;
    public Game(int playerCount) {
        deck = new Deck(1);
        deck.Shuffle();
        this.playerCount = playerCount;
        String playerName;
        Scanner enter = new Scanner(in);
        players = new ArrayList<Player>();
        out.print("Player name:");
        playerName = enter.next();
        players.add(new Player(playerName));
        for (int i = 1; i < playerCount; i++) {
            players.add(new Player("Computer"+i));
        }
        dealer = new Dealer("Vin Diesel");
    }
    public void showAllMember(){
        out.println("Dealer:");
        out.println(dealer.name);
        out.println("Player:");
        for(int i = 0;i<playerCount;i++){
            out.println(players.get(i).name);
        }
    }
    public void Start(){
        while(true)
        {
            for(int i = 0;i<2;i++){//start
                players.get(0).hit(deck.getcard());
                dealer.hit(deck.getcard());
            }
            out.println("Player("+dealer.name+"): Status:"+Status[dealer.calculatePoint()] +"\t------- ---"+dealer.cards.get(1).suit+" "+dealer.cards.get(1).face);
            playerShowStatus(0);
            break;
        }
    }
    public void playerShowStatus(int index){
        out.printf("Player("+players.get(index).name+"): Status:"+Status[players.get(index).calculatePoint()] +"\t");
        for(int i = 0;i < players.get(index).cards.size();i++)
            out.print(players.get(index).cards.get(i).suit + " " +players.get(index).cards.get(i).face +"\t");
        out.println("\nPoint:"+players.get(index).point+"\n-------------------------------------------------------------------------------------");
    }
    public void initializeCards(){
        dealer.cards.clear();
        for(int i = 0;i < playerCount ;i++)
            players.get(i).cards.clear();
    }
    public void check(){
        
    }
}
