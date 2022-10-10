import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarDecNode extends StmtNode {

    private String type;
    private IdNode id;


    public VarDecNode(String type, IdNode id)
    {
        this.type = type;
        this.id = id;
    }

    public static VarDecNode ParseVarDecNode(ArrayList<Token> tokens) throws Exception {
        List<String> Types = Arrays.asList("Double", "Integer", "String", "Boolean");
        String type = tokens.get(0).getToken();
        if (!Types.contains(type)) {
            VarDecNode.reportError("Expected valid type for variable declaration",
                                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }
        tokens.remove(0);
        IdNode idNode = IdNode.ParseIdNode(tokens);
        tokens.remove(0);
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            VarDecNode.reportError("Expected semicolon end for variable declaration",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }
        return new VarDecNode(type, idNode);
    }
    @Override
    public String convertToJott() {
        return this.type + " " + this.id + ";";
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
