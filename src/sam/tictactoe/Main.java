package sam.tictactoe;


/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        Board b = new Board(3);

        b.setChar('O', 1, 1);
        b.setChar('O', 2);

        GridRender.print(GridRender.render(b.getState()), 13);
    }
}