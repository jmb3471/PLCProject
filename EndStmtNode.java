public class EndStmtNode  extends JottNode implements JottTree{
    private Token token;
    private String end_stmt = ";";
    private String type;


    public EndStmtNode() {
        this.type = "EndStmt";
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
