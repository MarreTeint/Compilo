import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static Token current = new Token(null, 0, 0);
    public static Token last = new Token(null, 0, 0);
    //Key = operator, [0] = precedence, [1] = associativity
    public static HashMap<String, int[]> symboles = new HashMap<String, int[]>();
    public static String inside = "";
    public static int i = 0;

    public static void next() {


        //Initialisation variables
        String line;
        int lineIndex = 0;
        boolean isAWord = false;
        String word = "";
        boolean isAConst = false;
        String constante = "";


        lineIndex++;
        char lettre = inside.charAt(i);
        i++;
        switch (lettre) {
            case ' ': //Space or end of a word
                last = current;
                current = new Token("espace", 0, lineIndex);
                break;
            case '(': //Opened bracket
                last = current;
                current = new Token("parOpen", 0, lineIndex);
                break;
            case ')': //Closed bracket
                last = current;
                current = new Token("parClose", 0, lineIndex);
                break;
            case '{': //Oppened accolade
                last = current;
                current = new Token("accoladeOpen", 0, lineIndex);
                break;
            case '}': //Closed accolade
                last = current;
                current = new Token("accoladeClose", 0, lineIndex);
                break;
            case ';': //Closed accolade
                last = current;
                current = new Token("pointVirgule", 0, lineIndex);
                break;
            case '+': //Plus
                last = current;
                current = new Token("plus", 0, lineIndex);
                break;
            case '-': //Minus
                last = current;
                current = new Token("minus", 0, lineIndex);
                break;
            case '*': //Multiply
                last = current;
                current = new Token("multiply", 0, lineIndex);
                break;
            case '/': //Divide
                last = current;
                current = new Token("divide", 0, lineIndex);
                break;
            case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
            case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
            case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
            case 's': case 't': case 'u': case 'v': case 'w': case 'x':
            case 'y': case 'z': case 'A': case 'B': case 'C': case 'D':
            case 'E': case 'F': case 'G': case 'H': case 'I': case 'J':
            case 'K': case 'L': case 'M': case 'N': case 'O': case 'P':
            case 'Q': case 'R': case 'S': case 'T': case 'U': case 'V':
            case 'W': case 'X': case 'Y': case 'Z':
                word = word + lettre;
                while((i+1) < inside.length() && (inside.charAt(i) >= 'a' && inside.charAt(i) <= 'z' || inside.charAt(i) >= 'A' && inside.charAt(i) <= 'Z')) {
                    word = word + inside.charAt(i);
                    i++;
                }
                processWord(word, lineIndex);
                word = "";
                break;
            case '1': case '2': case '3': case '4': case '5': case '6':
            case '7': case '8': case '9':
                constante = constante + lettre;
                while((i+1) < inside.length() && (inside.charAt(i) >= '1' && inside.charAt(i) <= '9')) {
                    word = word + inside.charAt(i);
                    i++;
                }
                last = current;
                current = new Token("constante", 0, lineIndex);
                constante = "";
                break;
        }
        last = current;
        current = new Token("EOS", 0, lineIndex);
    }

    public static void initSymboles() {
        symboles.put("=",   new int[]{1, 0});
        symboles.put("==",  new int[]{2, 1});
        symboles.put("!=",  new int[]{2, 1});
        symboles.put("<",   new int[]{2, 1});
        symboles.put(">",   new int[]{2, 1});
        symboles.put("<=",  new int[]{2, 1});
        symboles.put(">=",  new int[]{2, 1});
        symboles.put("+",   new int[]{3, 1});
        symboles.put("-",   new int[]{3, 1});
        symboles.put("*",   new int[]{4, 1});
        symboles.put("/",   new int[]{4, 1});
        symboles.put("(",   new int[]{5, 1});
        symboles.put(")",   new int[]{5, 1});
    }

    public static void processWord(String word, int lineIndex) {
        if (word.equals("return")) {
            last = current;
            current = new Token("return", 0, lineIndex);
        } else if (word.equals("int")) {
            last = current;
            current = new Token("int", 0, lineIndex);
        } else if (word.equals("if")) {
            last = current;
            current = new Token("if", 0, lineIndex);
        }/*else {
            last = current;
            current = new Token("mot", 0, lineIndex);
        }*/
    }

    public static boolean check(String type) {
        if (current.type == type) {
            last = current;
            next();
            return true;
        }
        return false;
    }

    public static boolean accept(String type) {
        if (check(type)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String fileName = args[0];
        initSymboles();
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));//jsp prk plus besoin du src/
            String line;
            while ((line = br.readLine()) != null) {
                inside += line;
            }
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier.");
        }

        System.out.println(inside);
    }
}
