public class FunctionDefNode extends JottNode implements JottTree{
    private Token token;
    private JottNode id;
    private JottNode func_def_params;
    private JottNode func_return;
    private JottNode body;

    private String type;


    public FunctionDefNode() {
        this.token = token;
        this.id = null;
        this.func_def_params = null;;
        this.body = null;
        this.func_return = null;
        this.type = "FunctionDef";
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
}
