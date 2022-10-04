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
}
