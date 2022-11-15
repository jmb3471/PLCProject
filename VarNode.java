import java.util.ArrayList;
import java.util.HashMap;

public class VarNode extends Operand implements JottTree {

    public String id;
    public String type;

    public VarNode(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public static VarNode ParseVarNode(ArrayList<Token> tokens, HashMap<String, String> symTab) {
        String id = tokens.get(0).getToken();
        String type = symTab.get(id);
        return new VarNode(id, type);
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
    public String getType() {
        return type;
    }

    @Override
    public boolean validateTree() {
        /*if (type == null) {
            reportSemanticError("Undefined variable: " + id, );
        }*/
        return true;
    }
}