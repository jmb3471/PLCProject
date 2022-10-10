import java.util.ArrayList;

public class StmtNode extends BodyStmtNode {

    private JottNode asmtNode;
    private JottNode varDecNode;
    private JottNode funcCallNode;


    public StmtNode()
    {
        asmtNode = null;
        varDecNode = null;
        funcCallNode = null;
    }

    public static StmtNode ParseStmtNode(ArrayList<Token> tokens) {
        StmtNode stmtNode;
        if (tokens.get(2).getToken() == "=" || (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD && tokens.get(1).getToken() == "=")) {
            stmtNode = AsmtNode.ParseAsmtNode(tokens);
        }
        else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            stmtNode = FuncCallNode.ParseStmtNode(tokens);
        }
        else {
            stmtNode = VarDecNode.ParseStmtNode(tokens);
        }
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

    public void setAsmtNode(AsmtNode node)
    {
        asmtNode = node;
    }

    public void setVarDecNode(VarDecNode node)
    {
        varDecNode = node;
    }

    public void setFuncCallNode(FuncCallNode node)
    {
        funcCallNode = node;
    }

}
