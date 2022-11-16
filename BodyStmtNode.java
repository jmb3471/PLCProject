import java.util.ArrayList;
import java.util.HashMap;

public class BodyStmtNode extends JottNode {

    private IfStmtNode if_stmt;
    private While_Loop_Node while_stmt;
    private StmtNode stmt;
    private boolean ifStmt;


    public BodyStmtNode() {
        this.if_stmt = null;
        this.while_stmt = null;
        this.stmt = null;
        this.type = "BodyStmt";
        this.ifStmt = false;
    }


    public static BodyStmtNode ParseBodyStmtNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        BodyStmtNode bodyStmt;
        // Check if the BodyStmt is an if stmt
        if (tokens.get(0).getToken().equals("if")) {
            // Remove the "if"
            tokens.remove(0);
            bodyStmt = IfStmtNode.ParseIfStmtNode(tokens, symTab, depth, funcDefs);
            bodyStmt.depth = depth;
            bodyStmt.ifStmt = true;
        }

         // Check if the BodyStmt is a while loop
        else if (tokens.get(0).getToken().equals("while")) {
            tokens.remove(0);
            bodyStmt = While_Loop_Node.ParseWhileLoopNode(tokens, symTab, depth, funcDefs);
            bodyStmt.depth = depth;
        }

        // The BodyStmt must just be a stmt
        else {
            bodyStmt = StmtNode.ParseStmtNode(tokens, symTab, depth, funcDefs);
            bodyStmt.depth = depth;
        }
        return bodyStmt;
    }


    @Override
    public String convertToJott() {
        String jott = "";
        if (this.if_stmt != null) {
            jott += this.if_stmt.convertToJott();
        }
        if (this.while_stmt != null) {
            jott += this.while_stmt.convertToJott();
        }
        if (this.stmt != null) {
            jott += this.stmt.convertToJott();
        }
        return jott;
    }

    @Override
    public String convertToJava() {
        String java = "";
        if (this.if_stmt != null) {
            java += this.if_stmt.convertToJava();
        }
        if (this.while_stmt != null) {
            java += this.while_stmt.convertToJava();
        }
        if (this.stmt != null) {
            java += this.stmt.convertToJava();
        }
        return java;
    }

    @Override
    public String convertToC() {
        String c = "";
        if (this.if_stmt != null) {
            c += this.if_stmt.convertToC();
        }
        if (this.while_stmt != null) {
            c += this.while_stmt.convertToC();
        }
        if (this.stmt != null) {
            c += this.stmt.convertToC();
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = "";
        if (this.if_stmt != null) {
            python += this.if_stmt.convertToPython();
        }
        if (this.while_stmt != null) {
            python += this.while_stmt.convertToPython();
        }
        if (this.stmt != null) {
            python += this.stmt.convertToPython();
        }
        return python;
    }

    @Override
    public boolean validateTree() {
        System.out.println("Validating " + this.getClass());
        if (this.if_stmt != null) {
            return if_stmt.validateTree();
        }
        if (this.while_stmt != null) {
            return while_stmt.validateTree();
        }
        if (this.stmt != null) {
            return stmt.validateTree();
        }
        return false;
    }

    public boolean isIfStmt() {
        return ifStmt;
    }
}