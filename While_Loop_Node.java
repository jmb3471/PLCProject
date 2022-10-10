import java.util.ArrayList;
import java.util.List;

public class While_Loop_Node extends BodyStmtNode {

    private ExprNode cond;
    private BodyNode body;


    public While_Loop_Node(ExprNode cond, BodyNode body) {
        this.body = body;
        this.cond = cond;
    }

    public static While_Loop_Node ParseWhileLoopNode(ArrayList<Token> tokens) {

        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            return null;
        }

        // remove left bracket
        tokens.remove(0);

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

        While_Loop_Node while_loop_node = new While_Loop_Node(expr, body);
        return while_loop_node;
    }

    @Override
    public String convertToJott() {
        String jott = "while[" + cond.convertToJott() + "]{" + body.convertToJott() + "}";
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