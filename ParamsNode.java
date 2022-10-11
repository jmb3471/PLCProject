import java.util.ArrayList;

public class ParamsNode extends JottNode {

    private ArrayList<ExprNode> exprNodes;

    public ParamsNode(ArrayList<ExprNode> exprNodes) {
        this.exprNodes = exprNodes;
    }

    public static ParamsNode ParseParamsNode(ArrayList<Token> tokens) {
        ArrayList<ExprNode> exprNodes = new ArrayList<>();
        Boolean firstExpr = true;
        while (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            if (!firstExpr) {

            }
            ExprNode exprNode = ExprNode.ParseExprNode(tokens);
            exprNodes.add(exprNode);
        }
        return new ParamsNode(exprNodes);
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
