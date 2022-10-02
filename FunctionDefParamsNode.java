import java.util.List;

public class FunctionDefParamsNode extends JottNode implements JottTree{
    private JottNode id;
    private JottNode varType;
    private JottNode function_def_params_t;
    private String type;


    public FunctionDefParamsNode() {
        this.id = null;
        this.varType = null;;
        this.function_def_params_t = null;
        this.type = "FunctionDefParam";
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
