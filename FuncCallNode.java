import java.util.ArrayList;

public class FuncCallNode extends StmtNode implements JottTree {
    
    private JottNode id;
    private ArrayList<ExprNode> exprNodes;

    public FuncCallNode(JottNode id, ArrayList<ExprNode> exprNodes)
    {
        this.id = id;
        this.exprNodes = exprNodes;
    }

    public static FuncCallNode ParseFuncCallNode(ArrayList<Token> tokens) {
        IdNode idNode = IdNode.ParseIdNode(tokens);

        // remove id and [
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<ExprNode> exprNodes = new ArrayList<>();

        while (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            ExprNode exprNode = ExprNode.ParseExprNode(tokens);
            exprNodes.add(exprNode);
        }
        return new FuncCallNode(idNode, exprNodes);

    }

    @Override
    public String convertToJott() {
        String jott = this.id + "[";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                jott += this.exprNodes.get(i).convertToJott();
            }
            else {
                jott += this.exprNodes.get(i).convertToJott() + ",";
            }
        }
        return jott;

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
