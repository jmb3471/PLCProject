import java.net.IDN;

public class AsmtNode extends JottNode {
    
    private JottNode id;
    private JottNode dExpr;
    private JottNode iExpr;
    private JottNode sExpr;
    private JottNode bExpr;
    private JottNode endStmt;

    public AsmtNode()
    {
        id = null;
        dExpr = null;
        iExpr = null;
        sExpr = null;
        bExpr = null;
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

    public void setIdNode(IdNode node)
    {
        id = node;
    }
    public void setDExpr(DExprNode node)
    {
        dExpr = node;
    }
    public void setIExpr(IExprNode node)
    {
        iExpr = node;
    }
    public void setSExpr(SExprNode node)
    {
        sExpr = node;
    }
    public void setBExpr(BExprNode node)
    {
        bExpr = node;
    }
    public void setEndStmtNode(EndStmtNode node)
    {
        endStmt = node;
    }




}
