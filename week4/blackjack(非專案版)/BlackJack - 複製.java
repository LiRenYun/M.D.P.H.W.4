import static java.lang.System.*;
import java.lang.*;
import java.util.*;
public class BlackJack {
    public static void main(String[] args) {
        int member = 0;
        Scanner enter = new Scanner(in);
        Game game;
        out.print("Players?");
        member = enter.nextInt();
        game = new Game(member);
        game.showAllMember();
        game.Start();
    }
}
