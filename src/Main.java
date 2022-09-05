import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    Token current = new Token(null, 0, 0);
    Token last = new Token(null, 0, 0);
    //Key = operator, [0] = precedence, [1] = associativity
    HashMap<String, int[]> symboles = new HashMap<String, int[]>();
    initSymboles();
    String inside = "";
    int i = 0;

    public void next() {


        //Initialisation variables
        String line;
        int lineIndex = 0;
        boolean isAWord = false;
        String word = "";
        boolean isANumber = false;
        String number = "";


        lineIndex++;
        char lettre = inside.charAt(i);
        i++;
        switch (lettre) {
            case ' ': //Space or end of a word
                if (isAWord) {
                    isAWord = false; //Le mot est terminé
                    processWord(word, lineIndex);
                    word = "";
                }
                if (isANumber) {
                    isANumber = false; //Le mot est terminé
                    last = current;
                    current = new Token("number", 0, lineIndex);
                    number = "";
                }
                break;
            case '(': //Opened bracket
                last = current;
                current = new Token("parOpen", 0, lineIndex);
            case ')': //Closed bracket
                last = current;
                current = new Token("parClose", 0, lineIndex);
            case '{': //Oppened accolade
                last = current;
                current = new Token("accoladeOpen", 0, lineIndex);
            case '}': //Closed accolade
                last = current;
                current = new Token("accoladeClose", 0, lineIndex);
            case ';': //Closed accolade
                last = current;
                current = new Token("pointVirgule", 0, lineIndex);
            case '+': //Plus
                last = current;
                current = new Token("plus", 0, lineIndex);
            case '-': //Minus
                last = current;
                current = new Token("minus", 0, lineIndex);
            case '*': //Multiply
                last = current;
                current = new Token("multiply", 0, lineIndex);
            case '/': //Divide
                last = current;
                current = new Token("divide", 0, lineIndex);
            /*case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
            case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
            case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
            case 's': case 't': case 'u': case 'v': case 'w': case 'x':
            case 'y': case 'z': case 'A': case 'B': case 'C': case 'D':
            case 'E': case 'F': case 'G': case 'H': case 'I': case 'J':
            case 'K': case 'L': case 'M': case 'N': case 'O': case 'P':
            case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V':
            case 'W': case 'X': case 'Y': case 'Z':
                if (isANumber) {
                    isANumber = false; //Le mot est termin"
                    last = current;
                    current = new Token("number", 0, lineIndex);
                    number = "";
                }
                word = word + lettre; //Concaténation du potentiel mot
                isAWord = true; //On cherche à compléter un mot
                break;*/
            case '1': case '2': case '3': case '4': case '5': case '6':
            case '7': case '8': case '9':
                if(isAWord) {
                    isAWord = false;
                    processWord(word, lineIndex);
                    word = "";
                }
                number = number + lettre; //Concaténation du potentiel nombre
                isANumber = true;
                break;
        }
        last = current;
        current = new Token("EOS", 0, lineIndex);
    }

    public void initSymboles() {
        symboles.put("=", new int[]{1, 0});
        symboles.put("==", new int[]{2, 1});
        symboles.put("!=", new int[]{2, 1});
        symboles.put("<", new int[]{2, 1});
        symboles.put(">", new int[]{2, 1});
        symboles.put("<=", new int[]{2, 1});
        symboles.put(">=", new int[]{2, 1});
        symboles.put("+", new int[]{3, 1});
        symboles.put("-", new int[]{3, 1});
        symboles.put("*", new int[]{4, 1});
        symboles.put("/", new int[]{4, 1});
        symboles.put("(", new int[]{5, 1});
        symboles.put(")", new int[]{5, 1});
    }



    public void processWord(String word, int lineIndex) {
        if (word.equals("return")) {
            last = current;
            current = new Token("return", 0, lineIndex);
        } else if (word.equals("int")) {
            last = current;
            current = new Token("int", 0, lineIndex);
        } else {
            last = current;
            current = new Token("mot", 0, lineIndex);
        }
    }

    public boolean check(String type) {
        if (current.type == type) {
            last = current;
            next();
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

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));//jsp prk plus besoin du src/
            String line;
            while ((line = br.readLine()) != null) {
                inside += line;
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
        }

        System.out.println(inside);
    }
}
