import java.lang.*;
import java.util.*;
public class Dealer extends Partitioner{
    public Dealer(String pName) {
        super(pName);
        super.isDealer = true;
    }
}