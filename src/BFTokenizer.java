import javafx.util.Pair;

import java.util.Stack;

/**
 *
 * @author Carson Thompson
 */
public class BFTokenizer {

    private String invalidChars = "[^+\\-\\[\\]<>.,]"; //matches all but "+-[]<>.,"

    public BFTokenizer(){
    }

    //TODO add check for balanced brackets
    public char[] tokenize(String sourceCode) throws InvalidSourceException {
        int[] unbalancedBrackets = findUnbalancedBrackets(sourceCode);

        if(unbalancedBrackets.length != 0){
            throw new InvalidSourceException("Unbalanced brackets in source", unbalancedBrackets);
        }

        String tokens = sourceCode.replaceAll(invalidChars, "");

        return tokens.toCharArray();
    }

    /**
     * Finds the indices of any unmatched brackets if any.
     *
     * @param source input to check if brackets are balanced
     * @return int[] of indices of all unmatched brackets, empty if none
     */
    public int[] findUnbalancedBrackets(String source){
        Stack<Pair<Integer, Character>> bracketStack = new Stack<>();

        for (int i = 0; i < source.length(); i++) {
            switch (source.charAt(i)) {
                case '[': //removes previous bracket from stack if current char closes bracket, adds to stack otherwise
                    if (!bracketStack.empty() && bracketStack.peek().getValue() == ']') {
                        bracketStack.pop();
                    } else {
                        bracketStack.push(new Pair<>(new Integer(i), new Character('[')));
                    }
                    break;
                case ']': //removes previous bracket from stack if current char closes bracket, adds to stack otherwise
                    if (!bracketStack.empty() && bracketStack.peek().getValue() == '[') {
                        bracketStack.pop();
                    } else {
                        bracketStack.push(new Pair<>(new Integer(i), new Character(']')));
                    }
                    break;
            }
        }

        //takes index of every unclosed bracket as Integer and returns as a int[]
        int[] bracketIndices = bracketStack.stream().mapToInt(Pair::getKey).toArray();
        return bracketIndices;
    }
}
