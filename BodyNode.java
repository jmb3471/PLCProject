public class BodyNode extends JottNode implements JottTree{
    private JottNode bodyStmt;
    private JottNode body;
    private JottNode returnStmt;
    private String type;


    public BodyNode() {
        this.bodyStmt = null;
        this.body = null;;
        this.returnStmt = null;
        this.type = "Body";
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
