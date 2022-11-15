import java.util.ArrayList;

public class ConstantNode extends Operand implements JottTree {

    private String value;
    private String type;


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
            ConstantNode.reportSyntaxError("Incorrect ID for Constant", tokens.get(0).getFilename(),
                    tokens.get(0).getLineNum());
            return null;
        } else {
            String type = null;
            if (token.getTokenType().equals(TokenType.STRING)) {
                type = "String";
            } else if (token.getTokenType().equals(TokenType.NUMBER)) {
                if (token.getToken().contains(".")) {
                    type = "Double";
                } else {
                    type = "Integer";
                }
            } else {
                AsmtNode.reportSyntaxError("Incorrect Constant", tokens.get(0).getFilename(),
                        tokens.get(0).getLineNum());
                return null;
            }
            return new ConstantNode(token.getToken(), type);
        }
    }

    @Override
    public String getType() {
        return type;
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

        if (this.getType().equals("Double"))
        {
            return ValidationUtils.validateDouble(Double.parseDouble(value));
        }

        if (this.getType().equals("Integer"))
        {
            return ValidationUtils.validateInt(Integer.parseInt(value));
        }

        if (this.getType().equals("Boolean"))
        {
            if (!(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")))
            {
                return false;
            }
        }

        if (this.getType().equals("String"))
        {
            return ValidationUtils.validateString(value);
        }

        return true;
    }
}