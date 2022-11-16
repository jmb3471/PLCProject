import java.util.ArrayList;
import java.util.HashMap;

public class ExprNode extends Operand {

    private JottNode expr;
    private HashMap<String, String> symTab;
    public String type;

    public ExprNode() {}
    
    public ExprNode(JottNode node, HashMap<String, String> symTab, String type) {
        this.expr = node;
        this.symTab = symTab;
        this.type = type;
    }

    public static ExprNode makeNestedExprNode(Operand left, String op, Operand right, String opType, HashMap<String, String> symTab, String type)
    {
        return new ExprNode(new OperationNode(left, op, right, opType, symTab), symTab, type);
    }

    public static ExprNode ParseExprNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        
        Token token = tokens.get(0);
        if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
            if (token.getToken().equals("True") || token.getToken().equals("False")) {
                if (tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab, "Boolean");
                }
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode, symTab, constantNode.getType());
            }
            else {
                Token secondToken = tokens.get(1);
                if (secondToken.getTokenType().equals(TokenType.L_BRACKET)) {
                    FuncCallNode funcCallNode = FuncCallNode.ParseFuncCallNode(tokens, symTab, depth, funcDefs);
                    funcCallNode.setEndStmt();
                    if (tokens.get(0).getTokenType().equals(TokenType.REL_OP))
                    {
                        String op = tokens.get(0).getToken();
                        if(op.length() == 2)
                        {
                            tokens.remove(1);
                        }
                        tokens.remove(0);
                        ExprNode rightNode = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);
                        JottNode tempNode = makeNestedExprNode(new ExprNode(funcCallNode, symTab, "Boolean"), op, rightNode, "relational", symTab, "Boolean");
                        return new ExprNode(tempNode, symTab, "Boolean");
                    }
                    String type = "String";
                    for (int i = 0; i < funcDefs.size(); i++) {
                        if (funcDefs.get(i).ID.equals(funcCallNode.id.getId())) {
                            type = funcDefs.get(i).return_type;
                        }
                    }
                    return new ExprNode(funcCallNode, symTab, type);
                }
                else if (secondToken.getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab, "Boolean");
                }
                else if (secondToken.getTokenType().equals(TokenType.MATH_OP)) {
                    String type = "Integer";
                    if (secondToken.getToken().contains(".")) {
                        type = "Double";
                    }
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab, type);
                }
                else {
                    VarNode varNode = VarNode.ParseVarNode(tokens, symTab);
                    tokens.remove(0);
                    String type = symTab.get(varNode.id);
                    return new ExprNode(varNode, symTab, type);
                }
            }
        }
        else {
            if (token.getTokenType().equals(TokenType.NUMBER)) {
                if (tokens.get(1).getTokenType().equals(TokenType.MATH_OP) ||
                        tokens.get(1).getTokenType().equals(TokenType.REL_OP)) {
                    OperationNode operationNode = OperationNode.ParseOperationNode(tokens, symTab);
                    return new ExprNode(operationNode, symTab, "Boolean");
                }
                else {
                    ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);

                    // remove constant
                    tokens.remove(0);

                    return new ExprNode(constantNode, symTab, "Integer");
                }
            }
            if (token.getTokenType().equals(TokenType.STRING)) {
                ConstantNode constantNode = ConstantNode.ParseConstantNode(tokens);
                tokens.remove(0);
                return new ExprNode(constantNode, symTab, "String");
            }
            else {
                ExprNode.reportSyntaxError("Incorrect expression", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }
        }
    }

    @Override
    public String getType() {
        return this.type;
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
        System.out.println("Validating " + this.getClass());
        return expr.validateTree();
    }
}