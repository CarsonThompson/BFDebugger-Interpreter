import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static private byte[] tape = new byte[30000];
    static private char[] instrTape = new char[30000];
    static private int pointer = 0;
    static private int instrPointer = 0;

    static private int bracketCount = 1;

    public static void main(String[] args) {
        File f = new File(args[1]);     //Creation of File Descriptor for input file
        FileReader fr = null;   //Creation of File Reader object

        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);  //Creation of BufferedReader object
        int c = 0;
        int n = 0;
        while (true)         //Read char by Char
        {
            try {
                if (!((c = br.read()) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            char character = (char) c;          //converting integer to char


            instrTape[n] = character;       //Display the Character
            n++;
        }

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
