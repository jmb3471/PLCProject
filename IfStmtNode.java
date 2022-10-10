import java.util.ArrayList;
import java.util.List;

public class IfStmtNode extends BodyStmtNode {

    private ExprNode cond;
    private BodyNode Body;
    private ArrayList<BodyNode> elseIfBodys;
    private ArrayList<ExprNode> elseIfExprs;
    private BodyNode elseBody;


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

        // remove left bracket
        tokens.remove(0);

        // parse b_expr
        ExprNode expr = ExprNode.ParseExprNode(tokens);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            return null;
        }

        if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
            return null;
        }

        // remove left brace and right bracket
        tokens.remove(1);
        tokens.remove(0);

        BodyNode body = BodyNode.ParseBodyNode(tokens);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            return null;
        }

        // remove right brace
        tokens.remove(0);

        ArrayList<BodyNode> elseIFBodys = new ArrayList<>();
        ArrayList<ExprNode> elseIfExprs = new ArrayList<>();

        BodyNode elseNode = null;

        while (tokens.get(0).getToken() == "elseif") {
            if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
                return null;
            }

            // remove left bracket and "elseif"
            tokens.remove(1);
            tokens.remove(0);

            ExprNode elseIfExpr = ExprNode.ParseExprNode(tokens);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                return null;
            }

            if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
                return null;
            }

            // remove left brace and right bracket
            tokens.remove(1);
            tokens.remove(0);

            BodyNode elseIfBody = BodyNode.ParseBodyNode(tokens);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
                return null;
            }

            // remove right brace
            tokens.remove(0);

            elseIFBodys.add(elseIfBody);
            elseIfExprs.add(elseIfExpr);


        }

        if (tokens.get(0).getToken() == "else") {
            if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
                return null;
            }

            // remove left brace and "else"
            tokens.remove(1);
            tokens.remove(0);

            elseNode = BodyNode.ParseBodyNode(tokens);
            if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
                return null;
            }

            // removbe right brace
            tokens.remove(0);
        }

        IfStmtNode ifNode = new IfStmtNode(expr, body, elseIFBodys, elseIfExprs, elseNode);

        return ifNode;
    }

    @Override
    public String convertToJott() {
        String jott = "if[" + this.cond.convertToJott() + "]{" + this.Body.convertToJott() + "}";
        for (int i = 0; i < this.elseIfBodys.size(); i++) {
            jott += "elseif[" + this.elseIfExprs.get(i).convertToJott() + "]{"
                    + this.elseIfBodys.get(i).convertToJott() + "}";
        }
        if (this.elseBody != null) {
            jott += "else{" + this.elseBody.convertToJott() + "}";
        }
        return jott;
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
