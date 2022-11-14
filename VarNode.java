import java.util.ArrayList;

public class VarNode extends JottNode implements JottTree {
    public String id;


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
        return this.id;
    }

    @Override
    public String convertToC() {
        return this.id;
    }

    @Override
    public String convertToPython() {
        return this.id;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}