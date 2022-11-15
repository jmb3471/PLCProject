import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDefNode extends JottNode {

    public String ID;
    public ArrayList<FunctionDefParamsNode> params;
    public String return_type;
    private BodyNode Body;
    private HashMap<String, String> symTab;


    /**
     * FunctionDefNode constructor
     * @param id            The ID/Keyword of the function
     * @param params        An arraylist of FunctionDefParamsNodes for the params of the function
     * @param body          The BodyNode for the body of the function
     * @param return_type   The return type of the fundtion
     * @param symTab        The symbol table for the function
     */
    public FunctionDefNode(String id, ArrayList<FunctionDefParamsNode> params, BodyNode body, String return_type, HashMap<String, String> symTab) {
        this.ID = id;
        this.params = params;
        this.Body = body;
        this.return_type = return_type;
        this.symTab = symTab;
    }


    /**
     * Parses the current tokens and returns a FunctionDefNode object
     *
     * @param tokens   The tokens of the program
     * @param funcDefs
     * @return A FunctionDefNode object
     */
    public static FunctionDefNode ParseFunctionDefNode(ArrayList<Token> tokens, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        // Check if first token is an ID
        if (tokens.get(0).getTokenType() != TokenType.ID_KEYWORD) {
            FunctionDefNode.reportSyntaxError("Expected ID for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String id = tokens.get(0).getToken();

        // Check if there is a "[" in the correct spot
        if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
            FunctionDefNode.reportSyntaxError("Expected [ to be after ID for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // Remove the ID and the "["
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<FunctionDefParamsNode> params = new ArrayList<>();
        HashMap<String, String> symTab = new HashMap<>();
        boolean firstParam = true;

        // While a "]" hasn't been seen yet, create new FunctionDefParamsNodes and add
        // them to the params ArrayList
        while (tokens.get(0).getTokenType() != TokenType.R_BRACKET)
        {
            // If the current param is not the first param, check to see if 
            // there is a "," where it should be
            if (!firstParam)
            {
                if (tokens.get(0).getTokenType() != TokenType.COMMA && tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                    FunctionDefNode.reportSyntaxError("Expected , between params for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                    return null;
                }
                tokens.remove(0);
            }
            else {
                firstParam = false;
            }

            params.add(FunctionDefParamsNode.ParseFunctionDefParamsNode(tokens, symTab));
        }

        // Remove the "]"
        tokens.remove(0);

        // Check if the next element is a ":"
        if (tokens.get(0).getTokenType() != TokenType.COLON) {
            FunctionDefNode.reportSyntaxError("Expected : for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        String type = tokens.get(1).getToken();

        // Check if the return type is a valid type
        if (!type.equals("Double") && !type.equals("Integer") && !type.equals("String")
                && !type.equals("Boolean") && !type.equals("Void")) {
            FunctionDefNode.reportSyntaxError("Expected return type to be valid for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // Add the return type to the symbol table
        symTab.put("return", type);

        // Check if the next element is a "{"
        if (tokens.get(2).getTokenType() != TokenType.L_BRACE) {
            FunctionDefNode.reportSyntaxError("Expected function to start with { for FunctionDef", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // Remove the ":", the return type, and the "{"
        tokens.remove(2);
        tokens.remove(1);
        tokens.remove(0);

        // Parse the body
        BodyNode body = BodyNode.ParseBodyNode(tokens, symTab, 1, funcDefs);

        FunctionDefNode funcDef = new FunctionDefNode(id, params, body, type, symTab);

        return funcDef;
    }


    @Override
    public String convertToJott() {
        String jott = this.ID + "[";
        for (int i = 0; i < this.params.size(); i++) {
            jott += this.params.get(i).convertToJott();
        }
        jott += "]:" + this.return_type + "{" + this.Body.convertToJott() + "}";
        return jott;
    }

    @Override
    public String convertToJava() {
        String java = "public " + this.return_type + " " + this.ID + "(";
        for (int i = 0; i < this.params.size(); i++) {
            if (i == this.params.size() - 1) {
                java += this.params.get(i).convertToJava();
            }
            else {
                java += this.params.get(i).convertToJava() + ",";
            }
        }
        java += ") { " + this.Body.convertToJava() + "}";
        return java;
    }

    @Override
    public String convertToC() {
        String c = this.return_type + " " + this.ID + "(";
        for (int i = 0; i < this.params.size(); i++) {
            if (i == this.params.size() - 1) {
                c += this.params.get(i).convertToC();
            }
            else {
                c += this.params.get(i).convertToC() + ",";
            }
        }
        c += ") { " + this.Body.convertToC() + "}";

        return c;
    }

    @Override
    public String convertToPython() {
        String python = "def " + this.ID + "(";
        for (int i = 0; i < this.params.size(); i++) {
            if (i == this.params.size() - 1) {
                python += this.params.get(i).convertToPython();
            }
            else {
                python += this.params.get(i).convertToPython() + ",";
            }
        }
        python += "):\n\t" + this.Body.convertToPython();

        return python;
    }

    @Override
    public boolean validateTree() {
        return this.Body.validateTree();
    }
}
