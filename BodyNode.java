import java.util.ArrayList;
import java.util.HashMap;

public class BodyNode extends JottNode {

    private ArrayList<BodyStmtNode> bodyStmts;
    private ExprNode ReturnStmt;
    private HashMap symTab;

    public BodyNode(ArrayList<BodyStmtNode> bodyStmts, ExprNode returnStmt, HashMap symTab) {
        this.bodyStmts = bodyStmts;
        this.ReturnStmt = returnStmt;
        this.symTab = symTab;
    }

    public static BodyNode ParseBodyNode(ArrayList<Token> tokens, HashMap symTab) throws Exception {
        ArrayList<BodyStmtNode> bodyStmts = new ArrayList<>();
        ExprNode exprNode = null;

        while (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            if (tokens.get(0).getToken().equals("return")) {

                // remove return token
                tokens.remove(0);

                exprNode = ExprNode.ParseExprNode(tokens, symTab);

                if (tokens.get(0).getToken().equals(";")) {

                    // remove ;
                    tokens.remove(0);
                    break;
                }
                else {
                    BodyNode.reportError("Expected ';' did not get it", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                    return null;
                }
            }
            else {
                BodyStmtNode bodyStmt = BodyStmtNode.ParseBodyStmtNode(tokens, symTab);
                bodyStmts.add(bodyStmt);
            }
        }

        // remove }
        tokens.remove(0);

        BodyNode bodyNode = new BodyNode(bodyStmts, exprNode, symTab);

        return bodyNode;
    }
    

    @Override
    public String convertToJott() {
        String jott = "";
        for (int i = 0; i < this.bodyStmts.size(); i++) {
            jott += this.bodyStmts.get(i).convertToJott();
        }
        if (this.ReturnStmt != null) {
            jott += "return " + this.ReturnStmt.convertToJott() + ";";
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

    public boolean validateTree(String type) {
        if (this.ReturnStmt == null) {
            if (!type.equals("Void")) {
                return false;
            }
        }
        else {
            if (!this.ReturnStmt.equals(type)) {
                return false;
            }
        }
        for (BodyStmtNode bodyStmtNode: this.bodyStmts) {
            if (!bodyStmtNode.validateTree()) {
                return false;
            }
        }
        return true;
    }
}
