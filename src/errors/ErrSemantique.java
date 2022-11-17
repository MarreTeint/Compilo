package errors;

//class error when semantic not ok when compiling
public class ErrSemantique extends Exception {
    public ErrSemantique(String message) {
        super(message);
    }
    public ErrSemantique(String message, int line) {
        super(message + " at line " + line);
    }
    public ErrSemantique(){
        super();
    }
}
