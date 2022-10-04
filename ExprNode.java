public class ExprNode extends JottNode implements JottTree {
    private JottNode expr;
    private JottNode end_stmt;
    private String return_stmt = "return";
    private String type;


    public ExprNode() {
        this.expr = null;
        this.type = "Expr";
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