import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    public static Token[] Lexer(byte[] source) {
        Token[] tokens = new Token[1000];
        int numOfLines = 1;

        for (int i = 0; i < source.length; i++) {
            if(checkMatchChar("\n", String.valueOf(source[i]))) {
                numOfLines++;
            }


        }


        return tokens;
    }

    private static boolean checkMatchChar(String regex, String character) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(character);
        return matcher.matches();
    }

    //Getoperator function
    private static String getOperator(String character) {
        String operator = "";
        switch(character) {
            case "+":
                operator = Token.TYPE_PLUS;
                break;
            case "-":
                operator = Token.TYPE_MINUS;
                break;
            case "*":
                operator = Token.TYPE_MULTIPLY;
                break;
            case "/":
                operator = Token.TYPE_DIVIDE;
                break;
            case "!":
                operator = Token.TYPE_NOT;
                break;
            case "(":
                operator = Token.TYPE_PAR_OPEN;
                break;
            case ")":
                operator = Token.TYPE_PAR_CLOSE;
                break;
            case "{":
                operator = Token.TYPE_ACC_OPEN;
                break;
            case "}":
                operator = Token.TYPE_ACC_CLOSE;
                break;
            case "[":
                operator = Token.TYPE_CROCH_OPEN;
                break;
            case "]":
                operator = Token.TYPE_CROCH_CLOSE;
                break;
            case ";":
                operator = Token.TYPE_SEMICOL;
                break;
            case ",":
                operator = Token.TYPE_COMA;
                break;
            case "=":
                operator = Token.TYPE_AFFECTATION;
                break;
            case "<":
                operator = Token.TYPE_INF;
                break;
            case ">":
                operator = Token.TYPE_SUP;
                break;
        }
        return operator;
    }

}
