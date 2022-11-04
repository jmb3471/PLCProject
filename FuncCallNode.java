import java.util.ArrayList;

public class FuncCallNode extends StmtNode {
    
    private JottNode id;
    private ArrayList<ExprNode> exprNodes;

    public Boolean endStmt;
    public FuncCallNode(JottNode id, ArrayList<ExprNode> exprNodes)
    {
        this.id = id;
        this.exprNodes = exprNodes;
        this.endStmt = true;
    }

    public void setEndStmt() {
        this.endStmt = false;
    }

    public static FuncCallNode ParseFuncCallNode(ArrayList<Token> tokens) throws Exception {
        IdNode idNode = IdNode.ParseIdNode(tokens);

        // remove id and [
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<ExprNode> exprNodes = new ArrayList<>();

        while (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ExprNode exprNode = ExprNode.ParseExprNode(tokens);
            exprNodes.add(exprNode);
        }

        // remove ]
        tokens.remove(0);

        return new FuncCallNode(idNode, exprNodes);

    }

    @Override
    public String convertToJott() {
        String jott = this.id.convertToJott() + "[";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                jott += this.exprNodes.get(i).convertToJott();
            }
            else {
                jott += this.exprNodes.get(i).convertToJott() + ",";
            }
        }
        jott += "]";
        if (this.endStmt) {
            jott += ';';
        }
        return jott;

    }

    @Override
    public String convertToJava() {
        String java = this.id.convertToJava() + "(";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                java += this.exprNodes.get(i).convertToJava();
            }
            else {
                java += this.exprNodes.get(i).convertToJava() + ",";
            }
        }
        java += ")";
        if (this.endStmt) {
            java += ';';
        }
        return java;
    }

    @Override
    public String convertToC() {
        String c = this.id.convertToJava() + "(";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                c += this.exprNodes.get(i).convertToJava();
            }
            else {
                c += this.exprNodes.get(i).convertToJava() + ",";
            }
        }
        c += ")";
        if (this.endStmt) {
            c += ';';
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = this.id.convertToJava() + "(";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                python += this.exprNodes.get(i).convertToJava();
            }
            else {
                python += this.exprNodes.get(i).convertToJava() + ",";
            }
        }
        python += ")";
        if (this.endStmt) {
            python += '\n';
        }
        return python;    }

    @Override
    public boolean validateTree() {
        return false;
    }

}
