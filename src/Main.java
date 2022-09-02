import java.io.*;
import java.util.ArrayList;

public class Main {

    Token current = new Token(null, 0, 0);
    Token last = new Token(null, 0, 0);
    static String inside = "";
    int i = 0;

    Token next() {


        //Initialisation variables
        String line;
        int lineIndex = 0;
        boolean isAWord = false;
        String word = "";


        lineIndex++;
        char lettre = inside.charAt(i);
        i++;
        switch (lettre) {
            case ' ': //Space or end of a word
                if (isAWord) {

                    if (word.equals("return")) { //TODO Check if that's actually how it's supposed to be done lmao
                        last = current;
                        current = new Token("return", 0, lineIndex);
                        return current;
                    } else if (word.equals("int")) {
                        last = current;
                        current = new Token("int", 0, lineIndex);
                        return current;
                    } else {
                        last = current;
                        current = new Token("mot", 0, lineIndex); //TODO Change valeur for the actual "word" variable ?
                        return current;
                    }
                    //isAWord = false; //Le mot est terminé
                    //word = "";
                }
                break;
            case '(': //Opened bracket
                last = current;
                current = new Token("parOpen", 0, lineIndex);
                return current;
            case ')': //Closed bracket
                last = current;
                current = new Token("parClose", 0, lineIndex);
                return current;
            case '{': //Oppened accolade
                last = current;
                current = new Token("accoladeOpen", 0, lineIndex);
                return current;
            case '}': //Closed accolade
                last = current;
                current = new Token("accoladeClose", 0, lineIndex);
                return current;
            case ';': //Closed accolade
                last = current;
                current = new Token("pointVirgule", 0, lineIndex);
                return current;
            case '+': //Plus
                last = current;
                current = new Token("plus", 0, lineIndex);
                return current;
            case '-': //Minus
                last = current;
                current = new Token("minus", 0, lineIndex);
                return current;
            case '*': //Multiply
                last = current;
                current = new Token("multiply", 0, lineIndex);
                return current;
            case '/': //Divide
                last = current;
                current = new Token("divide", 0, lineIndex);
                return current;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g': //Lower case leters
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
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G': //Upper case letters
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
                word = word + lettre; //Concaténation du potentiel mot
                isAWord = true; //On cherche à compléter un mot
                break;


        }
        last = current;
        current = new Token("EOS", 0, lineIndex);
        return current;

    }


    public boolean check(String type) {
        if (current.type == type) {
            last = current;
            current = next();
            return true;
        }
        return false;
    }

    public boolean accept(String type) {
        if (check(type)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String fileName = args[0];
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/" + fileName), "ASCII"));
            String line;
            while ((line = br.readLine()) != null) {
                inside += line;
            }
        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(inside);

    }
}
