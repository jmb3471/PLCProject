import java.util.ArrayList;

public class BodyNode extends JottNode implements JottTree{
    private JottNode bodyStmt;
    private JottNode body;
    private JottNode returnStmt;

    private ArrayList<BodyStmtNode> bodyStmts;

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


    public BodyNode(ArrayList<BodyStmtNode> bodyStmts) {
        this.bodyStmts = bodyStmts;
    }

    public static BodyNode ParseBodyNode(ArrayList<Token> tokens) {
        return null;
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
