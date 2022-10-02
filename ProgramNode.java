public class ProgramNode extends JottNode implements JottTree {

    public JottNode functionList;
    public String endOfFile;

    public String type;


    public ProgramNode() {
        this.functionList = null;
        this.endOfFile = "$$";
        this.type = "Program";
    }

    @Override
    public String convertToJott() {
        return this.functionList.convertToJott();
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
