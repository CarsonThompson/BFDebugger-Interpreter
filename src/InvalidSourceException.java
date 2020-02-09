public class InvalidSourceException extends Exception {
    private int[] errorIndexes;

    public InvalidSourceException(String message, int[] errorIndexes){
        super(message);
        this.errorIndexes = errorIndexes;
    }

    public int[] getErrorIndexes() {
        return errorIndexes;
    }
    
}
