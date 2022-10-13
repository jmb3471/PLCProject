import java.util.ArrayList;

public class AsmtNode extends StmtNode {
    
    private String id;
    private ExprNode cond;
    private String type;

    public AsmtNode(String id, ExprNode cond, String type)
    {
        this.id = id;
        this.cond = cond;
        this.type = type;
    }

    public static AsmtNode ParseAsmtNode(ArrayList<Token> tokens) throws Exception {

        ExprNode cond = null;
        String id = null;
        String type = null;

        ArrayList<String> types = new ArrayList<>();
        types.add("Double");
        types.add("Integer");
        types.add("String");
        types.add("Boolean");

        if (types.contains(tokens.get(0).getToken()))
        {
            type = tokens.get(0).getToken();
            id = tokens.get(1).getToken();

            // remove "=" and <id> and string value
            tokens.remove(2);
            tokens.remove(1);
            tokens.remove(0);
            cond = ExprNode.ParseExprNode(tokens);
        }
        // if the assignment begins with an <id>
        else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            id = tokens.get(0).getToken();
            // remove <id> and "="
            tokens.remove(1);
            tokens.remove(0);
            cond = ExprNode.ParseExprNode(tokens);
        }
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            AsmtNode.reportError("Expected Semicolon, found none", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
        }
        return new AsmtNode(id, cond, type);
    }

    @Override
    public String convertToJott() {

        String jott = "";
        if (!type.equals(null))
            jott += type + " ";
        jott += id + " = ";
        jott += cond.convertToJott();
        jott += ";";

        return jott;
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
