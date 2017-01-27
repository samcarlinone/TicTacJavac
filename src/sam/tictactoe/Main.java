package sam.tictactoe;


/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        Board b = new Board(3);
        //System.out.println(GridRender.render(b.getState()));
        //GridRender.printSq(GridRender.render(b.getState()));
        GridRender.testPrint();

        System.out.println("Things are in motion");
    }
}