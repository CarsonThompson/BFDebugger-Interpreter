import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Driver for the current iteration of my BF interpreter.
 *
 * @author Carson Thompson
 */
public class Main {

    public static void main(String[] args) {
        String rawSource = "";
        try {
            rawSource = new String(Files.readAllBytes(Paths.get(args[1])));
        } catch (Exception e) {
            e.printStackTrace(); //TODO Implement usage msg
        }

        //tokenize raw input -- removes all comments and only leaves valid instructions
        BFTokenizer sourceTokenizer = new BFTokenizer();

        char[] tokenizedSource = new char[0];

        try {
            tokenizedSource = sourceTokenizer.tokenize(rawSource);
        } catch (InvalidSourceException e) { //temp error message
            System.out.println("Error: " + e.getMessage() +
                    " at character position(s) " + Arrays.toString(e.getErrorIndexes()));
            System.exit(2);
        }


        //sets up runtime and executes program
        BFRuntime runtime = new BFRuntime(tokenizedSource, 30000, 0);
        runtime.run();
    }
}
