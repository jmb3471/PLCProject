import java.util.ArrayList;

public class VarNode extends JottNode implements JottTree {
    private String id;


    public VarNode(String id) {
        this.id = id;
    }

    public static VarNode ParseVarNode(ArrayList<Token> tokens) {
        return new VarNode(tokens.get(0).getToken());
    }

    @Override
    public String convertToJott() {
        return this.id;
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