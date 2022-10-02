import java.util.List;

public class FunctionListNode extends JottNode implements JottTree {
    private List<JottNode> functionList;
    private FunctionListNode functionDef;
    private String type;


    public FunctionListNode() {
        this.functionList = null;
        this.functionDef = null;;
        this.type = "FunctionList";
    }

    @Override
    public String convertToJott() {
        String s = "";
        for (int i = 0; i < functionList.size(); i++) {
            s += functionList.get(i).convertToJott();
        }
        return s;
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
