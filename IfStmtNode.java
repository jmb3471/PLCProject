import java.util.ArrayList;
import java.util.List;

public class IfStmtNode extends BodyStmtNode implements JottTree{
    private String ifstmt = "if ";
    private JottNode b_expr;
    private JottNode body;
    private List<JottNode> elseif_lst;
    private JottNode else_stmt;

    private String type;


    public IfStmtNode() {
        this.else_stmt = null;
        this.elseif_lst = null;;
        this.body = null;
        this.b_expr = null;
        this.type = "Ifstmt";
    }

    public static IfStmtNode ParseIfStmtNode(ArrayList<Token> tokens) {
        if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
            return null;
        }

        tokens.remove(0);

        BExprNode bExpr= BExprNode.ParseBExprNode(tokens);

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
