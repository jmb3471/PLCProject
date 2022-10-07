import java.util.ArrayList;
import java.util.List;

public class FunctionDefParamsNode extends JottNode{

    private String type;

    private String ID;


    public FunctionDefParamsNode(String id, String type) {
        this.type = type;
        this.ID = id;
    }

    public static FunctionDefParamsNode ParseFunctionDefParamsNode(ArrayList<Token> tokens) {
        // Check if first token is an ID
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            return null;
        }

        String id = tokens.get(0).getToken();

        // Check if the next element is a ":"
        if (tokens.get(1).getTokenType() != TokenType.COLON) {
            return null;
        }

        String type = tokens.get(2).getToken();

        // Check if the next token is a valid type
        if (!type.equals("Double") && !type.equals("Integer") && !type.equals("String") && !type.equals("Boolean")) {
            return null;
        }

        // Remove the id, the ":", and the type
        tokens.remove(2);
        tokens.remove(1);
        tokens.remove(0);

        // Return a FunctionDefParamsNode object with the correct id and type
        return new FunctionDefParamsNode(id, type);
    }

    @Override
    public String convertToJott() {
        String jott = this.type + ":" + this.ID;
        return jott;
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
