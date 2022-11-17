import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class BodyNode extends JottNode {

    private ArrayList<BodyStmtNode> bodyStmts;
    private ExprNode ReturnStmt;
    private HashMap<String, String> symTab;
    private boolean ifStmt;

    public BodyNode(ArrayList<BodyStmtNode> bodyStmts, ExprNode returnStmt, HashMap<String, String> symTab, boolean ifStmt) {
        this.bodyStmts = bodyStmts;
        this.ReturnStmt = returnStmt;
        this.symTab = symTab;
        this.ifStmt = ifStmt;
    }

    public static BodyNode ParseBodyNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        boolean ifStmt = false;
        ArrayList<BodyStmtNode> bodyStmts = new ArrayList<>();
        ExprNode exprNode = null;

        while (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            if (tokens.get(0).getToken().equals("return")) {

                // remove return token
                tokens.remove(0);

                exprNode = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);

                if (tokens.get(0).getToken().equals(";")) {

                    // remove ;
                    tokens.remove(0);
                    break;
                }
                else {
                    BodyNode.reportSyntaxError("Expected ';' did not get it", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
                    return null;
                }
            }
            else {
                BodyStmtNode bodyStmt = BodyStmtNode.ParseBodyStmtNode(tokens, symTab, depth, funcDefs);
                if (bodyStmt.isIfStmt()) {
                    ifStmt = true;
                }
                bodyStmts.add(bodyStmt);
            }
        }

        // remove }
        tokens.remove(0);

        BodyNode bodyNode = new BodyNode(bodyStmts, exprNode, symTab, ifStmt);
        bodyNode.depth = depth;

        return bodyNode;
    }

    public ExprNode getReturnStmt() {
        if (this.ReturnStmt == null) {
            return null;
        }
        return this.ReturnStmt;
    }

    @Override
    public String convertToJott() {
        String jott = "";
        for (BodyStmtNode bodyStmt : this.bodyStmts) {
            jott += bodyStmt.convertToJott();
        }
        if (this.ReturnStmt != null) {
            jott += "return " + this.ReturnStmt.convertToJott() + ";";
        }
        return jott;
    }

    @Override
    public String convertToJava() {
        String java = "";
        for (BodyStmtNode bodyStmt : this.bodyStmts) {
            java += bodyStmt.convertToJava();
        }
        if (this.ReturnStmt != null) {
            java += "return " + this.ReturnStmt.convertToJava() + ";";
        }
        return java;
    }

    @Override
    public String convertToC() {
        String c = "";
        for (BodyStmtNode bodyStmt : this.bodyStmts) {
            c += bodyStmt.convertToC();
        }
        if (this.ReturnStmt != null) {
            c += "return " + this.ReturnStmt.convertToC() + ";";
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String tabs = "";
        for (int i = 0; i < this.depth; i++) {
            tabs += "\t";
        }
        String python = tabs;
        for (BodyStmtNode bodyStmt : this.bodyStmts) {
            if (bodyStmt instanceof StmtNode) {
                python += tabs;
            }
            python += bodyStmt.convertToPython();
        }
        if (this.ReturnStmt != null) {
            python += tabs + "return " + this.ReturnStmt.convertToPython();
        }
        return python;
    }

    @Override
    public boolean validateTree() {
        //System.out.println("Validating " + this.getClass());
        for (BodyStmtNode bodyStmts : this.bodyStmts) {
            if (!bodyStmts.validateTree()) {
                return false;
            }
        }
        if (this.ReturnStmt != null) {
            return this.ReturnStmt.validateTree();
        }
        return true;
    }

    public boolean validateTree(String type) {
        if (this.ReturnStmt == null) {
            if (!type.equals("Void")) {
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

    public boolean hasIfStmt() {
        return ifStmt;
    }

    public boolean validReturn() {
        for (BodyStmtNode bodyStmtNode: this.bodyStmts) {
            if (bodyStmtNode.isIfStmt()) {
                if(!((IfStmtNode)bodyStmtNode).validReturn()) {
                    return false;
                }
            }
        }
        return true;
    }
}
