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

    public static String TYPE_EOS = "eos";
    public static String TYPE_SPACE = "espace";
    public static String TYPE_RETURN = "return";
    public static String TYPE_INT = "int";
    public static String TYPE_IF = "if";
    public static String TYPE_ELSE = "else";
    public static String TYPE_CONSTANT = "constante";
    public static String TYPE_PLUS = "plus";
    public static String TYPE_MINUS = "moins";
    public static String TYPE_MULTIPLY = "multiplication";
    public static String TYPE_DIVIDE = "division";
    public static String TYPE_NOT = "negation";
    public static String TYPE_PAR_OPEN = "parOuv";
    public static String TYPE_PAR_CLOSE = "parFerm";
    public static String TYPE_ACC_OPEN = "accoladeOuv";
    public static String TYPE_ACC_CLOSE = "accoladeFerm";
    public static String TYPE_POINT_VIRGULE = "pointVirgule";
    public static String TYPE_VIRGULE = "virgule";
    public static String TYPE_AFFECTATION = "affectation";
    public static String TYPE_INF = "inferieur";
    public static String TYPE_SUP = "superieur";
    public static String TYPE_INF_EGAL = "inferieurEgal";
    public static String TYPE_SUP_EGAL = "superieurEgal";
    public static String TYPE_EGAL = "egal";
    public static String TYPE_DIFF = "different";
    public static String TYPE_FOR = "for";
    public static String TYPE_WHILE = "while";
    public static String TYPE_DO = "do";
    public static String TYPE_BREAK = "break";
    public static String TYPE_CONTINUE = "continue";
    public static String TYPE_AND = "&&";
    public static String TYPE_OR = "||";
    public static String TYPE_IDENT = "ident";

}
