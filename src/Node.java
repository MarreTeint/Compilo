public class Node {
    //TODO : add a field for the type of the node ident with adress and variable
    private String type;//name is for variable
    private int value,adresse;
    private Node[] sons;

    public Node(String type, int value) {
        this.type = type;
        this.value = value;
        this.sons = new Node[0];
    }
    public Node(String type, int value, Node[] sons) {
        this.type = type;
        this.value = value;
        this.sons = sons;
    }
    public Node(String type, String name, int adresse) {
        this.type = type;
        this.value = value;
        this.adresse = adresse;
        this.sons = new Node[0];
    }

    public Node(){

    }
    public void print() {
        System.out.println("Type: " + type + " Value: " + value);
    }
    public String toCode(){
        String code;
        switch (this.type) {
            case TYPE_DECLARATION:
                code="";
                //declare here vars in hashmap
                break;
            case TYPE_CONSTANT, TYPE_VAR:
                code="push " + this.value;// if var, value is modified by semantic analysis or mb took it in hashmap
                break;
            case TYPE_DROP:
                code="drop"; // Not sure about this one
                break;
            case TYPE_PLUS:
                code="add";
                break;
            case TYPE_DIVIDE:
                code="divide";
                break;
            case TYPE_MULTIPLY:
                code="multiply";
                break;
            case TYPE_MINUS:
                code="minus";
                break;
            default:
                code="";
                break;
            //TODO: case if, while, ...
        };
        return code+'\n';
    }
    public static String Read(Node N){
        String code = "";
        if(N.getType() == TYPE_IF) { // Not sure if this is how we learnd it, need to have a look
            if (/*child 1 return smting >0*/) {
                code = code + Read(N.sons[1]);
            }
            else if(N.sons.length == 3){
                code = code + Read(N.sons[2]);
            }
            return code;
        }
        for(int i = 0 ; i<N.sons.length ; i++){
            if(N.getType() == TYPE_DECLARATION){
                break;
            }
            code = code + Read(N.sons[i]);
        }
        code = code + N.toCode();
        return code;
    }
    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public Node[] getSons() {
        return sons;
    }

    public void addSon(Node son) {
        Node[] newSons = new Node[sons.length + 1];
        for (int i = 0; i < sons.length; i++) {
            newSons[i] = sons[i];
        }
        newSons[sons.length] = son;
        sons = newSons;
    }

    public static void printTree(Node Parent,int depth){
        System.out.println("Type: " + Parent.getType()+" Depth " +depth);


        for (int i = 0; i < Parent.getSons().length; i++) {
            printTree(Parent.getSons()[i],depth+1);
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
}
