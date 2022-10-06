import java.util.List;

public class While_Loop_Node extends BodyStmtNode implements JottTree{
    private String ifstmt = "while ";
    private JottNode b_expr;
    private JottNode body;

    private String type;


    public While_Loop_Node() {
        this.body = null;
        this.b_expr = null;
        this.type = "Ifstmt";
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