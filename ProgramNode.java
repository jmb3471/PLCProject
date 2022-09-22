public class ProgramNode implements JottTree {
    private Token token;
    private JottTree functionList;
    private JottTree endOfFile;

    public ProgramNode(Token token) {
        this.token = token;
        this.functionList = null;
        this.endOfFile = null;
    }
    
    public void addFunctionList(JottTree functionList) {
        this.functionList = functionList;
    }

    public void addEndOfFile(JottTree endOfFile) {
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
