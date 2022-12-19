import java.util.ArrayList;

public class Node {
    //TODO : add a field for the type of the node ident with adress and variable
    private String type;//name is for variable
    private ArrayList<Node> listeFils;
    private int ligne;
    private int value;
    private String chaine;
    private int emplacement;
    private int nombreEmplacement;

    public Node(String type, int ligne, int value) {
        this.type = type;
        this.ligne = ligne;
        this.value = value;
        this.listeFils = new ArrayList<Node>();
    }

    public Node(){

    }
    public void print() {
        System.out.println("Type: " + type + " Value: " + value);
    }

    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public ArrayList<Node> getListeFils() {
        return listeFils;
    }

    public void addFils(Node fils) {
        listeFils.add(fils);
    }

    public static void printTree(Node Parent,int depth){
        System.out.println("Type: " + Parent.getType()+" Depth " +depth);

        for (int i = 0; i < Parent.getListeFils().size(); i++) {
            printTree(Parent.getListeFils().get(i),depth+1);
        }
    }

    public static final String TYPE_IF =          Token.TYPE_IF;
    public static final String TYPE_BLOCK =       "block";
    public static final String TYPE_DECLARATION = "declaration";
    public static final String TYPE_LOOP =        "loop";
    public static final String TYPE_CONDITION =   "condition";
    public static final String TYPE_BREAK =       Token.TYPE_BREAK;
    public static final String TYPE_SEQUENCE =    "sequence";
    public static final String TYPE_DROP =        "drop";
    public static final String TYPE_PLUS =        Token.TYPE_PLUS;
    public static final String TYPE_MINUS =       Token.TYPE_MINUS;
    public static final String TYPE_MULTIPLY =    Token.TYPE_MULTIPLY;
    public static final String TYPE_DIVIDE =      Token.TYPE_DIVIDE;
    public static final String TYPE_AFFECTATION = Token.TYPE_AFFECTATION;
    public static final String TYPE_COMP =        Token.TYPE_COMP;
    public static final String TYPE_NOT =         Token.TYPE_NOT;
    public static final String TYPE_CONSTANT =    Token.TYPE_CONSTANT;
    public static final String TYPE_IDENT =       Token.TYPE_IDENT;
    public static final String TYPE_VAR =         "var";
    public static String TYPE_CALL =              "call";
    public static String TYPE_FUNCTION =          "function";
    public static String TYPE_RETURN =            Token.TYPE_RETURN;
    public static String TYPE_INDIRECTION =       "indirection";
    public static String TYPE_ADDRESS =           Token.TYPE_ADDRESS;
    public static String TYPE_OR =                Token.TYPE_OR;
    public static String TYPE_AND =               Token.TYPE_AND;
    public static String TYPE_INF =               Token.TYPE_INF;
    public static String TYPE_INF_EGAL =          Token.TYPE_INF_EGAL;
    public static String TYPE_SUP =               Token.TYPE_SUP;
    public static String TYPE_SUP_EGAL =          Token.TYPE_SUP_EGAL;
    public static String TYPE_DIFF =              Token.TYPE_DIFF;
    public static String TYPE_MODULO =            Token.TYPE_MODULO;
}
