package sam.tictactoe;

/**
 * Created by CARLINSE1 on 2/1/2017.
 */
public interface Game {
    /**
     * Runs game synchronously, should be callable multiple times
     */
    void run();

    /**
     * Gets a description of the game as a string
     * @return String, should not container newlines or other formatting characters
     */
    String description();
}
