import java.io.FileWriter;
import java.util.ArrayList;

public class Jott {
    public static void main(String[] args) {

        // don't we accept 4 command line args? 
        //if (args.length != 3) 
        //{
        //    return;
        //}

        try 
        {
            //System.out.println("args[0] = " + args[0]);
            //System.out.println("args[1] = " + args[1]);
            //System.out.println("args[2] = " + args[2]);
            //System.out.println("args[3] = " + args[3]);

            System.out.println("Tokenizing...");
            ArrayList<Token> tokens = JottTokenizer.tokenize(args[0]);
            System.out.println("Parsing...");
            JottTree tree = ProgramNode.ParseProgram(tokens);
            boolean valid = false;

            if (tree != null) {
                System.out.println("Validating...");
                valid = tree.validateTree();
            }

            if (!valid) {
                System.out.println("Invalid Tree");
                return;
            }

            System.out.println("Valid Tree");

            String output = "";
            if (args[2].equals("Jott") && args[1].contains(".jott")) {
                output = tree.convertToJott();
            }
            FileWriter fileWriter = new FileWriter(args[1]);
            fileWriter.write(output);
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
