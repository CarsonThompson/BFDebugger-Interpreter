import java.util.ArrayList;

public class BFDebugger {
    private BFRuntime runtime;
    private char[] instrTape;
    private byte[] dataTape;

    private ArrayList breakPoints;

    public BFDebugger(BFRuntime runtime){
        this.runtime = runtime;
        instrTape = runtime.getInstrTape();
        dataTape = runtime.getDataTape();
    }

    public void changeRuntime(BFRuntime runtime){
        this.runtime = runtime;
        instrTape = runtime.getInstrTape();
        dataTape = runtime.getDataTape();
    }

    public boolean runUntillBreakpoint(){
        while (!breakPoints.contains(runtime.getInstrPointer())){ //while current instruction isn't a breakpoint
            if(!runtime.step()){ //if you cant step at end of program
                return false;
            }
        }
        return true;
    }

    public boolean setBreakpoint(int instrIndex){
        if(instrIndex < 0 || instrIndex >= instrTape.length){
            return false;
        }

        breakPoints.add(instrIndex);
        return true;
    }


}
