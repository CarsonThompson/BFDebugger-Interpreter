import java.util.ArrayList;

/**
 *
 * @author Carson Thompson
 */
public class BFTokenizer {

    private String invalidChars = "[^+\\-\\[\\]<>.,]"; //matches all but "+-[]<>.,"

    public BFTokenizer(){
    }

    //TODO add check for balanced brackets
    public char[] tokenize(String sourceCode){
        String tokens = sourceCode.replaceAll(invalidChars, "");
        return tokens.toCharArray();
    }

}
