import java.util.ArrayList;



abstract class JottNode implements JottTree {

    private ArrayList<JottNode> children = new ArrayList<JottNode>();

    public String value;

    public String type;
    public int depth = 0;

    public JottNode() {

    }

    public static void reportSyntaxError(String message, String filename, int lineNumber) throws Exception {
        System.err.println("Syntax Error:" + "\n" + message + "\n" + filename + ":" + lineNumber);
        throw new Exception();
    }

    public static void reportSemanticError(String message, String filename, int lineNumber) throws Exception {
        System.err.println("Semantic Error:" + "\n" + message + "\n" + filename + ":" + lineNumber);
        throw new Exception();
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
