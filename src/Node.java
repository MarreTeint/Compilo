public class Node {
    private String type;
    private String value;
    private Node[] sons;

    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.sons = new Node[0];
    }
    public Node(String type, String value, Node[] sons) {
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
    public String getValue() {
        return value;
    }
    public Node[] getSons() {
        return sons;
    }

}
