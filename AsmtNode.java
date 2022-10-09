import java.net.IDN;
import java.util.ArrayList;

public class AsmtNode extends StmtNode {
    
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

    public static AsmtNode ParseAstmtNode(ArrayList<Token> tokens) {
        return new AsmtNode();
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



}
