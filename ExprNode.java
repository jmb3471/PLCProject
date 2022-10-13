import java.util.ArrayList;

public class ExprNode extends JottNode {

    private JottNode expr;

    public ExprNode(JottNode node) {
        this.expr = node;
    }

    public static ExprNode ParseExprNode(ArrayList<Token> tokens) throws Exception {
        
        Token token = tokens.get(0);
        if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                if (tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens);
                    return new ExprNode(operationNode);
                }
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode);
            }
            else {
                Token secondToken = tokens.get(1);
                if (secondToken.getTokenType().equals(TokenType.L_BRACKET)) {
                    FuncCallNode funcCallNode = FuncCallNode.ParseFuncCallNode(tokens);
                    return new ExprNode(funcCallNode);
                }
                else if (secondToken.getTokenType().equals(TokenType.MATH_OP) ||
                        secondToken.getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens);
                    return new ExprNode(operationNode);
                }
                else {
                    VarNode varNode = VarNode.ParseVarNode(tokens);
                    tokens.remove(0);
                    return new ExprNode(varNode);
                }
            }
        }
        else {
            if (token.getTokenType().equals(TokenType.NUMBER)) {
                if (tokens.get(1).getTokenType().equals(TokenType.MATH_OP) ||
                        tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens);
                    return new ExprNode(operationNode);
                }
                else {
                    ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                    tokens.remove(0);
                    return new ExprNode(constantNode);
                }
            }
            if (token.getTokenType().equals(TokenType.STRING)) {
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode);
            }
            else {
                ExprNode.reportError("Incorrect expression", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }
        }
    }

    @Override
    public String convertToJott() {
        return this.expr.convertToJott();
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