import java.util.ArrayList;

public class BFTokenizer {

    private String invalidChars = "[^+\\-\\[\\]<>.,]"; //matches all but "+-[]<>.,"

    public BFTokenizer(){

    }

    public char[] tokenize(String sourceCode){
        String tokens = sourceCode.replaceAll(invalidChars, "");

        return tokens.toCharArray();
    }

}
