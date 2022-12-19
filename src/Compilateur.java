import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Compilateur {

    ArrayList<Token> tokens;
    Symbole symoboleIdentité = new Symbole("", "", 0, 0);
    ArrayList<HashMap<String, Symbole>> tableSymboles = new ArrayList<HashMap<String, Symbole>>();

    int indexTokenActuel = 0;
    int indexSi = 0;
    int indexBoucle = 0;
    int indexEmplacement = 0;

    String code = "";

    ArrayList<Integer> pileBoucle = new ArrayList<Integer>();

    //TODO : Enlever ou non cette fonction
    public void error(String message) {
        System.out.println("Error: " + message);
        System.exit(0);
    }

    //PARTIE LEXICALE
    public void analyseLexicale(String input, int numLigne) {
        int indexCharacter = 0;
        while (input.length() > indexCharacter) {
            switch (input.charAt(indexCharacter)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    String nombre = "";
                    while (input.length() > indexCharacter && Character.isDigit(input.charAt(indexCharacter))) {
                        nombre += input.charAt(indexCharacter);
                        indexCharacter++;
                    }
                    indexCharacter--;
                    tokens.add(new Token(Token.TYPE_CONSTANT, "", Integer.valueOf(nombre), numLigne));
                    break;
                case '+':
                    tokens.add(new Token(Token.TYPE_PLUS, "", 0, numLigne));
                    break;
                case '-':
                    tokens.add(new Token(Token.TYPE_MINUS, "", 0, numLigne));
                    break;
                case '/':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '/') {
                        indexCharacter = input.length() + 1;
                        break;
                    } else {
                        tokens.add(new Token(Token.TYPE_DIVIDE, "", 0, numLigne));
                    }
                    break;
                case '*':
                    tokens.add(new Token(Token.TYPE_MULTIPLY, "", 0, numLigne));
                    break;
                case '%':
                    tokens.add(new Token(Token.TYPE_MODULO, "", 0, numLigne));
                    break;
                case '^':
                    tokens.add(new Token(Token.TYPE_POWER, "", 0, numLigne));
                    break;
                case ';':
                    tokens.add(new Token(Token.TYPE_SEMICOL, "", 0, numLigne));
                    break;
                case '(':
                    tokens.add(new Token(Token.TYPE_PAR_OPEN, "", 0, numLigne));
                    break;
                case ')':
                    tokens.add(new Token(Token.TYPE_PAR_CLOSE, "", 0, numLigne));
                    break;
                case '{':
                    tokens.add(new Token(Token.TYPE_ACC_OPEN, "", 0, numLigne));
                    break;
                case '}':
                    tokens.add(new Token(Token.TYPE_ACC_CLOSE, "", 0, numLigne));
                    break;
                case '[':
                    tokens.add(new Token(Token.TYPE_CROCH_OPEN, "", 0, numLigne));
                    break;
                case ']':
                    tokens.add(new Token(Token.TYPE_CROCH_CLOSE, "", 0, numLigne));
                    break;
                case '!':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '=') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_DIFF, "", 0, numLigne));
                    } else {
                        tokens.add(new Token(Token.TYPE_NOT, "", 0, numLigne));
                    }
                    break;
                case '=':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '=') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_COMP, "", 0, numLigne));
                    } else {
                        tokens.add(new Token(Token.TYPE_AFFECTATION, "", 0, numLigne));
                    }
                    break;
                case '<':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '=') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_INF_EGAL, "", 0, numLigne));
                    } else {
                        tokens.add(new Token(Token.TYPE_INF, "", 0, numLigne));
                    }
                    break;
                case '>':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '=') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_SUP_EGAL, "", 0, numLigne));
                    } else {
                        tokens.add(new Token(Token.TYPE_SUP, "", 0, numLigne));
                    }
                    break;
                case ',':
                    tokens.add(new Token(Token.TYPE_COMA, "", 0, numLigne));
                    break;
                case '&':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '&') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_AND, "", 0, numLigne));
                    } else {
                        String mot = "";
                        mot += input.charAt(indexCharacter);
                        tokens.add(new Token(Token.TYPE_IDENT, mot, 0, numLigne));
                    }
                    break;
                case '|':
                    if (input.length() > indexCharacter + 1 && input.charAt(indexCharacter + 1) == '|') {
                        indexCharacter++;
                        tokens.add(new Token(Token.TYPE_OR, "", 0, numLigne));
                    } else {
                        String mot = "";
                        mot += input.charAt(indexCharacter);
                        tokens.add(new Token(Token.TYPE_IDENT, mot, 0, numLigne));
                    }
                    break;
                default:
                    if (Character.isLetter(input.charAt(indexCharacter))) {
                        String mot = "";
                        mot += input.charAt(indexCharacter);
                        while (input.length() > indexCharacter && (Character.isLetter(input.charAt(indexCharacter + 1)) || input.charAt(indexCharacter + 1) == '_')) {
                            mot += input.charAt(indexCharacter + 1);
                            indexCharacter++;
                        }
                        if (mot.equals("if")) { //TODO : Par pareil
                            tokens.add(new Token(Token.TYPE_IF, "", 0, numLigne));
                        } else if (mot.equals("else")) {
                            tokens.add(new Token(Token.TYPE_ELSE, "", 0, numLigne));
                        } else if (mot.equals("while")) {
                            tokens.add(new Token(Token.TYPE_WHILE, "", 0, numLigne));
                        } else if (mot.equals("for")) {
                            tokens.add(new Token(Token.TYPE_FOR, "", 0, numLigne));
                        } else if (mot.equals("break")) {
                            tokens.add(new Token(Token.TYPE_BREAK, "", 0, numLigne));
                        } else if (mot.equals("continue")) {
                            tokens.add(new Token(Token.TYPE_CONTINUE, "", 0, numLigne));
                        } else if (mot.equals("debug")) {
                            tokens.add(new Token(Token.TYPE_DEBUG, "", 0, numLigne));
                        } else if (mot.equals("and")) {
                            tokens.add(new Token(Token.TYPE_AND, "", 0, numLigne));
                        } else if (mot.equals("or")) {
                            tokens.add(new Token(Token.TYPE_OR, "", 0, numLigne));
                        } else if (mot.equals("receive")) {
                            tokens.add(new Token(Token.TYPE_RECEIVE, "", 0, numLigne));
                        } else if (mot.equals("send")) {
                            tokens.add(new Token(Token.TYPE_SEND, "", 0, numLigne));
                        } else if (mot.equals("return")) {
                            tokens.add(new Token(Token.TYPE_RETURN, "", 0, numLigne));
                        } else if (mot.equals("int")) {
                            tokens.add(new Token(Token.TYPE_INT, "", 0, numLigne));
                        }
                    }
                    break;
            }
            indexCharacter++;
        }
    }

    //TODO : Supprimer cette fonction
    private Token tokenActuel() {
        return tokens.get(indexTokenActuel);
    }

    //TODO : Supprimer cette fonction
    private Token prochainToken() {
        return tokens.get(indexTokenActuel + 1);
    }

    //TODO : Supprimer cette fonction
    private void avancerToken() {
        indexTokenActuel++;
    }

    private void accepter(String type) {
        if (tokenActuel().getType().equals(type)) {
            avancerToken();
        } else {
            error("Token unexpected at line " + tokenActuel().getLigne() + " : " + tokenActuel().getType()); //TODO : Refaire le système  d'erreur
        }
        avancerToken();
    }

    private boolean verifier(String type) {
        if(tokenActuel().getType().equals(type)) {
            avancerToken();
            return true;
        } else {
            return false;
        }
    }

    //PARTIE SYNTHAXIQUE
    Node nouveauNode(String type, int ligne) {
        Node node = new Node(type, ligne, 0); //TODO : Verifier que ce soit le bon constructeur
        return node;
    }

    void addNode(Node pere, Node fils) {
        pere.addFils(fils);
    }

    Node expression(int prioriteMinimum) {
        Node node =
    }

    Node atome() {
        if(verifier(Token.TYPE_PAR_OPEN)) {
            Node node = expression(0);
            accepter(Token.TYPE_PAR_CLOSE);
            return node;
        } else if(verifier(Token.TYPE_MINUS)) {
            Node node = nouveauNode(Token.TYPE_MINUS, tokenActuel().getLigne()); //TODO : Neg unaire ?
            Node arg = expression(Operateur.operateurs.get(Token.TYPE_MINUS).getPriorite_gauche());
            addNode(node, arg);
            return node;
        } else if(verifier(Token.TYPE_NOT)) {
            Node node = nouveauNode(Token.TYPE_NOT, tokenActuel().getLigne()); //TODO : Not unaire ?
            Node arg = expression(Operateur.operateurs.get(Token.TYPE_NOT).getPriorite_gauche());
            addNode(node, arg);
            return node;
        } else if(verifier(Token.TYPE_MULTIPLY)) {
            Node node = nouveauNode()
        }

        return null;
    }

    Node S() {

    }



}
