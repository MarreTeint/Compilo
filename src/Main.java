import java.io.*;
import java.util.HashMap;

public class Main {

    public static Token current = new Token(null, 0, 0);
    public static Token last = new Token(null, 0, 0);
    //Key = operator, [0] = precedence, [1] = associativity
    public static HashMap<String, int[]> symboles = new HashMap<String, int[]>();
    public static String inside = "";
    public static int i = 0;

    public static void next() throws ErrLexical {
        last = current;

        //Initialisation variables
        String line;
        int lineIndex = 0;
        boolean isAWord = false;
        String word = "";
        boolean isAConst = false;
        String constante = "";


        lineIndex++;
        char lettre;
        if(i < inside.length()) {
            lettre = inside.charAt(i);

        i++;
        switch (lettre) {
            case '\n':
            case '\t':
            case ' ': //Space or end of a word
                last = current;
                current = new Token(Token.TYPE_SPACE, 0, lineIndex);
                break;
            case '(': //Opened bracket
                last = current;
                current = new Token(Token.TYPE_PAR_OPEN, 0, lineIndex);
                break;
            case ')': //Closed bracket
                last = current;
                current = new Token(Token.TYPE_PAR_CLOSE, 0, lineIndex);
                break;
            case '{': //Oppened accolade
                last = current;
                current = new Token(Token.TYPE_ACC_OPEN, 0, lineIndex);
                break;
            case '}': //Closed accolade
                last = current;
                current = new Token(Token.TYPE_ACC_CLOSE, 0, lineIndex);
                break;
            case ';': //Closed accolade
                last = current;
                current = new Token(Token.TYPE_POINT_VIRGULE, 0, lineIndex);
                break;
            case ',':
                last = current;
                current = new Token(Token.TYPE_VIRGULE, 0, lineIndex);
                break;
            case '+': //Plus
                last = current;
                current = new Token(Token.TYPE_PLUS, 0, lineIndex);
                break;
            case '-': //Minus
                last = current;
                current = new Token(Token.TYPE_MINUS, 0, lineIndex);
                break;
            case '*': //Multiply
                last = current;
                current = new Token(Token.TYPE_MULTIPLY, 0, lineIndex);
                break;
            case '/': //Divide
                last = current;
                current = new Token(Token.TYPE_DIVIDE, 0, lineIndex);
                break;
            case '!'://Not
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '=') {
                    current = new Token(Token.TYPE_DIFF, 0, lineIndex);
                } else {
                    current = new Token(Token.TYPE_NOT, 0, lineIndex);
                }
                break;
            case '='://Affectation
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '=') {
                    current = new Token(Token.TYPE_COMP, 0, lineIndex);
                } else {
                    current = new Token(Token.TYPE_AFFECTATION, 0, lineIndex);
                }
                break;
            case '<'://Less than
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '=') {
                    current = new Token(Token.TYPE_INF_EGAL, 0, lineIndex);
                } else {
                    current = new Token(Token.TYPE_INF, 0, lineIndex);
                }
                break;
            case '>'://Greater than
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '=') {
                    current = new Token(Token.TYPE_SUP_EGAL, 0, lineIndex);
                } else {
                    current = new Token(Token.TYPE_SUP, 0, lineIndex);
                }
                break;
            case '&':
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '&') {
                    current = new Token(Token.TYPE_AND, 0, lineIndex);
                } else {
                    throw new ErrLexical("Error at line " + lineIndex + ": & is not a valid operator");
                }
                break;
            case '|':
                last = current;
                if(i+1 < inside.length() && inside.charAt(i+1) == '|') {
                    current = new Token(Token.TYPE_OR, 0, lineIndex);
                } else {
                    throw new ErrLexical("Error at line " + lineIndex + ": | is not a valid operator");
                }
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
                try {
                    processWord(word, lineIndex);
                }
                catch (ErrLexical e) {
                    System.out.println(e.getMessage());
                }
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
                current = new Token(Token.TYPE_CONSTANT, 0, lineIndex);
                constante = "";
                break;
            /*default:
                current = new Token(Token.TYPE_EOS, 0, lineIndex);*/
        }
        }
        else{
            current = new Token(Token.TYPE_EOS, 0, lineIndex);
        }

        System.out.println("Current token : " + current.getType());
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

    public static void processWord(String word, int lineIndex) throws ErrLexical {
        if (word.equals(Token.TYPE_RETURN)) { //TODO : ATTENTION, ne pas utiliser les Token.type pour RECONAITRE autre chose que des mots (mais bien utiliser pour le reste)
            last = current;
            current = new Token(Token.TYPE_RETURN, 0, lineIndex);
        } else if (word.equals(Token.TYPE_INT)) {
            last = current;
            current = new Token(Token.TYPE_INT, 0, lineIndex);
        } else if (word.equals(Token.TYPE_IF)) {
            last = current;
            current = new Token(Token.TYPE_IF, 0, lineIndex);
        } else if (word.equals(Token.TYPE_ELSE)) {
            last = current;
            current = new Token(Token.TYPE_ELSE, 0, lineIndex);
        } else if (word.equals(Token.TYPE_WHILE)) {
            last = current;
            current = new Token(Token.TYPE_WHILE, 0, lineIndex);
        } else if (word.equals(Token.TYPE_FOR)) {
            last = current;
            current = new Token(Token.TYPE_FOR, 0, lineIndex);
        } else if (word.equals(Token.TYPE_DO)) {
            last = current;
            current = new Token(Token.TYPE_DO, 0, lineIndex);
        } else if (word.equals(Token.TYPE_BREAK)) {
            last = current;
            current = new Token(Token.TYPE_BREAK, 0, lineIndex);
        } else if (word.equals(Token.TYPE_CONTINUE)) {
            last = current;
            current = new Token(Token.TYPE_CONTINUE, 0, lineIndex);
        }
        else {
            last = current;
            current = new Token(Token.TYPE_IDENT, 0, lineIndex);
        }
        //throw new ErrLexical("Unknown word", lineIndex);
    }

    public static boolean check(String type) throws ErrLexical {
        while (current.type == Token.TYPE_SPACE) {
            next();
        }
        if (current.type == type) {
            last = current;
            next();
            return true;
        }
        return false;
    }





    public static boolean accept(String type) throws ErrSyntaxique, ErrLexical {
        //ignore token espace

        if (check(type)) {
            return true;
        }
        throw new ErrSyntaxique("Expected " + type + " but found " + current.type);

    }

    //Analyse syntaxique

    public static Node Syntaxe() throws ErrSyntaxique, ErrLexical {
        next();
        if(check(Token.TYPE_EOS)){
            return null;
        }
        Node N = new Node();
        while (current.type != Token.TYPE_EOS) {
            N = Global();
            next();
        }
        Node.printTree(N,0);
        return N;
    }

    static Node Global() throws ErrSyntaxique, ErrLexical {
        return  Function();
    }
    static Node Function() throws ErrSyntaxique, ErrLexical {
        return Instruction();
    }
    static Node Instruction() throws ErrSyntaxique, ErrLexical {
        if(check(Token.TYPE_IF)){
            accept(Token.TYPE_PAR_OPEN);
            Node texte = Expression();
            accept(Token.TYPE_PAR_CLOSE);
            Node then = Instruction();
            if(check(Token.TYPE_ELSE)){
                Node elsee = Instruction();
                Node n = new Node("if", 0);
                n.addSon(texte);
                n.addSon(then);
                n.addSon(elsee);
                return n;
            }
        }
        else if(check(Token.TYPE_ACC_OPEN)){
            Node n = new Node("block", 0);
            while(!check(Token.TYPE_ACC_CLOSE)){
                n.addSon(Instruction());
            }
            return n;
        } else if (check(Token.TYPE_INT)) {
            Node n = new Node ("declaration",0);
            boolean passed = false;
            while(!check(Token.TYPE_POINT_VIRGULE)){
                if(passed){
                    accept(Token.TYPE_VIRGULE);
                }
                else {
                    passed = true;
                }
                n.addSon(Expression());
            }
            return n;


        }
        else if(check(Token.TYPE_WHILE)){
            Node n = new Node("loop", 0);
            Node m = new Node("condition", 0);
            n.addSon(m);
            accept(Token.TYPE_PAR_OPEN);
            m.addSon(Expression());
            accept(Token.TYPE_PAR_CLOSE);
            m.addSon(Instruction());
            m.addSon(new Node("break", 0));
            return n;
        }
        else if (check(Token.TYPE_FOR)) {
            Node n = new Node("Sequence",0);
            Node m = new Node("loop", 0);
            Node p = new Node("condition", 0);
            accept(Token.TYPE_PAR_OPEN);
            n.addSon(Expression());
            accept(Token.TYPE_POINT_VIRGULE);
            n.addSon(m);
            p.addSon(Expression());
            accept(Token.TYPE_POINT_VIRGULE);
            Node temp = Expression();
            accept(Token.TYPE_PAR_CLOSE);
            m.addSon(Instruction());
            m.addSon(temp);
            m.addSon(p);
            return n;

        }
        else if(check(Token.TYPE_DO)){
            Node n = new Node("loop", 0);
            n.addSon(Instruction());
            Node m = new Node("condition", 0);
            n.addSon(m);
            accept(Token.TYPE_WHILE);
            accept(Token.TYPE_PAR_OPEN);
            m.addSon(Expression());
            accept(Token.TYPE_PAR_CLOSE);
            accept(Token.TYPE_POINT_VIRGULE);
            m.addSon(new Node("break", 0));
            return n;
        }
        Node n = Expression();
        accept(Token.TYPE_POINT_VIRGULE);
        Node N = new Node("drop", 0);
        N.addSon(n);
        return N;
    }
    static Node Expression() throws ErrSyntaxique, ErrLexical {
        Node N = Prefix();
        if(check(Token.TYPE_PLUS)){
            Node n = new Node("plus", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_MINUS)){
            Node n = new Node("moins", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_MULTIPLY)){
            Node n = new Node("multiplication", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_DIVIDE)){
            Node n = new Node("division", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        } else if (check(Token.TYPE_AFFECTATION)) {
            Node n = new Node("affectation", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        } else if (check(Token.TYPE_COMP)) {
            Node n = new Node("comparaison", 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        } else{
            return N;
        }
    }
    static Node Prefix() throws ErrSyntaxique, ErrLexical {
        if(check(Token.TYPE_MINUS)){
            Node N = Prefix();
            Node M = new Node("moins", 0);
            M.addSon(N);
            return M;
        }
        else if(check(Token.TYPE_PLUS)){
            Node N = Prefix();
            Node M = new Node("plus", 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_MULTIPLY)){
            Node N = Prefix();
            Node M = new Node("multiplication", 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_DIVIDE)){
            Node N = Prefix();
            Node M = new Node("division", 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_NOT)){
            Node N = Prefix();
            Node M = new Node("negation", 0);
            M.addSon(N);
            return M;
        }
        else{
            return Suffix();
        }
    }
    static Node Suffix() throws ErrSyntaxique, ErrLexical {
        return Atome();
    }
    static Node Atome() throws ErrSyntaxique, ErrLexical {
        if(check(Token.TYPE_CONSTANT)){
            return new Node("constante", current.getValeur());
        }
        else if(check(Token.TYPE_PAR_OPEN)) {
            //check if next is an expression
            next();
            Node N = Expression();
            if (!check(Token.TYPE_PAR_CLOSE)) {
                throw new ErrSyntaxique("Missing closing parenthesis");
            }
            next();
            return N;
        } else if (check(Token.TYPE_IDENT)) {
            return new Node ("var",0 );

        } else{
            throw new ErrSyntaxique("Not a valid expression atome");
        }
    }

    //Analyse sémantique
    static void ASem() throws ErrSyntaxique, ErrLexical {
        int nvar = 0;
        Node N = Syntaxe();
        /*ASemNode(N);
        N.nvar = nvar;*/
    }

    //Génration de code
    static void genCode(String fileName, Node codeTree) throws IOException {
        String code = "";
        /*do{
            code += genNode(codeTree);
        }while(current.type != Token.TYPE_EOS);*/
        code += ".start\n";
        code += "prep main\n";
        code += "call 0\n";
        code += "halt\n";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(code);
        fileWriter.close();
    }
    public static void main(String[] args) {

        String fileName = args[0];
        initSymboles();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));
            String line;
            while ((line = br.readLine()) != null) {
                inside += line;
                inside+='\n';
            }
        } catch (IOException e) {
            System.out.println("Erreur : Fichier introuvable");
        }
        try {
            Syntaxe();
        } catch (ErrSyntaxique ErrSyntaxique) {
            System.out.println("Erreur Syntaxique : " + ErrSyntaxique.getMessage());
        } catch (ErrLexical e) {
            throw new RuntimeException(e);
        }
        //System.out.println(inside);
        String fileOut = args[1];
        try {
            genCode(args[1], new Node("test", 0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


