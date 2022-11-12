import java.util.ArrayList;
import java.util.HashMap;

public class StmtNode extends BodyStmtNode {

    private JottNode asmtNode;
    private JottNode varDecNode;
    private JottNode funcCallNode;
    private HashMap<String, String> symTab;


    public StmtNode()
    {
        asmtNode = null;
        varDecNode = null;
        funcCallNode = null;
    }

    public static StmtNode ParseStmtNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth) throws Exception {
        StmtNode stmtNode;
        if (tokens.get(2).getToken().equals("=") || (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD && (tokens.get(1).getToken().equals("=") || tokens.get(2).getToken().equals("=")))) {
            stmtNode = AsmtNode.ParseAsmtNode(tokens, symTab, depth);
        }
        else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            stmtNode = FuncCallNode.ParseFuncCallNode(tokens, symTab);
        }
        else {
            stmtNode = VarDecNode.ParseVarDecNode(tokens, symTab);
        }
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            ExprNode.reportError("Expected end of line", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove ;
        tokens.remove(0);
        stmtNode.depth = depth;
        return stmtNode;
    }

    @Override
    public String convertToJott() {
        String jott = "";
        if (this.asmtNode != null) {
            jott += this.asmtNode.convertToJott();
        } else if (this.varDecNode != null) {
            jott += this.varDecNode.convertToJott();
        } else if (this.funcCallNode != null) {
            jott += this.funcCallNode.convertToJott();
            jott += ";";
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
