import java.util.ArrayList;

public class ConstantNode extends JottNode implements JottTree {
    private String value;
    public String type;


    public ConstantNode(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public static ConstantNode ParseConstantNode(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                return new ConstantNode(token.getToken(), "Boolean");
            }
            ConstantNode.reportError("Incorrect ID for Constant", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }
        else {
            String type = null;
            if (token.getTokenType().equals(TokenType.STRING)) {
                type = "String";
            }
            else if (token.getTokenType().equals(TokenType.NUMBER)) {
                if (token.getToken().contains(".")) {
                    type = "Double";
                }
                else {
                    type = "Integer";
                }
            }
            else {
                AsmtNode.reportError("Incorrect Constant", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }
            return new ConstantNode(token.getToken(), type);
        }
    }

    @Override
    public String convertToJott() {
        return this.value;
    }

    @Override
    public String convertToJava() {
        return this.value;
    }

    @Override
    public String convertToC() {
        return this.value;
    }

    @Override
    public String convertToPython() {
        return this.value;
    }

    @Override
    public boolean validateTree() {
        return true;
    }
}