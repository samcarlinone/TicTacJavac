package sam.tictactoe;

import java.util.Arrays;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class GridRender {

    static final char HORIZONTAL = ('═');
    static final char VERTICAL = ('║');
    static final char TOP_LEFT = ('╔');
    static final char TOP_RIGHT = ('╗');
    static final char BOT_LEFT = ('╚');
    static final char BOT_RIGHT = ('╝');
    static final char MID_LEFT = ('╠');
    static final char MID_RIGHT = ('╣');
    static final char MID_TOP = ('╦');
    static final char MID_BOT = ('╩');
    static final char MID_MID = ('╬');

    /**
     * Takes flat data of square area and prints it properly to console
     * @param data flat array in
     */
    public static void print(char[] data, int rowSize) {
        System.out.println(Integer.toString(data.length));
        int size = data.length/rowSize;

        for(int i=0; i<size; i++) {
            System.out.println(Arrays.copyOfRange(data, i*rowSize, (i+1)*rowSize));
        }
    }

    /**
     * Renders a regular board of any size
     * @param data board data in flat array
     * @return processed data with borders, still flat
     */
    public static char[] render(char[] data) {
        int size = (int) Math.sqrt(data.length);

        String top = new CharBuilder().append(TOP_LEFT)
                                      .repeat(""+new CharBuilder().repeat(HORIZONTAL, 3)+MID_TOP, size)
                                      .setEnd(TOP_RIGHT)
                                      .toString();

        String middle = new CharBuilder().repeat("" +
                                        new CharBuilder().repeat(VERTICAL+" % ", size).append(VERTICAL) +
                                        new CharBuilder().append(MID_LEFT).repeat(""+new CharBuilder().repeat(HORIZONTAL, 3).append(MID_MID), size).setEnd(MID_RIGHT), size)
                                        .substring(0, 13*(size*2-1))
                                        .toString();

        String bottom = new CharBuilder().append(""+BOT_LEFT)
                .repeat(""+new CharBuilder().repeat(HORIZONTAL, 3)+MID_BOT, size)
                .setEnd(BOT_RIGHT)
                .toString();

        CharBuilder result = new CharBuilder().append(top).append(middle).append(bottom);
        return result.replace(data);
    }

    /**
     * Fills a secondary array into a segment of the in array
     * @param in target array
     * @param startIndex beginning index inclusive
     * @param endIndex ending index inclusive
     * @param chars char array to sample from
     */
    private static void fill(char[] in, int startIndex, int endIndex, char[] chars) {
        for(int i=startIndex; i<=endIndex; i++) {
            in[i] = chars[(i-startIndex) % chars.length];
        }
    }
}
