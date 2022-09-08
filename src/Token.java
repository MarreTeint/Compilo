public class Token {

    String type;
    int valeur;
    int ligne;
    int adresse;

    public Token(String type, int valeur, int ligne) {
        this.type = type;
        this.valeur = valeur;
        this.ligne = ligne;
    }

    public Token(String type, int valeur, int ligne, int adresse) {
        this.type = type;
        this.valeur = valeur;
        this.ligne = ligne;
        this.adresse = adresse;
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

    public int getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Type = " + getType() + "\n" +
                "Valeur = " + Integer.toString(getValeur()) + "\n" +
                "Ligne = " + Integer.toString(getLigne());
    }
}
