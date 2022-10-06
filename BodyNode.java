import java.util.ArrayList;

public class BodyNode extends JottNode implements JottTree{
    private JottNode bodyStmt;
    private JottNode body;
    private JottNode returnStmt;

    private ArrayList<BodyStmtNode> bodyStmts;
    private ExprNode ReturnStmt;

    public void setBodyStmt(JottNode bodyStmt) {
        this.bodyStmt = bodyStmt;
    }

    public void setBody(JottNode body) {
        this.body = body;
    }

    public void setReturnStmt(JottNode returnStmt) {
        this.returnStmt = returnStmt;
    }

    private String type;


    public BodyNode() {
        this.bodyStmt = null;
        this.body = null;;
        this.returnStmt = null;
        this.type = "Body";
    }


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
