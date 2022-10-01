public class ReturnStmtNode extends JottNode implements JottTree{
    private Token token;
    private JottNode expr;
    private String end_stmt = ";";
    private String return_stmt = "return";
    private String type;


    public ReturnStmtNode(Token token) {
        this.token = token;
        this.expr = null;
        this.type = "ReturnStmt";
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
