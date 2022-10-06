import java.util.ArrayList;

public class BodyStmtNode extends JottNode implements JottTree {
    private JottNode if_stmt;
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
        BodyStmtNode bodyStmt = null;
        // Check if the BodyStmt is an if stmt
        if (tokens.get(0).getToken().equals("if")) {
            // Remove the "if"
            tokens.remove(0);
            bodyStmt = IfStmtNode.ParseBodyStmtNode(tokens);
        }

         // Check if the BodyStmt is a while loop
        else if (tokens.get(0).getToken().equals("while")) {
            bodyStmt = While_Loop_Node.ParseWhileLoopNode(tokens);
        }

        // The BodyStmt must just be a stmt
        else {
            if (!tokens.get(0).getTokenType().equals(TokenType.ID_KEYWORD)) {
                return null;
            }
            bodyStmt = StmtNode.ParseStmtNode(tokens);
        }
        return bodyStmt;
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