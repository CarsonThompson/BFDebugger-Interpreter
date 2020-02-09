import java.io.*;

/**
 * Executes parsed and tokenize BF programs, will eventually have hooks for debugging.
 *
 * <pre>
 * ====================================================================================================================
 * > | increment the data pointer (to point to the next cell to the right).
 * < | decrement the data pointer (to point to the next cell to the left).
 * + | increment the byte at the data pointer.
 * - | decrement the byte at the data pointer.
 * . | output the byte at the data pointer.
 * , | accept one byte of input, storing its value in the byte at the data pointer.
 * [ | if the byte at the data pointer is zero, jump forward to the command after the matching ] command.
 * ] | if the byte at the data pointer is nonzero, jump back to the command after the matching [ command.
 *   |     -Wikipedia
 * ====================================================================================================================
 *</pre>
 * @author Carson Thompson
 */
public class BFRuntime {

    private char[] instrTape;
    private byte[] dataTape;

    private int instrPointer;
    private int dataPointer;

    private InputStreamReader usrIn;

    public BFRuntime(char[] instructions, int dataTapeSize, int dataPointerStartIndex){
        instrTape = instructions;
        dataTape = new byte[dataTapeSize];

        instrPointer = 0;
        dataPointer = dataPointerStartIndex;

        try { //TODO find better way of handling charset exception
            usrIn = new InputStreamReader(System.in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes all remaining instructions
     */
    public void run(){
        while (instrPointer < instrTape.length){
            step();
        }
    }

    /**
     * Executes a single instruction
     *
     * @return true, if step was successful
     */
    public boolean step(){
        if(instrPointer >= instrTape.length) //Checks if there are no more instructions
            return false;

        switch (instrTape[instrPointer]){
            case '>': //move pointer to the next cell
                dataPointer++;
                break;
            case '<': //move pointer to the previous cell
                dataPointer--;
                break;
            case '+': //add 1 to current cell
                dataTape[dataPointer]++;
                break;
            case '-': //subtract 1 from current cell
                dataTape[dataPointer]--;
                break;
            case '.': //prints value of current cell as UTF-8 char
                System.out.print((char) dataTape[dataPointer]);
                break;
            case ',': //reads a single UTF-8 byte of buffered input from stdin, see readByte() for more detail.
                dataTape[dataPointer] = readByte();
                break;
            case '[': //jumps forward to closing bracket if data at pointer is 0, actually lands at instruction after.
                if (dataTape[dataPointer] == 0)
                    instrPointer = getClosingBracket();
                break;
            case ']': //jumps back to opening bracket if data at pointer is not 0, actually lands at instruction after.
                if (dataTape[dataPointer] != 0)
                    instrPointer = getOpeningBracket();
                break;
        }

        instrPointer++; //moves to next instruction
        return true; //successful!
    }


    private byte readByte() {
        try {
            return (byte)usrIn.read();
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * Performs a linear search to find the position of the corresponding closing bracket from
     * the current location of the instruction pointer.
     *
     * @return index of closing bracket
     */
    private int getClosingBracket() {
        int bracketStack = 1; //counts brackets in FIFO manner
        int curIndex = instrPointer;

        while (bracketStack > 0){
            curIndex++;

            if(instrTape[curIndex] == '[')
                bracketStack++;

            if(instrTape[curIndex] == ']')
                bracketStack--;
        }

        return curIndex;
    }

    /**
     * Performs a linear search to find the position of the corresponding opening bracket from
     * the current location of the instruction pointer.
     *
     * @return index of opening bracket
     */
    private int getOpeningBracket() {
        int bracketStack = 1; //counts brackets in FIFO manner
        int curIndex = instrPointer;

        while (bracketStack > 0){
            curIndex--;

            if (instrTape[curIndex] == ']') {
                bracketStack++;
            } else if (instrTape[curIndex] == '[') {
                bracketStack--;
            }
        }

        return curIndex;
    }
}
