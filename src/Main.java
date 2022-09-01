import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        String fileName = args[0];
        ArrayList<Token> listTokens = new ArrayList<Token>();

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+fileName), "ASCII"));

            String line;
            int lineIndex = 0;
            while ((line = br.readLine()) != null) {
                lineIndex++;
                for (int i = 0; i < line.length(); i++) {
                    char lettre = line.charAt(i);
                    System.out.println("Char at: " + lettre);
                    switch (lettre) {
                        case ' ':
                            break;
                        case '(':
                            listTokens.add(new Token("parOpen", 0, lineIndex));
                            break;
                        case ')':
                            listTokens.add(new Token("parClose", 0, lineIndex));
                            break;
                        case '{':
                            listTokens.add(new Token("acOpen", 0, lineIndex));
                            break;
                        case '}':
                            listTokens.add(new Token("accClose", 0, lineIndex));
                            break;
                            //case for all letters
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z':
                        //case for all upper letters
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                            listTokens.add(new Token("lettre", (int)lettre, lineIndex));
                            break


                    }
                }
                System.out.println("Another line");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }   

        System.out.println(listTokens.toString());

    }

}
