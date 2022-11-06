import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class While_Loop_Node extends BodyStmtNode {

    private ExprNode cond;
    private BodyNode body;
    private HashMap symTab;


    public While_Loop_Node(ExprNode cond, BodyNode body, HashMap symTab) {
        this.body = body;
        this.cond = cond;
        this.symTab = symTab;
    }

    public static While_Loop_Node ParseWhileLoopNode(ArrayList<Token> tokens, HashMap symTab) throws Exception {

        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            While_Loop_Node.reportError("Expected [ for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left bracket
        tokens.remove(0);

        ExprNode expr = ExprNode.ParseExprNode(tokens, symTab);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            While_Loop_Node.reportError("Expected ] for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
            While_Loop_Node.reportError("Expected { for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left brace and right bracket
        tokens.remove(1);
        tokens.remove(0);

        BodyNode body = BodyNode.ParseBodyNode(tokens, symTab);

        While_Loop_Node while_loop_node = new While_Loop_Node(expr, body, symTab);
        return while_loop_node;
    }

    @Override
    public String convertToJott() {
        return "while[" + cond.convertToJott() + "]{" + body.convertToJott() + "}";
    }

    @Override
    public String convertToJava() {
        return "while (" + cond.convertToJava() + ") {" + body.convertToJava() + "}";
    }

    @Override
    public String convertToC() {
        return "while (" + cond.convertToC() + ") {\n\t" + body.convertToC() + "}\n";
    }

    @Override
    public String convertToPython() {
        return "while " + cond.convertToPython() + ":\n\t" + body.convertToPython();
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}