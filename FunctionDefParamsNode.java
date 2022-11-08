import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDefParamsNode extends JottNode{

    private String type;

    private String ID;


    public FunctionDefParamsNode(String id, String type) {
        this.type = type;
        this.ID = id;
    }

    public static FunctionDefParamsNode ParseFunctionDefParamsNode(ArrayList<Token> tokens, HashMap<String, String> symTab) throws Exception {
        // Check if first token is an ID
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            FunctionDefParamsNode.reportError("Expected ID for first token for FunctionDefParam", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String id = tokens.get(0).getToken();

        // Check if the next element is a ":"
        if (tokens.get(1).getTokenType() != TokenType.COLON) {
            FunctionDefParamsNode.reportError("Expected : for FunctionDefParam", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String type = tokens.get(2).getToken();

        // Check if the next token is a valid type
        if (!type.equals("Double") && !type.equals("Integer") && !type.equals("String") && !type.equals("Boolean")) {
            FunctionDefParamsNode.reportError("Expected valid type for FunctionDefParam", tokens.get(2).getFilename(), tokens.get(2).getLineNum());
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
        return this.type + " " + this.ID;
    }

    @Override
    public String convertToC() {
        return this.type + " " + this.ID;
    }

    @Override
    public String convertToPython() {
        return this.ID;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
