import java.io.*;

public class Main {

    public static void main(String[] args){

        String fileName = args[0];

        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/"+fileName), "ASCII"));

            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    System.out.println("Char at: " + line.charAt(i));
                }
                System.out.println("Another line");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
