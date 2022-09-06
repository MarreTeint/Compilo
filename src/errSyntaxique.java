//class error when syntax not ok when compiling
public class errSyntaxique extends Exception {
    public errSyntaxique(String message) {
        super(message);
    }
    public errSyntaxique(String message, int line) {
        super(message + " at line " + line);
    }
    public errSyntaxique(){
        super();
    }
}
