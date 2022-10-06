import java.util.ArrayList;

public class BExprNode extends JottNode {
    
    private JottNode id;
    private JottNode funcCall;
    private boolean bool;
    private String relOp;
    private JottNode dExpr;
    private JottNode iExpr;
    private JottNode sExpr;
    private JottNode bExpr;

    public BExprNode()
    {
        id = null;
        funcCall = null;
        bool = true;
        relOp = null;
        dExpr = null;
        iExpr = null;
        sExpr = null;
        bExpr = null;
    }

    public static BExprNode ParseBExprNode(ArrayList<Token> tokens) {
        return null;
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

    public void setBoolean(boolean bool)
    {
        this.bool = bool;
    }

    public void setRelOp(String relOp)
    {
        if (ValidationUtils.validateRelOp(relOp))
            this.relOp = relOp;
        else
            this.relOp = null;
    }

    public void setIExpr(IExprNode node)
    {
        iExpr = node;
    }

    public void setDExpr(DExprNode node)
    {
        dExpr = node;
    }

    public void setSExpr(SExprNode node)
    {
        sExpr = node;
    }

    public void setBExpr(BExprNode node)
    {
        bExpr = node;
    }


}
