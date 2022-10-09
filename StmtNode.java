import java.util.ArrayList;

public class StmtNode extends BodyStmtNode implements JottTree
{
    private JottNode asmtNode;
    private JottNode varDecNode;
    private JottNode funcCallNode;
    private JottNode endStmtNode;

    public StmtNode()
    {
        asmtNode = null;
        varDecNode = null;
        funcCallNode = null;
        endStmtNode = null;
    }

    public static StmtNode ParseStmtNode(ArrayList<Token> tokens) {
        StmtNode stmtNode;
        if (tokens.get(2).getToken() == "=") {
            stmtNode = AsmtNode.ParseAstmtNode(tokens);
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

    public void setEndStmtNode(EndStmtNode node)
    {
        endStmtNode = node;
    }

}
