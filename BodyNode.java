import java.util.ArrayList;

public class BodyNode extends JottNode {

    private ArrayList<BodyStmtNode> bodyStmts;
    private ExprNode ReturnStmt;

    public BodyNode(ArrayList<BodyStmtNode> bodyStmts, ExprNode returnStmt) {
        this.bodyStmts = bodyStmts;
        this.ReturnStmt = returnStmt;
    }


    public static BodyNode ParseBodyNode(ArrayList<Token> tokens) {
        ArrayList<BodyStmtNode> bodyStmts = new ArrayList<>();
        ExprNode exprNode = null;

        while (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            if (tokens.get(0).getToken().equals("return")) {

                // remove return token
                tokens.remove(0);

                exprNode = ExprNode.ParseExprNode(tokens);
                break;
            }
            else {
                BodyStmtNode bodyStmt = BodyStmtNode.ParseBodyStmtNode(tokens);
                bodyStmts.add(bodyStmt);
            }
        }

        BodyNode bodyNode = new BodyNode(bodyStmts, exprNode);

        return bodyNode;
    }
    

    @Override
    public String convertToJott() {
        String jott = "";
        for (int i = 0; i < this.bodyStmts.size(); i++) {
            jott += this.bodyStmts.get(i).convertToJott();
        }
        if (this.ReturnStmt != null) {
            jott += this.ReturnStmt.convertToJott();
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
