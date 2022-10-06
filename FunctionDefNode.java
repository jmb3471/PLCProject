import java.util.ArrayList;

public class FunctionDefNode extends JottNode implements JottTree{
    private Token token;
    private JottNode id;
    private JottNode func_def_params;
    private JottNode func_return;
    private JottNode body;

    private String ID;
    private ArrayList<FunctionDefParamsNode> params;
    private String return_type;
    private BodyNode Body;


    public FunctionDefNode() {
        this.token = token;
        this.id = null;
        this.func_def_params = null;;
        this.body = null;
        this.func_return = null;
        this.type = "FunctionDef";
    }
    

    /**
     * FunctionDefNode constructor
     * @param id            The ID/Keyword of the function
     * @param params        An arraylist of FunctionDefParamsNodes for the params of the function
     * @param return_type   The return type of the fundtion
     * @param body          The body of the function
     */
    public FunctionDefNode(String id, ArrayList<FunctionDefParamsNode> params, String return_type, BodyNode body) {
        this.ID = id;
        this.params = params;
        this.return_type = return_type;
        this.Body = body;
    }


    /**
     * Parses the current tokens and returns a FunctionDefNode object
     * @param tokens    The tokens of the program
     * @return          A FunctionDefNode object
     */
    public static FunctionDefNode ParseFunctionDefNode(ArrayList<Token> tokens) {
        // Check if first token is an ID
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            return null;
        }

        String id = tokens.get(0).getToken();

        // Check if there is a "[" in the correct spot
        if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
            return null;
        }

        // Remove the ID and the "]"
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<FunctionDefParamsNode> params = new ArrayList<>();
        boolean firstParam = true;

        // While a "]" hasent been seen yet, create new FunctionDefParamsNodes and add
        // them to the params ArrayList
        while (tokens.get(0).getTokenType() != TokenType.R_BRACKET)
        {
            // If the current param is not the first param, check to see if 
            // there is a "," where it should be
            if (!firstParam)
            {
                if (tokens.get(0).getTokenType() != TokenType.COMMA) {
                    return null;
                }

            }
            else {
                firstParam = false;
            }

            params.add(FunctionDefParamsNode.ParseFunctionDefParamsNode(tokens));
        }

        // Remove the "]"
        tokens.remove(0);

        // Check if the next element is a ":"
        if (tokens.get(0).getTokenType() != TokenType.COLON) {
            return null;
        }

        String type = tokens.get(1).getToken();

        // Check if the return type is a valid type
        if (type != "Double" || type != "Integer" || type != "String" || type != "Boolean") {
            return null;
        }

        // Check if the next element is a "{"
        if (tokens.get(2).getTokenType() != TokenType.L_BRACE) {
            return null;
        }

        // Remove the ":", the return type, and the "{"
        tokens.remove(2);
        tokens.remove(1);
        tokens.remove(0);

        // Parse the body
        BodyNode body = BodyNode.ParseBodyNode(tokens);

        FunctionDefNode funcDef = new FunctionDefNode(id, params, type, body);

        return funcDef;
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

    public void setIdNode(IdNode node) {
        this.id = node;
    }

    public void setFuncDefParamsNode(FunctionDefParamsNode func_def_params) {
        this.func_def_params = func_def_params;
    }

    public void setFuncReturnNode(FunctionReturnNode func_return) {
        this.func_return = func_return;
    }

    public void setBodyNode(BodyNode body) {
        this.body = body;
    }
}
