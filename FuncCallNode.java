public class FuncCallNode extends JottNode {
    
    private JottNode id;

    public FuncCallNode()
    {
        id = null;
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
