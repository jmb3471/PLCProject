import java.util.ArrayList;
import java.util.HashMap;

public class ExprNode extends JottNode {

    private JottNode expr;
    private HashMap<String, String> symTab;

    public ExprNode() {}
    
    public ExprNode(JottNode node, HashMap<String, String> symTab) {
        this.expr = node;
        this.symTab = symTab;
    }

    public static ExprNode makeNestedExprNode(ExprNode left, String op, ExprNode right, String opType, HashMap<String, String> symTab)
    {
        return new ExprNode(new OperationNode(left, op, right, opType, symTab), symTab);
    }

    public static ExprNode ParseExprNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth) throws Exception {
        
        Token token = tokens.get(0);
        if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                if (tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab);
                }
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode, symTab);
            }
            else {
                Token secondToken = tokens.get(1);
                if (secondToken.getTokenType().equals(TokenType.L_BRACKET)) {
                    FuncCallNode funcCallNode = FuncCallNode.ParseFuncCallNode(tokens, symTab);
                    funcCallNode.setEndStmt();
                    if (tokens.get(0).getTokenType().equals(TokenType.REL_OP))
                    {
                        String op = tokens.get(0).getToken();
                        if(op.length() == 2)
                        {
                            tokens.remove(1);
                        }
                        tokens.remove(0);
                        ExprNode rightNode = ExprNode.ParseExprNode(tokens, symTab, depth);
                        JottNode tempNode = makeNestedExprNode(new ExprNode(funcCallNode, symTab), op, rightNode, "relational", symTab);
                        return new ExprNode(tempNode, symTab);
                    }
                    return new ExprNode(funcCallNode, symTab);
                }
                else if (secondToken.getTokenType().equals(TokenType.MATH_OP) ||
                        secondToken.getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab);
                }
                else {
                    VarNode varNode = VarNode.ParseVarNode(tokens);
                    tokens.remove(0);
                    return new ExprNode(varNode, symTab);
                }
            }
        }
        else {
            if (token.getTokenType().equals(TokenType.NUMBER)) {
                if (tokens.get(1).getTokenType().equals(TokenType.MATH_OP) ||
                        tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab);
                }
                else {
                    ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);

                    // remove constant
                    tokens.remove(0);

                    return new ExprNode(constantNode, symTab);
                }
            }
            if (token.getTokenType().equals(TokenType.STRING)) {
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode, symTab);
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