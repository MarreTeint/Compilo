//class error when semantic not ok when compiling
public class errSemantique extends Exception {
    public errSemantique(String message) {
        super(message);
    }
    public errSemantique(String message, int line) {
        super(message + " at line " + line);
    }
    public errSemantique(){
        super();
    }
}
