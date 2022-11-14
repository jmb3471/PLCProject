import java.io.FileWriter;
import java.util.ArrayList;

public class Jott {
    public static void main(String[] args) {
        if (args.length != 3) {
            return;
        }
        try {
            ArrayList<Token> tokens = JottTokenizer.tokenize(args[0]);
            JottTree tree = JottParser.parse(tokens);
            boolean valid = false;
            if (tree != null) {
                valid = tree.validateTree();
            }
            if (!valid) {
                return;
            }
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
