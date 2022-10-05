public class IExprNode extends JottNode {

    private JottNode id;
    private int val;
    private String op;
    private JottNode iExpr;
    private JottNode funcCall;

    public IExprNode()
    {
        id = null;
        val = 0;
        op = "";
        iExpr = null;
        funcCall = null;
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

    public void setInt(int num)
    {
        if(ValidationUtils.validateInt(num))
            val = num;
    }

    public void setOpNode(String val)
    {
        op = val;
    }

    public void setIExpr(IExprNode node)
    {
        iExpr = node;
    }

    public void setFuncCallNode(FuncCallNode node)
    {
        funcCall = node;
    }

    
}