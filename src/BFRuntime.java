public class BFRuntime {

    char[] instrTape;
    byte[] dataTape;

    int instrPointer;
    int dataPointer;

    public BFRuntime(char[] instructions, int dataTapeSize, int pointerStartIndex){
        instrTape = instructions;
        dataTape = new byte[dataTapeSize];

        instrPointer = 0;
        dataPointer = pointerStartIndex;
    }

    /**
     * Executes single instruction
     */
    public void step(){
        switch (instrTape[instrPointer]){
            //TODO copy over and clean up cases
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

    public byte[] getData(){
        return dataTape;
    }
}
