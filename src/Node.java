public class Node {
    private String type;
    private int value;
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
}
