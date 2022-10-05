public class VarDecNode extends JottNode {

    private JottNode type;
    private JottNode id;
    private JottNode endStmt;


    public VarDecNode()
    {
        type = null;
        id = null;
        endStmt = null;
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

    public void setTypeNode(TypeNode node)
    {
        type = node;
    }

    public void setIdNode(IdNode node)
    {
        id = node;
    }

    public void setEndStmtNode(EndStmtNode node)
    {
        endStmt = node;
    }
    
}
