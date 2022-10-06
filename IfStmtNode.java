import java.util.ArrayList;
import java.util.List;

public class IfStmtNode extends BodyStmtNode implements JottTree{
    private String ifstmt = "if ";
    private JottNode b_expr;
    private JottNode body;
    private List<JottNode> elseif_lst;
    private JottNode else_stmt;

    private String type;

    private ExprNode cond;
    private BodyNode Body;
    private ArrayList<BodyNode> elseIfBodys;
    private ArrayList<ExprNode> elseIfExprs;
    private BodyNode elseBody;


    public IfStmtNode() {
        this.else_stmt = null;
        this.elseif_lst = null;;
        this.body = null;
        this.b_expr = null;
        this.type = "Ifstmt";
    }

    public IfStmtNode(ExprNode cond, BodyNode body, ArrayList<BodyNode> elseIfBodys, ArrayList<ExprNode> elseIfExprs, BodyNode elseBody) {
        this.cond = cond;
        this.Body = body;
        this.elseIfBodys = elseIfBodys;
        this.elseIfExprs = elseIfExprs;
        this.elseBody = elseBody;
    }

    public static IfStmtNode ParseIfStmtNode(ArrayList<Token> tokens) {
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            return null;
        }

        tokens.remove(0);

        ExprNode expr = ExprNode.ParseExprNode(tokens);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            return null;
        }

        if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
            return null;
        }

        tokens.remove(1);
        tokens.remove(0);

        BodyNode body = BodyNode.ParseBodyNode(tokens);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            return null;
        }

        ArrayList<BodyNode> elseIFBodys = new ArrayList<>();
        ArrayList<ExprNode> elseIfExprs = new ArrayList<>();

        BodyNode elseNode = null;

        while (tokens.get(0).getToken() == "elseif") {
            if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
                return null;
            }

            tokens.remove(1);
            tokens.remove(0);

            ExprNode elseIfExpr = ExprNode.ParseExprNode(tokens);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                return null;
            }

            if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
                return null;
            }

            tokens.remove(1);
            tokens.remove(0);

            BodyNode elseIfBody = BodyNode.ParseBodyNode(tokens);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
                return null;
            }

            tokens.remove(0);

            elseIFBodys.add(elseIfBody);
            elseIfExprs.add(elseIfExpr);


        }

        if (tokens.get(0).getToken() == "else") {
            elseNode = BodyNode.ParseBodyNode(tokens);
        }

        IfStmtNode ifNode = new IfStmtNode(expr, body, elseIFBodys, elseIfExprs, body);

        return ifNode;
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
