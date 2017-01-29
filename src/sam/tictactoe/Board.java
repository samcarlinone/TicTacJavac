package sam.tictactoe;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Board {
    private char[] data;

    public int size;

    public enum BoardState {
        EMPTY, PARTIAL, WON, FULL
    }

    public BoardState boardState;
    public char winner = ' ';

    /**
     * Board constructor from existing data
     * @param data a character array of data, ' ' (space) is reserved
     * @param size size of square board in characters
     */
    public Board(char[] data, int size) {
        this.data = data;
        this.size = size;
    }

    /**
     * Board constructor with default value (space)
     * @param size size of square board in characters
     */
    public Board(int size) {
        data = new char[size*size];

        for(int i=0; i<size*size; i++)
            data[i] = ' ';

        this.size = size;

        boardState = BoardState.EMPTY;
    }

    /**
     * Get char from data by index
     * @param index flat array of character
     * @return The character at index or 'e' if index out of bounds
     */
    public char getChar(int index) {
        if(index < 0 || data.length <= index)
            return 'e';

        return data[index];
    }

    /**
     * Get char from data by pos
     * @param x x coordinate
     * @param y y coordinate
     * @return The character at pos (x, y) or 'e' if index out of bounds
     */
    public char getChar(int x, int y) {
        if(x < 0 || y < 0 || size <= x || size <= y)
            return 'e';

        return data[x + y*size];
    }

    /**
     * Set char in data by index
     * @param c new character, (space) is taken to mean empty place
     * @param index validated index, out of bounds index results in no change
     */
    public void setChar(char c, int index) {
        if(index < 0 || data.length <= index)
            return;

        data[index] = c;

        updateState();
    }

    /**
     * Set char in data by pos
     * @param c new character, (space) is taken to mean empty place
     * @param x validated x coord
     * @param y validated y coord
     */
    public void setChar(char c, int x, int y) {
        if(x < 0 || y < 0 || size <= x || size <= y)
            return;

        data[x + y*size] = c;

        updateState();
    }

    /**
     * Updates boardState and winner internally, and returns new data
     * @return Current boardState
     */
    private BoardState updateState() {
        Boolean partial = false;

        //Check rows + columns
        for(int i=0; i<size; i++) {
            char row = getRow(i);

            if(row != ' ') {
                partial = true;

                if(row != '!') {
                    //The row is not mixed or empty, so someone won
                    winner = row;
                    return boardState = BoardState.WON;
                }
            }

            char col = getCol(i);

            if(col != ' ') {
                partial = true;

                if(col != '!') {
                    //The column is not mixed or empty, so someone won
                    winner = col;
                    return boardState = BoardState.WON;
                }
            }
        }

        //Check diagonals
        char diagR = getRisingDiag();
        if(diagR != ' ' && diagR != '!') {
            //The column is not mixed or empty, so someone won
            winner = diagR;
            return boardState = BoardState.WON;
        }

        char diagF = getFallingDiag();
        if(diagF != ' ' && diagF != '!') {
            //The column is not mixed or empty, so someone won
            winner = diagF;
            return boardState = BoardState.WON;
        }

        //Check full
        Boolean full = true;

        for(int i=0; i<size*size; i++){
            if(getChar(i) == ' ') {
                full = false;
                break;
            }
        }

        if(full)
            return boardState = BoardState.FULL;

        //Either partial or empty
        if(partial)
            return boardState = BoardState.PARTIAL;
        else
            return boardState = BoardState.EMPTY;
    }

    /**
     * Gets the data of a row
     * @param row The row index [0, height - 1]
     * @return The return value will be a char if the row is all one char, an 'e' if the row is out-of-bounds, or an '!' if the row is mixed
     */
    public char getRow(int row) {
        if(row < 0 || size <= row)
            return 'e';

        char row_val = getChar(0, row);

        for(int i=1; i<size; i++)
            if(getChar(i, row) != row_val)
                return '!';

        return row_val;
    }

    /**
     * Gets the data of a column
     * @param col The column index [0, column - 1]
     * @return The return value will be a char if the column is all one char, an 'e' if the column is out-of-bounds, or an '!' if the column is mixed
     */
    public char getCol(int col) {
        if(col < 0 || size <= col)
            return 'e';

        char col_val = getChar(col, 0);

        for(int i=1; i<size; i++)
            if(getChar(col, i) != col_val)
                return '!';

        return col_val;
    }

    /**
     * Gets the data of the rising diagonal (/)
     * @return The return value will be the char if the diagonal is one char, or an '!' if the diagonal is mixed
     */
    public char getRisingDiag() {
        char diag_val = getChar(0, size-1);

        for(int i=1; i<size; i++)
            if(getChar(i, size-i-1) != diag_val)
                return '!';

        return diag_val;
    }

    /**
     * Gets the data of the falling diagonal (\)
     * @return The return value will be the char if the diagonal is one char, or an '!' if the diagonal is mixed
     */
    public char getFallingDiag() {
        char diag_val = getChar(0, 0);

        for(int i=1; i<size; i++)
            if(getChar(i, i) != diag_val)
                return '!';

        return diag_val;
    }

    /**
     * Returns a char array of the data
     * @return flat data char array
     */
    public char[] getData() {
        return data;
    }
}
