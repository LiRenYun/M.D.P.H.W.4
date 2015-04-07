package blackjack;
import java.lang.*;
import static java.lang.System.*;
import java.util.*;
public class Partitioner {
    String name;
    String []face ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    int softhand = 0; //A稱為軟牌
    int point = 0;
    boolean isDealer;
    ArrayList<Card> cards;
    public Partitioner(String pName) {
        this.name = pName;
        this.isDealer = false;
        cards = new ArrayList<Card>();
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void hit(Card input){
        cards.add(input);
    }
    public void showcards(){
        for(int i = 0;i<cards.size();i++)
            out.println(cards.get(i).suit + " "+cards.get(i).face);
    }
    public int calculatePoint(){    //回傳點數狀態
        softhand = 0;
        point = 0;
        int index = 0;
        for(int i = 0;i<this.cards.size();i++){
            for(int j = 0;j <13;j++){
                if(this.cards.get(i).face.equals(face[j]) == true){
                    index = j;
                }
            }
            if(index == 0){
                softhand++;
                point+=11;
            }
            else if(index > 0 && index <=10){
                point += (index+1);
            }
            else
                point += 10;
        }
        if(point == 21 && softhand == 1){
            return 1;
        }
        else if(point > 21 && softhand == 0){
            return 2;
        }
        else if(point > 21 && softhand != 0){
            while(point > 21 && softhand > 0){
                point -= 10;
                softhand--;
            }
            if(point > 21 && softhand == 0)
                return 2;
            else if(point < 21)
                return 0;
        }
        else
            return 0;
        return 0;
    }
}
