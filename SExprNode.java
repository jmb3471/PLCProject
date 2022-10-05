public class SExprNode extends JottNode {

    private JottNode id;
    private JottNode funcCall;
    private JottNode strLiteral;

    public SExprNode()
    {
        id = null;
        funcCall = null;
        strLiteral = null;
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

    public void setIdNode(IdNode node)
    {
        id = node;
    }

    public void setFuncCallNode(FuncCallNode node)
    {
        funcCall = node;
    }

    public void setStrLiteral(StrLiteralNode node)
    {
        strLiteral = node;
    }
    
}
