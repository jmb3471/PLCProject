import java.util.ArrayList;
import java.util.HashMap;

public class While_Loop_Node extends BodyStmtNode {

    private ExprNode cond;
    private BodyNode body;
    private HashMap<String, String> symTab;


    public While_Loop_Node(ExprNode cond, BodyNode body, HashMap<String, String> symTab) {
        this.body = body;
        this.cond = cond;
        this.symTab = symTab;
    }

    public static While_Loop_Node ParseWhileLoopNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {

        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            While_Loop_Node.reportSyntaxError("Expected [ for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left bracket
        tokens.remove(0);

        ExprNode expr = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            While_Loop_Node.reportSyntaxError("Expected ] for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
            While_Loop_Node.reportSyntaxError("Expected { for while loop",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left brace and right bracket
        tokens.remove(1);
        tokens.remove(0);

        BodyNode body = BodyNode.ParseBodyNode(tokens, symTab, depth + 1, funcDefs);

        While_Loop_Node while_loop_node = new While_Loop_Node(expr, body, symTab);
        while_loop_node.depth = depth;
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
        return "while (" + cond.convertToC() + ") {" + body.convertToC() + "}";
    }

    @Override
    public String convertToPython() {
        String tabs = "";
        for (int i = 0; i < this.depth + 1; i++) {
                tabs += "\t";
        }
        return tabs + "while " + cond.convertToPython() + ":\n\t" + tabs + body.convertToPython();
    }

    @Override
    public boolean validateTree() {
        System.out.println("Validating " + this.getClass());
        return this.body.validateTree() && this.cond.validateTree();
    }
}