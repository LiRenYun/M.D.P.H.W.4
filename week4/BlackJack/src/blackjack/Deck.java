package blackjack;
import java.lang.*;
import java.util.*;
import static java.lang.System.*;
import java.util.Random;
public class Deck {
    ArrayList<Card> cards;
    int n;
    Random rand;
    String []suit ={"♠Spade","♥Heart","♦Diamond","♣Club"};
    String []face ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public Deck(int n){
        rand = new Random();
        cards = new ArrayList<Card>();
        if(n <= 0)
            this.n = 1;
        else
            this.n = n;
        for(int i = 0;i < this.n*52;i++){
            Card card =new Card(suit[i/13],face[i%13]);
            cards.add(card);
        }
    }
    public void Shuffle(){
        int k = rand.nextInt(51);
        for(int i = 0;i < cards.size();i++){
            k = rand.nextInt(51);
            Collections.swap(cards,i,k);
        }
    }
    public void newcards(){
        for(int i = 0;i < this.n*52;i++){
            Card card =new Card(suit[i/13],face[i%13]);
            cards.add(card);
        }
    }
    public Card getcard(){
        if(cards.size() == 0){
            newcards();
        }
        else
            return cards.remove(0);
        return null;
    }
    public void show(){
        for(int i = 0;i < cards.size();i++){
            out.println(cards.get(i).suit+"  "+cards.get(i).face);
        }
    }
}
