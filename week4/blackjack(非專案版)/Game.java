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
    public void showAllMember(){    //���ե\���
        out.println("Dealer:");
        out.println(dealer.name);
        out.println("Player:");
        for(int i = 0;i<playerCount;i++){
            out.println(players.get(i).name);
        }
    }
    public void Start(){    //�C���i��
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
                while(this.check(i) == 0){  //�ư����P �Ϊ� 21�I
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
    public void gameResult(int index){  //�P�_�C�����G
        if(players.get(index).Status == 2 && dealer.Status == 2)    //�����z�P
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 1 && dealer.Status == 1)   //����21�I
            out.println("Player("+players.get(index).name+"):Tie!,Dealer Tie");
        else if(players.get(index).Status == 2 && dealer.Status == 0)   //���a�z�P ���a���z
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 2 && dealer.Status == 1)   //���a�z�P ���aBlackJack
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).Status == 0 && dealer.Status == 2)   //���a�z�P�A���a���z
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).Status == 1 && dealer.Status == 2)   //���a�z�P�A���aBlackJack
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).point > dealer.point && players.get(index).Status == 0 && dealer.Status ==0)   //���a�I�Ƥj����a
            out.println("Player("+players.get(index).name+"):Win!,Dealer Lose");
        else if(players.get(index).point < dealer.point && players.get(index).Status == 0&& dealer.Status ==0)  //���a�I�Ƥj�󪱮a
            out.println("Player("+players.get(index).name+"):Lose!,Dealer Win");
        else if(players.get(index).point == dealer.point && players.get(index).Status == 0 && dealer.Status ==0 )   //�����I�ƥ���
            out.println("Player("+players.get(index).name+"):Tie!,Dealer Tie");
    }
    public void playerShowStatus(int index){    //�q�X��U���a�Ҧ���P�P���A
        out.printf("Player("+players.get(index).name+"): Status:"+Status[players.get(index).calculatePoint()] +"\t");
        for(int i = 0;i < players.get(index).cards.size();i++)
            out.print(players.get(index).cards.get(i).suit + " " +players.get(index).cards.get(i).face +"\t");
        out.println("\nPoint:"+players.get(index).point);
    }
    public void dealerFirstPointShow(){ //���a�Ĥ@���G�P(�@�i�G�@�i���G)
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
    public void dealerShowStatus(){ //���a�G��P
        out.printf("Dealer("+dealer.name+"): Status:"+Status[dealer.calculatePoint()] +"\t");
        for(int i = 0;i < dealer.cards.size();i++)
            out.print(dealer.cards.get(i).suit + " " +dealer.cards.get(i).face +"\t");
        out.println("\nPoint:"+dealer.point);
    }
    public void initializeCards(){  //��l��
        dealer.cards.clear();
        for(int i = 0;i < playerCount ;i++)
            players.get(i).cards.clear();
    }
    public int check(int index){    //�ˬd���a�O�_�i�H�~��n�P(�u�n�z�P)�Ϊ�21�I���p�U �N�i�H���ε����a�~��n�P(�� �b�n�P���L�{��21�I�F ���i�~��n)
        if(players.get(index).calculatePoint() == 0){
            return 0;//�i�H�~��n�ƩΪ̳�stay
        }
        else if(players.get(index).calculatePoint() == 1 || players.get(index).calculatePoint() == 2){
            return 1;//�]��blackjack or busted �N���i�H������ʧ@�F
        }
        return 0;
    }
}
