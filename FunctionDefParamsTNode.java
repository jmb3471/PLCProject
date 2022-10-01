public class FunctionDefParamsTNode extends JottNode implements JottTree{
    private Token token;
    private JottNode id;
    private JottNode varType;
    private JottNode function_def_params_t;
    private String type;


    public FunctionDefParamsTNode(Token token) {
        this.token = token;
        this.id = null;
        this.varType = null;;
        this.function_def_params_t = null;
        this.type = "FunctionDefParamT";
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
