import java.util.ArrayList;

public class OperationNode extends JottNode implements JottTree {
    private JottNode left;
    private String operator;
    private JottNode right;
    private String opType;


    public OperationNode(JottNode left, String operator, JottNode right, String opType) {
        this.value = value;
        this.type = type;
    }

    public static OperationNode ParseOperationNode(ArrayList<Token> tokens) {
        Token token = tokens.get(0);
        JottNode left = null;
        String operator = null;
        JottNode right = null;
        String opType = null;

        // assign left operand
        if (token.getTokenType().equals(TokenType.NUMBER)) {
            left = ConstantNode.ParseConstantNode(tokens);
        }
        else if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                left = ConstantNode.ParseConstantNode(tokens);
            }
            else {
                left = VarNode.ParseVarNode(tokens);
            }
        }

        // assign opType (might be unnecessary)
        Token secondToken = tokens.get(1);
        if (secondToken.getTokenType().equals(TokenType.REL_OP)) {
            opType = "relational";
        }
        else if (secondToken.getTokenType().equals(TokenType.MATH_OP)) {
            opType = "mathematical";
        }

        // assign operator
        operator = secondToken.getToken();

        // assign right operand
        Token thirdToken = tokens.get(2);
        if (thirdToken.getTokenType().equals(TokenType.NUMBER)) {
            right = ConstantNode.ParseConstantNode(tokens);
        }
        else if (thirdToken.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                right = ConstantNode.ParseConstantNode(tokens);
            }
            else {
                right = VarNode.ParseVarNode(tokens);
            }
        }
        else {
            OperationNode.reportError("Invalid second operand", thirdToken.getFilename(), thirdToken.getLineNum());
            return null;
        }

        return new OperationNode(left, operator, right, opType);
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