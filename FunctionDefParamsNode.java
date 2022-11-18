import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDefParamsNode extends JottNode {

    public String type;
    private String ID;


    public FunctionDefParamsNode(String id, String type) {
        this.type = type;
        this.ID = id;
    }

    public static FunctionDefParamsNode ParseFunctionDefParamsNode(ArrayList<Token> tokens, HashMap<String, String> symTab) throws Exception {
        // Check if first token is an ID
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            FunctionDefParamsNode.reportSyntaxError("Expected ID for first token for FunctionDefParam", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String id = tokens.get(0).getToken();

        // Check if the next element is a ":"
        if (tokens.get(1).getTokenType() != TokenType.COLON) {
            FunctionDefParamsNode.reportSyntaxError("Expected : for FunctionDefParam", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String type = tokens.get(2).getToken();

        // Check if the next token is a valid type
        if (!type.equals("Double") && !type.equals("Integer") && !type.equals("String") && !type.equals("Boolean")) {
            FunctionDefParamsNode.reportSyntaxError("Expected valid type for FunctionDefParam", tokens.get(2).getFilename(), tokens.get(2).getLineNum());
            return null;
        }

        // Remove the id, the ":", and the type
        tokens.remove(2);
        tokens.remove(1);
        tokens.remove(0);

        // Update the symbol table
        symTab.put(id, type);

        // Return a FunctionDefParamsNode object with the correct id and type
        return new FunctionDefParamsNode(id, type);
    }

    @Override
    public String convertToJott() {
        return this.ID + ":" + this.type;
    }

    @Override
    public String convertToJava() {
        String j_type;
        switch(type) {
            case "Integer":
                j_type= "int";
                break;

            case "String":
                j_type = "String";
                break;

            case "Boolean":
                j_type = "boolean";
                break;

            case "Double":
                j_type = "double";
                break;

            default:
                System.out.println("Invalid type");
                return "";
        }
        return j_type + " " + this.ID;
    }

    @Override
    public String convertToC() {
        String c_type;
        switch(this.type) {
            case "Integer":
                c_type= "int";
                break;

            case "String":
                c_type = "char*";
                break;

            case "Boolean":
                c_type = "bool";
                break;

            case "Double":
                c_type = "double";
                break;

            default:
                System.out.println("Invalid type");
                return "";
        }
        return c_type + " " + this.ID;
    }

    @Override
    public String convertToPython() {
        return this.ID;
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}
