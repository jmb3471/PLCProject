import java.util.ArrayList;

public class ReturnStmtNode extends JottNode implements JottTree{
    private JottNode expr;
    private JottNode end_stmt;
    private String return_stmt = "return";
    private String type;


    public ReturnStmtNode() {
        this.expr = null;
        this.type = "ReturnStmt";
    }

    public static ReturnStmtNode ParseReturnStmtNode(ArrayList<Token> tokens) {

        // stubbed out to finish body node
        ReturnStmtNode returnStmtNode = new ReturnStmtNode();
        return returnStmtNode;
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
