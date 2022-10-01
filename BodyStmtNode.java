public class BodyStmtNode extends JottNode implements JottTree {
    private Token token;
    private JottNode if_stmt;
    private JottNode while_stmt;
    private JottNode stmt;
    private String type;


    public BodyStmtNode(Token token) {
        this.token = token;
        this.if_stmt = null;
        this.while_stmt = null;
        this.stmt = null;
        this.type = "BodyStmt";
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