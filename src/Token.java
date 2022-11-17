public class Token {

    String type;
    int    valeur;
    int    ligne;
    int    adresse;

    public Token(String type, int valeur, int ligne) {
        this.type =   type;
        this.valeur = valeur;
        this.ligne =  ligne;
    }

    public Token(String type, int valeur, int ligne, int adresse) {
        this.type =    type;
        this.valeur =  valeur;
        this.ligne =   ligne;
        this.adresse = adresse;
    }

    public Token(String type, int valeur) {
        this.type =   type;
        this.valeur = valeur;
    }

    public String getType() { return type; }
    public int getValeur() { return valeur; }
    public int getLigne() { return ligne; }
    public int getAdresse() { return adresse;}

    @Override
    public String toString() {
        return  "Type = "   + getType()                     + "\n" +
                "Valeur = " + Integer.toString(getValeur()) + "\n" +
                "Ligne = "  + Integer.toString(getLigne());
    }

    public static final String TYPE_EOS =         "eos";
    public static final String TYPE_SPACE =       "espace";
    public static final String TYPE_RETURN =      "return";
    public static final String TYPE_INT =         "int";
    public static final String TYPE_IF =          "if";
    public static final String TYPE_ELSE =        "else";
    public static final String TYPE_CONSTANT =    "constante";
    public static final String TYPE_PLUS =        "plus";
    public static final String TYPE_MINUS =       "moins";
    public static final String TYPE_MULTIPLY =    "multiplication";
    public static final String TYPE_DIVIDE =      "division";
    public static final String TYPE_NOT =         "negation";
    public static final String TYPE_PAR_OPEN =    "parOuv";
    public static final String TYPE_PAR_CLOSE =   "parFerm";
    public static final String TYPE_ACC_OPEN =    "accoladeOuv";
    public static final String TYPE_ACC_CLOSE =   "accoladeFerm";
    public static final String TYPE_CROCH_OPEN =  "crochetOuv";
    public static final String TYPE_CROCH_CLOSE = "crochetFerm";
    public static final String TYPE_SEMICOL =     "pointVirgule";
    public static final String TYPE_COMA =        "virgule";
    public static final String TYPE_AFFECTATION = "affectation";
    public static final String TYPE_INF =         "inferieur";
    public static final String TYPE_SUP =         "superieur";
    public static final String TYPE_INF_EGAL =    "inferieurEgal";
    public static final String TYPE_SUP_EGAL =    "superieurEgal";
    public static final String TYPE_COMP =        "comparaison";
    public static final String TYPE_DIFF =        "different";
    public static final String TYPE_FOR =         "for";
    public static final String TYPE_WHILE =       "while";
    public static final String TYPE_DO =          "do";
    public static final String TYPE_BREAK =       "break";
    public static final String TYPE_CONTINUE =    "continue";
    public static final String TYPE_ADDRESS =      "&";
    public static final String TYPE_AND =         "&&";
    public static final String TYPE_OR =          "||";
    public static final String TYPE_IDENT =       "ident";

}
