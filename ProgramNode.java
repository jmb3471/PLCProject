public class ProgramNode implements JottTree {

    private Token token;
    private JottNode functionList;
    private JottNode endOfFile;

    public ProgramNode() {
        
    }

    public ProgramNode(Token token) {

        this.token = null;
        this.functionList = null;
        this.endOfFile = null;;
    }
    
    public void addFunctionList(JottNode functionList) {
        this.functionList = functionList;
    }

    public void addEndOfFile(JottNode endOfFile) {
        this.endOfFile = endOfFile;
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
