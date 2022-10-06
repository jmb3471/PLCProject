import java.util.ArrayList;



abstract class JottNode implements JottTree {

    private ArrayList<JottNode> children = new ArrayList<JottNode>();

    public String value;

    public String type;

    public JottNode() {

    }

    public static void reportError(String message, String filename, int lineNumber) {
        System.err.println("Syntax Error:" + "\n" + message + "\n" + filename + ":" + lineNumber);
    }

    public ArrayList<JottNode> getChildren(){
        return this.children;
    }

    public void addChild(JottNode child){
        this.children.add(child);
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
