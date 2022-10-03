public class VarTypeNode extends JottNode implements JottTree {
    private Token token;
    private String type;

    public VarTypeNode() {
        this.type = "Type";
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

    public void setId(Token token) {
        this.token = token;
    }
}
