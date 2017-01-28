package sam.tictactoe;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class CharBuilder {
    private StringBuilder data;

    public CharBuilder() {
        data = new StringBuilder();
    }

    /**
     * Repeat and append a string builder compatible data
     * @param in String containing data
     * @param times Number of times to append
     * @return Returns this CharBuilder
     */
    public CharBuilder repeat(String in, int times) {
        for(int i=0; i<times; i++) {
            data.append(in);
        }

        return this;
    }

    /**
     * Repeat and append a string builder compatible data
     * @param in Char
     * @param times Number of times to append
     * @return Returns this CharBuilder
     */
    public CharBuilder repeat(char in, int times) {
        for(int i=0; i<times; i++) {
            data.append(in);
        }

        return this;
    }

    /**
     * Append a string to this CharBuilder
     * @param in The input string
     * @return Returns this CharBuilder
     */
    public CharBuilder append(String in) {
        data.append(in);

        return this;
    }

    /**
     * Append a string to this CharBuilder
     * @param in Char
     * @return Returns this CharBuilder
     */
    public CharBuilder append(char in) {
        data.append(in);

        return this;
    }

    /**
     * Change the character at a specific index
     * @param in input character
     * @param index index to change at
     * @return Returns this CharBuilder
     */
    public CharBuilder setAt(char in, int index) {
        data.deleteCharAt(index);
        data.insert(index, in);

        return this;
    }

    /**
     * Change the last character
     * @param in input character
     * @return Returns this CharBuilder
     */
    public CharBuilder setEnd(char in) {
        data.deleteCharAt(data.length()-1);
        data.append(in);

        return this;
    }

    /**
     * Get a part of the current data
     * @param begin begin index (inclusive)
     * @param end end index (exclusive)
     * @return Returns a new CharBuilder
     */
    public CharBuilder substring(int begin, int end) {
        return new CharBuilder().append(data.substring(begin, end));
    }

    /**
     * Replaces '%' chars with chars from data array
     * @param inData data to use for replacement
     * @return returns new char[] array
     */
    public char[] replace(char[] inData) {
        int dataIndex = 0;
        StringBuilder result = new StringBuilder(data);

        while(result.indexOf("%") != -1) {
            int index = result.indexOf("%");

            result.deleteCharAt(index);
            result.insert(index, inData[dataIndex]);

            dataIndex += 1;
        }

        return result.toString().toCharArray();
    }

    /**
     * Gets the internal data as a string
     * @return A string representation of data
     */
    public String toString() {
        return data.toString();
    }

    /**
     * Gets data as char array
     * @return The data as a char[]
     */
    public char[] toCharArray() {
        return toString().toCharArray();
    }
}
