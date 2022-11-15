import java.util.ArrayList;
import java.util.HashMap;

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

    public static IfStmtNode ParseIfStmtNode(ArrayList<Token> tokens, HashMap symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {

        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            IfStmtNode.reportSyntaxError("Expected [ for ifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left bracket
        tokens.remove(0);

        // parse b_expr
        ExprNode expr = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            IfStmtNode.reportSyntaxError("Expected ] for ifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
            IfStmtNode.reportSyntaxError("Expected { for ifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove left brace and right bracket
        tokens.remove(1);
        tokens.remove(0);

        BodyNode body = BodyNode.ParseBodyNode(tokens, symTab, depth+1, funcDefs);

        if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            IfStmtNode.reportSyntaxError("Expected } for ifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove right brace
        tokens.remove(0);

        ArrayList<BodyNode> elseIFBodys = new ArrayList<>();
        ArrayList<ExprNode> elseIfExprs = new ArrayList<>();

        BodyNode elseNode = null;

        while (tokens.get(0).getToken() == "elseif") {
            if (tokens.get(1).getTokenType() != TokenType.L_BRACKET) {
                IfStmtNode.reportSyntaxError("Expected [ for elseifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            // remove left bracket and "elseif"
            tokens.remove(1);
            tokens.remove(0);

            ExprNode elseIfExpr = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
                IfStmtNode.reportSyntaxError("Expected ] for elseifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
                IfStmtNode.reportSyntaxError("Expected { for elseifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            // remove left brace and right bracket
            tokens.remove(1);
            tokens.remove(0);

            BodyNode elseIfBody = BodyNode.ParseBodyNode(tokens, symTab, depth+1, funcDefs);

            if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
                IfStmtNode.reportSyntaxError("Expected } for elseifstmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            // remove right brace
            tokens.remove(0);

            elseIFBodys.add(elseIfBody);
            elseIfExprs.add(elseIfExpr);
        }

        if (tokens.get(0).getToken() == "else") {
            if (tokens.get(1).getTokenType() != TokenType.L_BRACE) {
                IfStmtNode.reportSyntaxError("Expected [ for elsestmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            // remove left brace and "else"
            tokens.remove(1);
            tokens.remove(0);

            elseNode = BodyNode.ParseBodyNode(tokens, symTab, depth+1, funcDefs);
            if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
                IfStmtNode.reportSyntaxError("Expected ] for elsestmt", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                return null;
            }

            // remove right brace
            tokens.remove(0);
        }

        IfStmtNode ifNode = new IfStmtNode(expr, body, elseIFBodys, elseIfExprs, elseNode);
        ifNode.depth = depth;

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
        String java = "if (" + this.cond.convertToJava() + ") {" + this.Body.convertToJava() + "}";
        for (int i = 0; i < this.elseIfBodys.size(); i++) {
            java += " else if (" + this.elseIfExprs.get(i).convertToJava() + ") {" + this.elseIfBodys.get(i).convertToJava() + "}";
        }
        if (this.elseBody != null) {
            java += " else {" + this.elseBody.convertToJava() + "}";
        }
            return java;
    }

    @Override
    public String convertToC() {
        String c = "if (" + this.cond.convertToC() + ") {" + this.Body.convertToC() + "}";
        for (int i = 0; i < this.elseIfBodys.size(); i++) {
            c += " else if (" + this.elseIfExprs.get(i).convertToC() + ") {" + this.elseIfBodys.get(i).convertToC() + "}";
        }
        if (this.elseBody != null) {
            c += " else {" + this.elseBody.convertToC() + "}";
        }
        return c;    }

    @Override
    public String convertToPython() {
        String tabs = "";
        for (int i = 0; i < this.depth + 1; i++) {
            tabs += "\t";
        }
        String python = tabs + "if " + this.cond.convertToPython() + ":\n\t" + tabs + this.Body.convertToPython() + "\n";
        for (int i = 0; i < this.elseIfBodys.size(); i++) {
            python += tabs + "elif " + this.elseIfExprs.get(i).convertToPython() + ":\n\t" + tabs + this.elseIfBodys.get(i).convertToPython() + "\n";
        }
        if (this.elseBody != null) {
            python += tabs + "else:\n\t" + tabs + this.elseBody.convertToPython() + "\n";
        }
        return python;
    }

    @Override
    public boolean validateTree() {
        System.out.println("Validating " + this.getClass());
        if (!(this.cond.validateTree() && this.Body.validateTree())) {
            return false;
        }
        for (int i = 0; i < this.elseIfBodys.size(); i++) {
            if (!(this.elseIfBodys.get(i).validateTree() && this.elseIfExprs.get(i).validateTree())) {
                return false;
            }
        }
        if (this.elseBody != null) {
            return this.elseBody.validateTree();
        }
        return true;
    }
}
