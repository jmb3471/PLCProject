import java.util.ArrayList;

public class BodyStmtNode extends JottNode {

    private BodyStmtNode if_stmt;
    private JottNode while_stmt;
    private JottNode stmt;
    private String type;


    public BodyStmtNode() {
        this.if_stmt = null;
        this.while_stmt = null;
        this.stmt = null;
        this.type = "BodyStmt";
    }


    public static BodyStmtNode ParseBodyStmtNode(ArrayList<Token> tokens) {
        BodyStmtNode bodyStmt;
        // Check if the BodyStmt is an if stmt
        if (tokens.get(0).getToken().equals("if")) {
            // Remove the "if"
            tokens.remove(0);
            bodyStmt = IfStmtNode.ParseIfStmtNode(tokens);
        }

         // Check if the BodyStmt is a while loop
        else if (tokens.get(0).getToken().equals("while")) {
            tokens.remove(0);
            bodyStmt = While_Loop_Node.ParseWhileLoopNode(tokens);
        }

        // The BodyStmt must just be a stmt
        else {
            bodyStmt = StmtNode.ParseStmtNode(tokens);
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