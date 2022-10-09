import java.util.ArrayList;
import java.util.List;

public class While_Loop_Node extends BodyStmtNode implements JottTree{
    private ExprNode cond;
    private BodyNode Body;


    public While_Loop_Node(ExprNode cond, BodyNode Body) {
        this.Body = Body;
        this.cond = cond;
    }

    public static While_Loop_Node ParseWhileLoopNode(ArrayList<Token> tokens) {
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
        While_Loop_Node while_loop_node = new While_Loop_Node(expr, body);
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