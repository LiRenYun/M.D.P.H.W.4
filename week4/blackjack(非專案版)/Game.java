import java.lang.*;
import static java.lang.System.*;
import java.util.*;
public class Game {
    String []face ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
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
            out.print("Player name:");
            playerName = enter.next();
            players.add(new Player(playerName));
        }
        dealer = new Dealer("Vin Diesel");
    }
    public void showAllMember(){    //測試功能用
        out.println("Dealer:");
        out.println(dealer.name);
        out.println("Player:");
        for(int i = 0;i<playerCount;i++){
            out.println(players.get(i).name);
        }
    }
    public void Start(){    //遊戲進行
        int opt;
        Scanner enter = new Scanner(in);
        while(true)
        {
            for(int i = 0;i<2;i++){//start
                for(int k = 0;k < playerCount;k++)
                    players.get(k).hit(deck.getcard());
                dealer.hit(deck.getcard());
            }
            out.println("Dealer("+dealer.name+"): Status:"+Status[dealer.calculatePoint()] +"\t------- ---"+dealer.cards.get(1).suit+" "+dealer.cards.get(1).face);
            dealerFirstPointShow();
            dealer.calculatePoint();
            for(int k = 0;k < playerCount;k++){
                playerShowStatus(k);
            }
            out.println("-----------------------------------------------------------------------");
            for(int i = 0;i < players.size() ;i++){
                while(this.check(i) == 0){  //排除報牌 或者 21點
                    out.printf("Player("+players.get(i).name+"):1.)Hit 2.)Stay:");
                    opt = enter.nextInt();
                    if(opt == 1){
                        players.get(i).hit(deck.getcard());
                        playerShowStatus(i);
                        out.println("-----------------------------------------------------------------------");
                    }
                    else if(opt == 2){
                        break;
                    }
                    else
                        out.println("The select is error!");
                }
            }
            out.println("-----------------------------------------------------------------------");
            if(dealer.point < 17){
                while(dealer.point < 17){
                    dealer.hit(deck.getcard());
                    dealer.calculatePoint();
                    dealerShowStatus();
                    for(int k = 0;k < playerCount;k++){
                        playerShowStatus(k);
                    }
                    out.println("-----------------------------------------------------------------------");
                }
            }
            else{
                dealerShowStatus();
                for(int k = 0;k < playerCount;k++){
                    playerShowStatus(k);
                }
                out.println("-----------------------------------------------------------------------");
            }
            for(int i = 0;i<players.size();i++)
                gameResult(i);
            
            while(true){
                out.printf("1.)New Game 2.)Leave");
                opt = enter.nextInt();
                if(opt == 1){
                    initializeCards();
                    out.println("\n\nStar a new game!\n---------------------------------------------------------------------------------");
                    break;
                }
                else if(opt == 2){
                    out.println("See you next time!");
                    break;
                }
                else
                    out.println("The select is error!");
            }
            if(opt == 2)
                break;
        }
    }
    public void gameResult(int index){  //判斷遊戲結果
        if(players.get(index).Status == 2 && dealer.Status == 2)    //兩方皆爆牌
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 1 && dealer.Status == 1)   //兩方皆21點
            out.println("Player("+players.get(index).name+"):Tie!,Dealer Tie");
        else if(players.get(index).Status == 2 && dealer.Status == 0)   //玩家爆牌 莊家未爆
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 2 && dealer.Status == 1)   //玩家爆牌 莊家BlackJack
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 0 && dealer.Status == 2)   //莊家爆牌，玩家未爆
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).Status == 1 && dealer.Status == 2)   //莊家爆牌，玩家BlackJack
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).point > dealer.point && players.get(index).Status == 0 && dealer.Status ==0)   //玩家點數大於莊家
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).point < dealer.point && players.get(index).Status == 0&& dealer.Status ==0)  //莊家點數大於玩家
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).point == dealer.point && players.get(index).Status == 0 && dealer.Status ==0 )   //雙方點數平手
            out.println("Player("+players.get(index).name+"):Tie!,Dealer Tie");
    }
    public void playerShowStatus(int index){    //秀出當下玩家所有手牌與狀態
        out.printf("Player("+players.get(index).name+"): Status:"+Status[players.get(index).calculatePoint()] +"\t");
        for(int i = 0;i < players.get(index).cards.size();i++)
            out.print(players.get(index).cards.get(i).suit + " " +players.get(index).cards.get(i).face +"\t");
        out.println("\nPoint:"+players.get(index).point);
    }
    public void dealerFirstPointShow(){ //莊家第一次亮牌(一張亮一張不亮)
        int index = 0;
        for(int i = 0;i<13;i++){
                if(dealer.cards.get(1).face.equals(face[i]) == true)
                    index = i;
            }
            if(index == 0)
                out.println("Point:"+11);
            else if(index >0 && index <10)
                out.println("Point:"+(index+1));
            else
                out.println("Point:"+10);
    }
    public void dealerShowStatus(){ //莊家亮手牌
        out.printf("Dealer("+dealer.name+"): Status:"+Status[dealer.calculatePoint()] +"\t");
        for(int i = 0;i < dealer.cards.size();i++)
            out.print(dealer.cards.get(i).suit + " " +dealer.cards.get(i).face +"\t");
        out.println("\nPoint:"+dealer.point);
    }
    public void initializeCards(){  //初始化
        dealer.cards.clear();
        for(int i = 0;i < playerCount ;i++)
            players.get(i).cards.clear();
    }
    public int check(int index){    //檢查玩家是否可以繼續要牌(只要爆牌)或者21點情況下 就可以不用給玩家繼續要牌(但 在要牌的過程中21點了 仍可繼續要)
        if(players.get(index).calculatePoint() == 0){
            return 0;//可以繼續要排或者喊stay
        }
        else if(players.get(index).calculatePoint() == 1 || players.get(index).calculatePoint() == 2){
            return 1;//因為blackjack or busted 就不可以做任何動作了
        }
        return 0;
    }
}
