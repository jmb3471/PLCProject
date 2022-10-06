import java.util.ArrayList;
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

    public static While_Loop_Node ParseWhileLoopNode(ArrayList<Token> tokens) {

        // stubbed out to finish body node
        While_Loop_Node while_loop_node = new While_Loop_Node();
        return while_loop_node;
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