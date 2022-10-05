public class StrLiteralNode extends JottNode {

    private JottNode str;

    public StrLiteralNode()
    {
        str = null;
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

    public void setStrNode(StrNode node)
    {
        str = node;
    } 
    
}
