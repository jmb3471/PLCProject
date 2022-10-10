import java.util.ArrayList;

public class IdNode extends JottNode implements JottTree {
    private String id;

    public IdNode(String id) {
        this.id = id;
    }

    public static IdNode ParseIdNode(ArrayList<Token> tokens) {
        return new IdNode(tokens.get(0).getToken());
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
