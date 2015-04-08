import java.lang.*;
import static java.lang.System.*;
import java.util.*;
public class Partitioner {
    int Status = 0;
    String name;
    String []face ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};  //�ΨӧP�_��P�I��
    int softhand = 0; //A�٬��n�P �������X�iA �ΨӧP�_�ϥ� �z�P�ɡA-10�I���� A�@�}�l����11�I
    int point = 0;  //�`�I�Ƭ���
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
    public int calculatePoint(){    //�^���I�ƪ��A
        softhand = 0;
        point = 0;
        int index = 0;
        for(int i = 0;i<this.cards.size();i++){ //�P�_������index ���I��count�M A����
            for(int j = 0;j <13;j++){
                if(this.cards.get(i).face.equals(face[j]) == true){
                    index = j;
                }
            }
            if(index == 0){ //��0�i A ��11�I �����n�P++
                softhand++;
                point+=11;
            }
            else if(index > 0 && index <10){//1~9�i �� Index+1
                point += (index+1);
            }
            else    //JQK���p(index 10 11 12)
                point += 10;
        }
        if(point == 21 && softhand == 1){   //��nblackjack
            Status = 1;
            return 1;
        }
        else if(point > 21 && softhand == 0){   //�z�P�B�S��A
            Status = 2;
            return 2;
        }
        else if(point > 21 && softhand != 0){   //�z�P���O��A
            while(point > 21 && softhand > 0){
                point -= 10;
                softhand--;
            }
            if(point > 21 && softhand == 0){    //�A�F �S�P�F �o�z�P�F
                Status = 2;
                return 2;
            }
            else if(point < 21){    //�A �S�z�P
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
