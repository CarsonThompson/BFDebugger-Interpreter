/**
 * Defines exception for problems found in user provided source, includes field for
 * showing where exception occurs in source.
 *
 * @author Carson Thompson
 */
public class InvalidSourceException extends Exception {
    private int[] exceptionIndices;

    public InvalidSourceException(String message, int[] exceptionIndices){
        super(message);
        this.exceptionIndices = exceptionIndices;
    }

    /**
     *
     * @return location(s) in source where exception occurs.
     */
    public int[] getExceptionIndexes() {
        return exceptionIndices;
    }

}
