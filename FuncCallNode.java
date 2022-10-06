import java.util.ArrayList;

public class FuncCallNode extends JottNode implements JottTree {
    
    private JottNode id;
    private JottNode params;

    public FuncCallNode()
    {
        id = null;
    }

    public static FuncCallNode ParseFuncCallNode(ArrayList<Token> tokens) {
        IdNode idNode = IdNode.ParseIdNode(tokens);

        // remove id and [
        tokens.remove(1);
        tokens.remove(0);


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

}
