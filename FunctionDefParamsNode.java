import java.util.List;

public class FunctionDefParamsNode extends JottNode{
    private JottNode id;
    private JottNode varType;
    private JottNode function_def_params_t;
    private String type;

    public void setIdNode(JottNode id) {
        this.id = id;
    }

    public void setVarTypeNode(JottNode varType) {
        this.varType = varType;
    }

    public void setFunctionDefParamsTNode(JottNode function_def_params_t) {
        this.function_def_params_t = function_def_params_t;
    }

    public FunctionDefParamsNode() {
        this.id = null;
        this.varType = null;;
        this.function_def_params_t = null;
        this.type = "FunctionDefParams";
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
