public class DExprNode extends JottNode {

    private JottNode id;
    private double dbl;
    private String op;
    private JottNode dExpr;
    private JottNode funcCall;

    public DExprNode()
    {
        id = null;
        dbl = 0;
        op = "";
        dExpr = null;
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

    public void setDbl(double num)
    {
        if(ValidationUtils.validateDouble(num))
            dbl = num;
    }

    public void setOpNode(String val)
    {
        op = val;
    }

    public void setDExpr(DExprNode node)
    {
        dExpr = node;
    }

    public void setFuncCallNode(FuncCallNode node)
    {
        funcCall = node;
    }

    
}
