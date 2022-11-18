import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDefNode extends JottNode {

    public String ID;
    public ArrayList<FunctionDefParamsNode> params;
    public String return_type;
    private BodyNode Body;
    private HashMap<String, String> symTab;
    private String fileName;

    public int getLineNumber() {
        return lineNumber;
    }

    private int lineNumber;


    /**
     * FunctionDefNode constructor
     * @param id            The ID/Keyword of the function
     * @param params        An arraylist of FunctionDefParamsNodes for the params of the function
     * @param body          The BodyNode for the body of the function
     * @param return_type   The return type of the fundtion
     * @param symTab        The symbol table for the function
     */
    public FunctionDefNode(String id, ArrayList<FunctionDefParamsNode> params, BodyNode body, String return_type, HashMap<String, String> symTab, String fileName, int lineNumber) {
        this.ID = id;
        this.params = params;
        this.Body = body;
        this.return_type = return_type;
        this.symTab = symTab;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
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
        String fileName = tokens.get(0).getFilename();
        int lineNumber = tokens.get(0).getLineNum();

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

        FunctionDefNode funcDef = new FunctionDefNode(id, params, body, type, symTab, fileName, lineNumber);

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
        String j_return;
        switch(this.return_type) {
            case "Integer":
                j_return= "int";
                break;

            case "String":
                j_return = "String";
                break;

            case "Boolean":
                j_return = "boolean";
                break;

            case "Double":
                j_return = "double";
                break;

            case "Void":
                j_return = "void";
                break;

            default:
                System.out.println("Invalid type");
                return "";
        }
        String java = "public " + j_return + " " + this.ID + "(";
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
        String c;
        if (this.ID.equals("main")) {
            c = "int main(void";
        }
        else {
            String c_return;
            switch(this.return_type) {
                case "Integer":
                    c_return= "int";
                    break;

                case "String":
                    c_return = "char*";
                    break;

                case "Boolean":
                    c_return = "bool";
                    break;

                case "Double":
                    c_return = "double";
                    break;

                case "Void":
                    c_return = "void";
                    break;

                default:
                    System.out.println("Invalid type");
                    return "";
            }
            c = c_return + " " + this.ID + "(";
        }
        for (int i = 0; i < this.params.size(); i++) {
            if (i == this.params.size() - 1) {
                c += this.params.get(i).convertToC();
            }
            else {
                c += this.params.get(i).convertToC() + ",";
            }
        }
        if (this.ID.equals("main")) {
            c += ") { " + this.Body.convertToC() + "return 1;}";
        }
        else {
            c += ") { " + this.Body.convertToC() + "}";
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = "\ndef " + this.ID + "(";
        for (int i = 0; i < this.params.size(); i++) {
            if (i == this.params.size() - 1) {
                python += this.params.get(i).convertToPython();
            }
            else {
                python += this.params.get(i).convertToPython() + ",";
            }
        }
        python += "):\n" + this.Body.convertToPython();

        return python;
    }

    @Override
    public boolean validateTree() {
        //System.out.println("Validating " + this.getClass());
        if (this.Body.hasIfStmt()) {
            if (this.Body.validReturn()) {
                return this.Body.validateTree();
            }
        }
        ExprNode returnStmt = this.Body.getReturnStmt();
        if (returnStmt == null) {
            if (!this.return_type.equals("Void")) {
                try {
                    reportSemanticError("Function missing return statement", this.fileName, this.lineNumber);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        else {
            if (!this.return_type.equals(returnStmt.getType())) {
                try {
                    reportSemanticError("Body return type doesn't match function return type", returnStmt.fileName, returnStmt.lineNumber);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return this.Body.validateTree();
    }
}
