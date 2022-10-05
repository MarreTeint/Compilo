public class ErrLexical extends Exception {
    public ErrLexical(String message, int line) {
        super(message + " at line " + line);
    }
    public ErrLexical(String message) {
        super(message);
    }
    public ErrLexical(){
        super();
    }
}

