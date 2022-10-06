import java.util.ArrayList;
import java.util.List;

public class FunctionDefParamsNode extends JottNode{
    private JottNode id;
    private JottNode varType;
    private JottNode function_def_params_t;
    private String type;

    private String ID;

    public void setIdNode(JottNode id) {
        this.id = id;
    }

    public void setVarTypeNode(JottNode varType) {
        this.varType = varType;
    }

    public void setFunctionDefParamsTNode(JottNode function_def_params_t) {
        this.function_def_params_t = function_def_params_t;
    }

    public FunctionDefParamsNode() {
        this.id = null;
        this.varType = null;;
        this.function_def_params_t = null;
        this.type = "FunctionDefParams";
    }

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
        if (type != "Double" || type != "Integer" || type != "String" || type != "Boolean") {
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
        return null;
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
