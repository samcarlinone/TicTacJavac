package sam.tictactoe;

import java.util.Arrays;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class GridRender {
    public interface Borders {
        char HORIZONTAL = ('═');
        char VERTICAL = ('║');
        char TOP_LEFT = ('╔');
        char TOP_RIGHT = ('╗');
        char BOT_LEFT = ('╚');
        char BOT_RIGHT = ('╝');
        char MID_LEFT = ('╠');
        char MID_RIGHT = ('╣');
        char MID_TOP = ('╦');
        char MID_BOT = ('╩');
        char MID_MID = ('╬');
    }

    public static void printSq(char[] data) {
        System.out.println(Integer.toString(data.length));
        int size = (int) Math.sqrt(data.length);

        for(int i=0; i<size; i++) {
            System.out.println(Arrays.copyOfRange(data, i*size, i*size + size));
        }
    }

    public static char[] render(char[] data) {
        int size = (int) Math.sqrt(data.length);
        int finalSize = size*2+1;

        char[] result = new char[finalSize * finalSize];

        //Render top Row
        result[0] = Borders.TOP_LEFT;
        fill(result, 1, finalSize-2, new char[]{Borders.HORIZONTAL, Borders.MID_TOP});
        result[finalSize-1] = Borders.TOP_RIGHT;

        //Render middle
        for(int i=1; i<finalSize-1; i++) {
            if(i % 2 == 1) {
                //Render data row
                for (int j = 0; j < size; j++) {
                    result[i * finalSize + j * 2] = Borders.VERTICAL;
                    result[i * finalSize + j * 2 + 1] = data[(i-1)/2 * size + j];
                }

                result[i * finalSize + finalSize - 1] = Borders.VERTICAL;
            } else {
                //Render middle row
                result[i * finalSize] = Borders.MID_LEFT;
                fill(result, i * finalSize + 1, i * finalSize + finalSize - 2, new char[]{Borders.HORIZONTAL, Borders.MID_MID});
                result[i * finalSize + finalSize - 1] = Borders.MID_RIGHT;
            }
        }

        //Render bottom row
        result[finalSize * (finalSize-1)] = Borders.BOT_LEFT;
        fill(result, finalSize*(finalSize-1)+1, finalSize*(finalSize)-1, new char[]{Borders.HORIZONTAL, Borders.MID_BOT});
        result[finalSize*finalSize-1] = Borders.BOT_RIGHT;

        return result;
    }

    private static void fill(char[] in, int startIndex, int endIndex, char[] chars) {
        for(int i=startIndex; i<=endIndex; i++) {
            in[i] = chars[(i-startIndex) % chars.length];
        }
    }
}
