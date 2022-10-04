public class Node {
    //TODO : add a field for the type of the node ident with adress and variable
    private String type;
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

    public static void printTree(Node Parent){
        System.out.println("Type: " + Parent.getType() + " Value: " + Parent.getValue());
        for (int i = 0; i < Parent.getSons().length; i++) {
            printTree(Parent.getSons()[i]);
        }
    }

    public static String TYPE_IF = Token.TYPE_IF;
    public static String TYPE_BLOCK = "block";
    public static String TYPE_DECLARATION = "declaration";
    public static String TYPE_LOOP = "loop";
    public static String TYPE_CONDITION = "condition";
    public static String TYPE_BREAK = Token.TYPE_BREAK;
    public static String TYPE_SEQUENCE = "sequence";
    public static String TYPE_DROP = "drop";
    public static String TYPE_PLUS = Token.TYPE_PLUS;
    public static String TYPE_MINUS = Token.TUPES_MINUS;
    public static String TYPE_MULTIPLY = Token.TYPE_MULTIPLY;
    public static String TYPE_DIVIDE = Token.TYPE_DIVIDE;
    public static String TYPE_AFFECTATION = Token.TYPE_AFFECTATION;
    public static String TYPE_COMP = Token.TYPE_COMP;
    public static String TYPE_NOT = Token.TYPE_NOT;
    public static String TYPE_CONSTANT = Token.TYPE_CONSTANT;
    public static String TYPE_IDENT = Token.TYPE_IDENT;
    public static String TYPE_TEST = "test";
}
