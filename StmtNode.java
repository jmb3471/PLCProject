public class StmtNode extends JottNode 
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
