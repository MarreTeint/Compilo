import java.io.*;
import java.util.ArrayList;

public class Main {

    Token current = new Token(null,null,null);
    Token last = new Token(null,null,null);
    static void next(String File){




            //Initialisation variables
            String line;
            int lineIndex = 0;
            boolean isAWord = false;
            String word = "";


                lineIndex++;
                for (int i = 0; i < line.length(); i++) {
                    char lettre = line.charAt(i);
                    switch (lettre) {
                        case ' ': //Space or end of a word
                            if(isAWord) {

                                if(word.equals("return")) //TODO Check if that's actually how it's supposed to be done lmao
                                    listTokens.add(new Token("return", 0, lineIndex));
                                else if (word.equals("int"))
                                    listTokens.add(new Token("int", 0, lineIndex));
                                else
                                    listTokens.add(new Token("mot", 0, lineIndex)); //TODO Change valeur for the actual "word" variable ?

                                isAWord = false; //Le mot est terminé
                                word = "";
                            }
                            break;
                        case '(': //Opened bracket
                            listTokens.add(new Token("parOpen", 0, lineIndex));
                            break;
                        case ')': //Closed bracket
                            listTokens.add(new Token("parClose", 0, lineIndex));
                            break;
                        case '{': //Oppened accolade
                            listTokens.add(new Token("acOpen", 0, lineIndex));
                            break;
                        case '}': //Closed accolade
                            listTokens.add(new Token("accClose", 0, lineIndex));
                            break;
                        case ';': //Closed accolade
                            listTokens.add(new Token("endOfLine", 0, lineIndex));
                            break;
                        case '+': //Plus
                            listTokens.add(new Token("plus", 0, lineIndex));
                            break;
                        case '-': //Minus
                            listTokens.add(new Token("minus", 0, lineIndex));
                            break;
                        case '*': //Multiply
                            listTokens.add(new Token("multiply", 0, lineIndex));
                            break;
                        case '/': //Divide
                            listTokens.add(new Token("divide", 0, lineIndex));
                            break;
                        case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': //Lower case leters
                        case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n':
                        case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u':
                        case 'v': case 'w': case 'x': case 'y': case 'z':
                        case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G': //Upper case letters
                        case 'H': case 'I': case 'J': case 'K': case 'L': case 'M': case 'N':
                        case 'O': case 'P': case 'Q': case 'R': case 'S': case 'T': case 'U':
                        case 'V': case 'W': case 'X': case 'Y': case 'Z':
                            word = word + lettre; //Concaténation du potentiel mot
                            isAWord = true; //On cherche à compléter un mot
                            break;
                        default:
                            listTokens.add(new Token("EOS", 0, lineIndex));

                    }
                }
            }

            //End of file
            listTokens.add(new Token("endOfFile", 0, lineIndex));

            br.close();


    }

    public boolean check(type){
        if(current.type == type){
            last = current;
            current = next();
            return true;
        }
    }

    public boolean accept(type){
        if(check(type)){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args){

        String fileName = args[0];
        String inside = "";
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+fileName), "ASCII"));} catch (IOException e) {
            e.printStackTrace();
        }
        while ((line = br.readLine()) != null) {
            inside += line;
        }

        system.out.println(inside);


        //Print results
        for(Token token : listTokens)
            System.out.println(token.toString());


    }

}
