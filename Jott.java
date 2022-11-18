import java.io.Console;
import java.io.FileWriter;
import java.util.ArrayList;

public class Jott {
    public static void main(String[] args) {
        if (args.length != 3) 
        {
            System.out.println("Usage for Jott Translator:\n" +
                                "\tJott:\t java Jott <input.jott> <output.jott> Jott\n" +
                                "\tPyhon 3: java Jott <input.jott> <output.py> Python\n" +
                                "\tJava:\t java Jott <input.jott> <output.java> Java\n" +
                                "\tC:\t java Jott <input.jott> <output.c> C");
            return;
        }

        try 
        {
            System.out.println("Tokenizing...\n");
            ArrayList<Token> tokens = JottTokenizer.tokenize(args[0]);
            if (tokens.isEmpty()){
                System.out.println(args[0] + " failed while tokenizing.");
                return;
            }

            System.out.println("Parsing...\n");
            JottTree tree = ProgramNode.ParseProgram(tokens);
            if (tree == null){
                System.out.println(args[0] + " failed while parsing.");
                return;
            }
            
            System.out.println("Validating...\n");
            boolean valid = tree.validateTree();

            if (!valid) {
                System.out.println(args[0] + " failed while validating.");
                return;
            }

            System.out.println("Valid Tree\n");

            String output;
            switch (args[2]) {
                case "Jott":
                    System.out.println("Converting to Jott...\n");
                    output = tree.convertToJott();
                    break;

                case "Python":
                    System.out.println("Converting to Python...\n");
                    output = tree.convertToPython();
                    break;

                case "Java":
                    System.out.println("Converting to Java...\n");
                    output = tree.convertToJava();
                    break;

                case "C":
                    System.out.println("Converting to C...\n");
                    output = tree.convertToC();
                    break;

                default:
                    System.out.println(args[2] + " is not a valid language to convert to.");
                    return;
            }

            if (output == null){
                System.out.println("Error occured while converting");
                return;
            }

            FileWriter fileWriter = new FileWriter(args[1]);
            fileWriter.write(output);
            fileWriter.close();

            System.out.println("Output file saved.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
