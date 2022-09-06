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
        last = current;

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

    //Analyse syntaxique

    public Node Syntaxe() throws errSyntaxique {
        if(check("EOS")){
            return null;
        }
        Node N = Global();
        return N;
    }

    Node Global() throws errSyntaxique {
        return  Function();
    }
    Node Function() throws errSyntaxique {
        return Instruction();
    }
    Node Instruction() throws errSyntaxique {
        if(check("if")){
            accept("parOpen");
            Node texte = Expression();
            accept("parClose");
            Node then = Instruction();
            if(check("else")){
                Node elsee = Instruction();
                Node n = new Node("if", 0);
                n.addSon(texte);
                n.addSon(then);
                n.addSon(elsee);
                return n;
            }
        }
        else if(check("accoladeopen")){
            Node n = new Node("block", 0);
            while(!check("accoladeclose")){
                n.addSon(Instruction());
            }
            return n;
        }
        Node n = Expression();
        accept("pointVirgule");
        Node N = new Node("drop", 0);
        N.addSon(n);
        return N;
    }
    //TODO faire ca
    Node Expression() throws errSyntaxique {
        Node N = Prefix();
        if(check("plus")){
            Node n = new Node("add", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check("minus")){
            Node n = new Node("sub", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check("multiply")){
            Node n = new Node("mul", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check("divide")){
            Node n = new Node("div", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else{
            return N;
        }
    }
    Node Prefix() throws errSyntaxique {
        if(check("moins")){
            Node N = Prefix();
            Node M = new Node("moins", 0);
            M.addSon(N);
            return M;
        }
        else if(check("plus")){
            Node N = Prefix();
            Node M = new Node("plus", 0);
            M.addSon(N);
            return M;
        }
        else if (check("multiply")){
            Node N = Prefix();
            Node M = new Node("multiply", 0);
            M.addSon(N);
            return M;
        }
        else if (check("divide")){
            Node N = Prefix();
            Node M = new Node("divide", 0);
            M.addSon(N);
            return M;
        }
        else if (check("negation")){
            Node N = Prefix();
            Node M = new Node("negation", 0);
            M.addSon(N);
            return M;
        }
        else{
            return Suffix();
        }
    }
    Node Suffix() throws errSyntaxique {
        return Atome();
    }
    Node Atome() throws errSyntaxique {
        if(check("number")){
            return new Node("number", current.getValeur());
        }
        else if(check("parOpen")) {
            //check if next is an expression
            next();
            Node N = Expression();
            if (!check("parClose")) {
                throw new errSyntaxique("Missing closing parenthesis");
            }
            next();
            return N;
        }
        else{
            throw new errSyntaxique("Not a valid expression");
        }
    }

    //Analyse sémantique

    //Génration de code
    public static void main(String[] args) {

        String fileName = args[0];
        initSymboles();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));//jsp prk plus besoin du src/
            String line;
            while ((line = br.readLine()) != null) {
                inside += line;
                inside+='\n';
            }
        } catch (IOException e) {
            System.out.println("Erreur : Fichier introuvable");
        }

        System.out.println(inside);
    }
}
