 public class errLexical extends Exception {
     public errLexical(String message, int line) {
         super(message + " at line " + line);
     }
     public errLexical(String message) {
         super(message);
     }
     public errLexical(){
         super();
     }
 }

