import java.util.ArrayList;
import java.util.HashMap;

public class OperationNode extends JottNode implements JottTree {
    private JottNode left;
    private String operator;
    private JottNode right;
    private String opType;
    private HashMap<String, String> symTab;


    public OperationNode(JottNode left, String operator, JottNode right, String opType, HashMap<String, String> symTab) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.opType = opType;
        this.symTab = symTab;
    }

    public static OperationNode ParseOperationNode(ArrayList<Token> tokens, HashMap<String, String> symTab) throws Exception {
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

        // remove left operand
        tokens.remove(0);

        // assign opType (might be unnecessary)
        Token secondToken = tokens.get(0);
        if (secondToken.getTokenType().equals(TokenType.REL_OP)) {
            opType = "relational";
        }
        else if (secondToken.getTokenType().equals(TokenType.MATH_OP)) {
            opType = "mathematical";
        }

        // assign operator
        operator = secondToken.getToken();

        // remove operator
        tokens.remove(0);

        // assign right operand
        if (tokens.get(1).getTokenType().equals(TokenType.MATH_OP) || tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
            right = OperationNode.ParseOperationNode(tokens, symTab);
        }
        else {
            if (tokens.get(1).getTokenType().equals(TokenType.MATH_OP) || tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                right = OperationNode.ParseOperationNode(tokens, symTab);
            }
            else {
                Token thirdToken = tokens.get(0);
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

                // remove right operand
                tokens.remove(0);
            }
        }


        return new OperationNode(left, operator, right, opType, symTab);
    }

    @Override
    public String convertToJott() {
        return this.left.convertToJott() + " " + this.operator + " " + this.right.convertToJott();
    }

    @Override
    public String convertToJava() {
        return this.left.convertToJott() + " " + this.operator + " " + this.right.convertToJott();
    }

    @Override
    public String convertToC() {
        return this.left.convertToJott() + " " + this.operator + " " + this.right.convertToJott();

    }

    @Override
    public String convertToPython() {
        return this.left.convertToJott() + " " + this.operator + " " + this.right.convertToJott();
    }

    @Override
    public boolean validateTree() {
        if (!left.validateTree()) {
            return false;
        }
        else if (!right.validateTree()) {
            return false;
        }
        else {
            return left.type.equals(right.type);
        }
    }
}