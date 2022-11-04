import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdNode extends JottNode implements JottTree {
    private String id;

    public IdNode(String id) {
        this.id = id;
    }

    public static IdNode ParseIdNode(ArrayList<Token> tokens) throws Exception {
        List<String> keywords = Arrays.asList("while", "if", "return", "else if", "else");
        if (keywords.contains(tokens.get(0).getToken())) {
            IdNode.reportError("Keyword cannot be used as id", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
        }
        return new IdNode(tokens.get(0).getToken());
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
        return true;
    }
}
