public class Token {

    String type;
    int valeur;
    int ligne;

    public Token(String type, int valeur, int ligne) {
        this.type = type;
        this.valeur = valeur;
        this.ligne = ligne;
    }

    public Token(String type, int valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    public String getType() {
        return type;
    }

    public int getValeur() {
        return valeur;
    }

    public int getLigne() {
        return ligne;
    }
}
