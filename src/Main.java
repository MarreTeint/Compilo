import java.io.*;
import java.util.HashMap;

public class Main {

    public static final String ERR_INTRO = "Error at line";

    public static Token                  current =       new Token(null, 0, 0);
    public static Token                  last =          new Token(null, 0, 0);
    public static HashMap<String, int[]> symboles =      new HashMap<>(); //Key = operator, [0] = precedence, [1] = associativity
    public static String                 inside =        "";
    public static int                    i =             0;
    public static int                    lineIndex =     1;

    public static void next() throws ErrLexical {

        char letter;
        String word = "";
        String constant = "";

        last = current;

        if(i < inside.length()) {
            letter = inside.charAt(i);
            i++;
            switch (letter) {
                case Symbole.ENTER -> {
                                          lineIndex++;
                                          current = new Token(Token.TYPE_SPACE,     0, lineIndex);
                }
                case Symbole.TAB, Symbole.SPACE ->
                                          current = new Token(Token.TYPE_SPACE,     0, lineIndex);
                case Symbole.PAR_OPEN ->  current = new Token(Token.TYPE_PAR_OPEN,  0, lineIndex);
                case Symbole.PAR_CLOSE -> current = new Token(Token.TYPE_PAR_CLOSE, 0, lineIndex);
                case Symbole.ACC_OPEN ->  current = new Token(Token.TYPE_ACC_OPEN,  0, lineIndex);
                case Symbole.ACC_CLOSE -> current = new Token(Token.TYPE_ACC_CLOSE, 0, lineIndex);
                case Symbole.SEMICOLON -> current = new Token(Token.TYPE_SEMICOL,   0, lineIndex);
                case Symbole.COMA ->      current = new Token(Token.TYPE_COMA,      0, lineIndex);
                case Symbole.PLUS ->      current = new Token(Token.TYPE_PLUS,      0, lineIndex);
                case Symbole.MINUS ->     current = new Token(Token.TYPE_MINUS,     0, lineIndex);
                case Symbole.MULTIPLY ->  current = new Token(Token.TYPE_MULTIPLY,  0, lineIndex);
                case Symbole.DIVIDE ->    current = new Token(Token.TYPE_DIVIDE,    0, lineIndex);
                case Symbole.NOT -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.AFFECTATION) {
                        current = new Token(Token.TYPE_DIFF, 0, lineIndex);
                    } else {
                        current = new Token(Token.TYPE_NOT, 0, lineIndex);
                    }
                }
                case Symbole.AFFECTATION -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.AFFECTATION) {
                        current = new Token(Token.TYPE_COMP, 0, lineIndex);
                        i++;
                    } else {
                        current = new Token(Token.TYPE_AFFECTATION, 0, lineIndex);
                    }
                }
                case Symbole.INF -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.AFFECTATION) {
                        current = new Token(Token.TYPE_INF_EGAL, 0, lineIndex);
                        i++;
                    } else {
                        current = new Token(Token.TYPE_INF, 0, lineIndex);
                    }
                }
                case Symbole.SUP -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.AFFECTATION) {
                        current = new Token(Token.TYPE_SUP_EGAL, 0, lineIndex);
                        i++;
                    } else {
                        current = new Token(Token.TYPE_SUP, 0, lineIndex);
                    }
                }
                case Symbole.SINGLE_AND -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.SINGLE_AND) {
                        current = new Token(Token.TYPE_AND, 0, lineIndex);
                        i++;
                    } else {
                        throw new ErrLexical(ERR_INTRO + " " + lineIndex + ". '" + Symbole.SINGLE_AND + "' is not a valid operator");
                    }
                }
                case Symbole.SINGLE_OR -> {
                    if (i < inside.length() && inside.charAt(i) == Symbole.SINGLE_OR) {
                        current = new Token(Token.TYPE_OR, 0, lineIndex);
                        i++;
                    } else {
                        throw new ErrLexical(ERR_INTRO + " " + lineIndex + ". '" + Symbole.SINGLE_OR + "' is not a valid operator");
                    }
                }
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                    word = word + letter;
                    while ((i) < inside.length() && (inside.charAt(i) >= 'a' && inside.charAt(i) <= 'z' || inside.charAt(i) >= 'A' && inside.charAt(i) <= 'Z')) {
                        word = word + inside.charAt(i);
                        i++;
                    }
                    try {
                        processWord(word, lineIndex);
                    } catch (ErrLexical e) {
                        System.out.println(e.getMessage());
                    }
                    word = "";
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    constant = constant + letter;
                    while ((i) < inside.length() && (inside.charAt(i) >= '1' && inside.charAt(i) <= '9')) {
                        constant = constant + inside.charAt(i);
                        i++;
                    }
                    current = new Token(Token.TYPE_CONSTANT, 0, lineIndex);
                    constant = "";
                }
            /*default:
                current = new Token(Token.TYPE_EOS, 0, lineIndex);*/
            }
        }
        else{
            current = new Token(Token.TYPE_EOS, 0, lineIndex);
        }
        //Debug
        System.out.println("Current token : " + current.getType());

    }

    public static void initSymboles() {
        symboles.put(String.valueOf(Symbole.AFFECTATION), new int[]{1, 0});
        symboles.put(               Symbole.COMP,         new int[]{2, 1});
        symboles.put(               Symbole.DIFF,         new int[]{2, 1});
        symboles.put(String.valueOf(Symbole.INF),         new int[]{2, 1});
        symboles.put(String.valueOf(Symbole.SUP),         new int[]{2, 1});
        symboles.put(               Symbole.INF_EGAL,     new int[]{2, 1});
        symboles.put(               Symbole.SUP_EGAL,     new int[]{2, 1});
        symboles.put(String.valueOf(Symbole.PLUS),        new int[]{3, 1});
        symboles.put(String.valueOf(Symbole.MINUS),       new int[]{3, 1});
        symboles.put(String.valueOf(Symbole.MULTIPLY),    new int[]{4, 1});
        symboles.put(String.valueOf(Symbole.DIVIDE),      new int[]{4, 1});
        symboles.put(String.valueOf(Symbole.PAR_OPEN),    new int[]{5, 1});
        symboles.put(String.valueOf(Symbole.PAR_CLOSE),   new int[]{5, 1});
    }

    public static void processWord(String word, int lineIndex) throws ErrLexical {
        //ATTENTION, ne pas utiliser les Token.type pour RECONNAITRE autre chose que des mots
        switch (word) {
            case Token.TYPE_RETURN ->   current = new Token(Token.TYPE_RETURN, 0, lineIndex);
            case Token.TYPE_INT ->      current = new Token(Token.TYPE_INT, 0, lineIndex);
            case Token.TYPE_IF ->       current = new Token(Token.TYPE_IF, 0, lineIndex);
            case Token.TYPE_ELSE ->     current = new Token(Token.TYPE_ELSE, 0, lineIndex);
            case Token.TYPE_WHILE ->    current = new Token(Token.TYPE_WHILE, 0, lineIndex);
            case Token.TYPE_FOR ->      current = new Token(Token.TYPE_FOR, 0, lineIndex);
            case Token.TYPE_DO ->       current = new Token(Token.TYPE_DO, 0, lineIndex);
            case Token.TYPE_BREAK ->    current = new Token(Token.TYPE_BREAK, 0, lineIndex);
            case Token.TYPE_CONTINUE -> current = new Token(Token.TYPE_CONTINUE, 0, lineIndex);
            default ->                  current = new Token(Token.TYPE_IDENT, 0, lineIndex);
            //todo add identifier to the token
        }
        //throw new ErrLexical("Unknown word", lineIndex);
    }

    public static boolean check(String type) throws ErrLexical {
        while (current.type.equals(Token.TYPE_SPACE)) {
            next();
        }
        if (current.type.equals(type)) {
            last = current;
            next();
            return true;
        }
        return false;
    }

    public static void accept(String type) throws ErrSyntaxique, ErrLexical {
        //ignore token espace
        if (!check(type)) {
            throw new ErrSyntaxique(ERR_INTRO + " " + current.getLigne() + ". Expected " + type + " but found " + current.type);
        }
    }

    //Analyse syntaxique

    public static Node Syntaxe() throws ErrSyntaxique, ErrLexical {
        next();
        if(check(Token.TYPE_EOS)){
            return null;
        }
        Node N = new Node();
        while (!current.type.equals(Token.TYPE_EOS)) {
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
        if(check(Token.TYPE_INT)){
            if(check(Token.TYPE_IDENT)){
                if(check(Token.TYPE_PAR_OPEN)){
                    accept(Token.TYPE_PAR_CLOSE);
                    Node n = new Node(Node.TYPE_FUNCTION, current.getValeur());
                    n.addSon(Instruction());
                    return n;
                }
            }
        }
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
                Node n = new Node(Node.TYPE_IF, 0);
                n.addSon(texte);
                n.addSon(then);
                n.addSon(elsee);
                return n;
            }
        }
        else if(check(Token.TYPE_ACC_OPEN)){
            Node n = new Node(Node.TYPE_BLOCK, 0);
            while(!check(Token.TYPE_ACC_CLOSE)){
                n.addSon(Instruction());
            }
            return n;
        } else if (check(Token.TYPE_INT)) {

                Node n = new Node (Node.TYPE_DECLARATION,0);
                boolean passed = false;
                while(!check(Token.TYPE_SEMICOL)){
                    if(passed){
                        accept(Token.TYPE_COMA);
                    }
                    else {
                        passed = true;
                    }
                    n.addSon(Expression());
                }
                return n;

        } else if (check(Token.TYPE_RETURN)) {
            Node n = new Node(Node.TYPE_RETURN, 0);
            n.addSon(Expression());
            accept(Token.TYPE_SEMICOL);
            return n;
        } else if(check(Token.TYPE_WHILE)){
            Node n = new Node(Node.TYPE_LOOP, 0);
            Node m = new Node(Node.TYPE_CONDITION, 0);
            n.addSon(m);
            accept(Token.TYPE_PAR_OPEN);
            m.addSon(Expression());
            accept(Token.TYPE_PAR_CLOSE);
            m.addSon(Instruction());
            m.addSon(new Node(Node.TYPE_BREAK, 0));
            return n;
        }
        else if (check(Token.TYPE_FOR)) {
            Node n = new Node(Node.TYPE_SEQUENCE,0);
            Node m = new Node(Node.TYPE_LOOP, 0);
            Node p = new Node(Node.TYPE_CONDITION, 0);
            accept(Token.TYPE_PAR_OPEN);
            n.addSon(Expression());
            accept(Token.TYPE_SEMICOL);
            n.addSon(m);
            p.addSon(Expression());
            accept(Token.TYPE_COMA);
            Node temp = Expression();
            accept(Token.TYPE_PAR_CLOSE);
            m.addSon(Instruction());
            m.addSon(temp);
            m.addSon(p);
            return n;

        }
        else if(check(Token.TYPE_DO)){
            Node n = new Node(Node.TYPE_LOOP, 0);
            n.addSon(Instruction());
            Node m = new Node(Node.TYPE_CONDITION, 0);
            n.addSon(m);
            accept(Token.TYPE_WHILE);
            accept(Token.TYPE_PAR_OPEN);
            m.addSon(Expression());
            accept(Token.TYPE_PAR_CLOSE);
            accept(Token.TYPE_SEMICOL);
            m.addSon(new Node(Node.TYPE_BREAK, 0));
            return n;
        }
        Node n = Expression();
        accept(Token.TYPE_SEMICOL);
        Node N = new Node(Node.TYPE_DROP, 0);
        N.addSon(n);
        return N;
    }
    static Node Expression() throws ErrSyntaxique, ErrLexical {
        Node N = Prefix();
        if(check(Token.TYPE_PLUS)){
            Node n = new Node(Node.TYPE_PLUS, 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_MINUS)){
            Node n = new Node(Node.TYPE_MINUS, 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_MULTIPLY)){
            Node n = new Node(Node.TYPE_MULTIPLY, 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        }
        else if(check(Token.TYPE_DIVIDE)){
            Node n = new Node(Node.TYPE_DIVIDE, 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        } else if (check(Token.TYPE_AFFECTATION)) {
            Node n = new Node(Node.TYPE_AFFECTATION, 0);
            n.addSon(N);
            n.addSon(Expression());
            return n;
        } else if (check(Token.TYPE_COMP)) {
            Node n = new Node(Node.TYPE_COMP, 0);
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
            Node M = new Node(Node.TYPE_MINUS, 0);
            M.addSon(N);
            return M;
        }
        else if(check(Token.TYPE_PLUS)){
            Node N = Prefix();
            Node M = new Node(Node.TYPE_PLUS, 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_MULTIPLY)){
            Node N = Prefix();
            Node M = new Node(Node.TYPE_MULTIPLY, 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_DIVIDE)){
            Node N = Prefix();
            Node M = new Node(Node.TYPE_DIVIDE, 0);
            M.addSon(N);
            return M;
        }
        else if (check(Token.TYPE_NOT)){
            Node N = Prefix();
            Node M = new Node(Node.TYPE_NOT, 0);
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
            return new Node(Node.TYPE_CONSTANT, current.getValeur());
        }
        else if(check(Token.TYPE_PAR_OPEN)) {
            //check if next is an expression
            next();
            Node N = Expression();
            if (!check(Token.TYPE_PAR_CLOSE)) {
                throw new ErrSyntaxique(ERR_INTRO + " " + current.getLigne() + ". Missing closing parenthesis");
            }
            next();
            return N;
        } else if (check(Token.TYPE_IDENT)) {
             if(check(Token.TYPE_PAR_OPEN)){
                Node n = new Node(Node.TYPE_CALL, current.getValeur());
                //TODO add parameters as children of n
                accept(Token.TYPE_PAR_CLOSE);
                return n;
            }
            else{
                return new Node(Node.TYPE_VAR, current.getValeur());
            }
        } else{
            throw new ErrSyntaxique(ERR_INTRO + " " + current.getLigne() + ". Not a valid expression");
        }
    }

    //Analyse sémantique
    static void ASem() throws ErrSyntaxique, ErrLexical {
        int nvar = 0;
        Node N = Syntaxe();
        /*ASemNode(N);
        N.nvar = nvar;*/
    }

    //Génération de code
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
                inside += '\n';
            }
        } catch (IOException e) {
            System.out.println("Error : File unfound");
            return;
        }
        try {
            Syntaxe();
        } catch (ErrSyntaxique ErrSyntaxique) {
            System.out.println("Syntax error : " + ErrSyntaxique.getMessage());
        } catch (ErrLexical e) {
            throw new RuntimeException(e);
        }
        //System.out.println(inside);
        String fileOut = args[1];
        try {
            genCode(args[1], new Node(fileOut, 0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}