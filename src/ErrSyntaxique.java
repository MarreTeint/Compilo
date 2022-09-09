//class error when syntax not ok when compiling
public class ErrSyntaxique extends Exception {
    public ErrSyntaxique(String message) {
        super(message);
    }
    public ErrSyntaxique(String message, int line) {
        super(message + " at line " + line);
    }
    public ErrSyntaxique(){
        super();
    }
}
