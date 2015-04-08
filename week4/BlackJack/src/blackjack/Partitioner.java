package blackjack;
import java.lang.*;
import static java.lang.System.*;
import java.util.*;
public class Partitioner {
    int Status = 0;
    String name;
    String []face ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};  //用來判斷手牌點數
    int softhand = 0; //A稱為軟牌 紀錄有幾張A 用來判斷使用 爆牌時，-10點紀錄 A一開始都為11點
    int point = 0;  //總點數紀錄
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
        for(int i = 0;i<this.cards.size();i++){ //判斷位於哪個index 做點數count和 A紀錄
            for(int j = 0;j <13;j++){
                if(this.cards.get(i).face.equals(face[j]) == true){
                    index = j;
                }
            }
            if(index == 0){ //第0張 A 為11點 紀錄軟牌++
                softhand++;
                point+=11;
            }
            else if(index > 0 && index <10){//1~9張 為 Index+1
                point += (index+1);
            }
            else    //JQK情況(index 10 11 12)
                point += 10;
        }
        if(point == 21 && softhand == 1){   //剛好blackjack
            Status = 1;
            return 1;
        }
        else if(point > 21 && softhand == 0){   //爆牌且沒有A
            Status = 2;
            return 2;
        }
        else if(point > 21 && softhand != 0){   //爆牌但是有A
            while(point > 21 && softhand > 0){
                point -= 10;
                softhand--;
            }
            if(point > 21 && softhand == 0){    //減完A了 沒牌了 卻爆牌了
                Status = 2;
                return 2;
            }
            else if(point < 21){    //減完A 沒爆牌
                Status = 0;
                return 0;
            }
        }
        else{
            Status = 0;
            return 0;
        }
        return 0;
    }
}
