public class ElseStmtNode extends JottNode implements JottTree {
    private Token token;
    private String else_stmt = "else ";
    private JottNode body;
    private String type;


    public ElseStmtNode(Token token) {
        this.token = token;
        this.body = null;
        this.type = "ElseStmt";
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

