import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static private String acceptedChars = "+-<>[].,";
    static private byte[] tape = new byte[30000];
    static private char[] instrTape = new char[30000];
    static private int pointer = 0;
    static private int instrPointer = 0;

    static private int bracketCount = 1;

    public static void main(String[] args) {
        String rawSource = "";
        try {
            rawSource = new String(Files.readAllBytes(Paths.get(args[1])));
        } catch (Exception e) {
            e.printStackTrace(); //TODO Implement usage msg
        }

        //tokenize raw input -- removes all comments and only leaves valid instructions
        BFTokenizer sourceTokenizer = new BFTokenizer();
        char[] tokenizedSource = sourceTokenizer.tokenize(rawSource);

        //Temp shim to reach working state
        System.arraycopy(tokenizedSource, 0, instrTape, 0, tokenizedSource.length);


        //TODO: move to new class
        while (instrTape[instrPointer] != '\u0000') {
            switch (instrTape[instrPointer]) {
                case '>':


                    if(pointer>=tape.length-1){
                        pointer = 0;
                    } else {
                        pointer++;
                    }
                    instrPointer++;
                    break;
                case '<':

                    if(pointer<=0){
                        pointer = tape.length-1;
                    } else {
                        pointer--;
                    }

                    instrPointer++;
                    break;
                case '+':
                    tape[pointer]++;
                    instrPointer++;
                    break;
                case '-':
                    tape[pointer]--;
                    instrPointer++;
                    break;
                case '.':
                    System.out.print((char) tape[pointer]);
                    instrPointer++;
                    break;
                case ',':
                    Scanner usrIn = new Scanner(System.in);
                    tape[pointer] = (byte)usrIn.next().charAt(0);
                    usrIn.close();
                    instrPointer++;
                    break;
                case '[':
                    if (tape[pointer] == 0) {

                        while (bracketCount != 0){
                            instrPointer++;

                            if (instrTape[instrPointer] == '['){
                                bracketCount++;
                            }

                            if(instrTape[instrPointer] == ']'){
                                bracketCount--;
                            }
                        }

                        bracketCount = 1;
                        instrPointer++;
                    } else {
                        instrPointer++;
                    }
                    break;
                case ']':
                    if (tape[pointer] != 0) {
                        while (bracketCount != 0){
                            instrPointer--;

                            if (instrTape[instrPointer] == ']'){
                                bracketCount++;
                            }

                            if(instrTape[instrPointer] == '['){
                                bracketCount--;
                            }
                        }

                        bracketCount = 1;
                        instrPointer++;
                    } else {
                        instrPointer++;
                    }
                    break;
                default:

                    break;
            }
        }
    }
}
