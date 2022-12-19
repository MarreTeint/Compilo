import java.util.HashMap;

public class Operateur {
    private int priorite_gauche;
    private int priorite_droite;
    private String type;

    public Operateur(int priorite_gauche, int priorite_droite, String type) {
        this.priorite_gauche = priorite_gauche;
        this.priorite_droite = priorite_droite;
        this.type = type;
    }

    public int getPriorite_gauche() {
        return priorite_gauche;
    }

    public int getPriorite_droite() {
        return priorite_droite;
    }

    public String getType() {
        return type;
    }

    //Create a static public HashMap<Integer, Operateur> operateurs with values in it
    public static HashMap<String, Operateur> operateurs = new HashMap<String, Operateur>() {
        {
            put(Token.TYPE_AFFECTATION, new Operateur(5, 5, Node.TYPE_AFFECTATION));
            put(Token.TYPE_OR, new Operateur(        10, 11, Node.TYPE_OR));
            put(Token.TYPE_AND, new Operateur(       20, 21, Node.TYPE_AND));
            put(Token.TYPE_INF, new Operateur(       30, 31, Node.TYPE_INF));
            put(Token.TYPE_INF_EGAL, new Operateur(  30, 31, Node.TYPE_INF_EGAL));
            put(Token.TYPE_SUP, new Operateur(       30, 31, Node.TYPE_SUP));
            put(Token.TYPE_SUP_EGAL, new Operateur(  30, 31, Node.TYPE_SUP_EGAL));
            put(Token.TYPE_COMP, new Operateur(      30, 31, Node.TYPE_COMP));
            put(Token.TYPE_DIFF, new Operateur(      30, 31, Node.TYPE_DIFF));
            put(Token.TYPE_PLUS, new Operateur(      40, 41, Node.TYPE_PLUS));
            put(Token.TYPE_MINUS, new Operateur(     40, 41, Node.TYPE_MINUS));
            put(Token.TYPE_MULTIPLY, new Operateur(  50, 51, Node.TYPE_MULTIPLY));
            put(Token.TYPE_DIVIDE, new Operateur(    50, 51, Node.TYPE_DIVIDE));
            put(Token.TYPE_MODULO, new Operateur(    50, 51, Node.TYPE_MODULO));
            //TODO : Token moint unaire ?
            put(Token.TYPE_NOT, new Operateur(       55, 55, Node.TYPE_NOT));
            //TODO : Token multi unaire ?
            //TODO : Token puissance ?
        }};
}
