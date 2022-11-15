import java.util.*;

public class VarDecNode extends StmtNode {

    private String type;
    private IdNode id;


    public VarDecNode(String type, IdNode id)
    {
        this.type = type;
        this.id = id;
    }

    public static VarDecNode ParseVarDecNode(ArrayList<Token> tokens, HashMap<String, String> symTab) throws Exception {
        List<String> Types = Arrays.asList("Double", "Integer", "String", "Boolean");
        String type = tokens.get(0).getToken();
        if (!Types.contains(type)) {
            VarDecNode.reportSyntaxError("Expected valid type for variable declaration",
                                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }
        tokens.remove(0);
        IdNode idNode = IdNode.ParseIdNode(tokens);
        tokens.remove(0);
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            VarDecNode.reportSyntaxError("Expected semicolon end for variable declaration",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }
        symTab.put(idNode.getId(), type + "_undf");
        return new VarDecNode(type, idNode);
    }
    @Override
    public String convertToJott() {
        return this.type + " " + this.id + ";";
    }

    @Override
    public String convertToJava() {
        return this.type + " " + this.id + ";";
    }

    @Override
    public String convertToC() {
        if (Objects.equals(this.type, "String")) {
            return "char " + this.id + "[100];";
        }
        return this.type + " " + this.id + ";";
    }

    @Override
    public String convertToPython() {
        return "\n";
    }

    @Override
    public boolean validateTree() {
        return true;
    }

}
