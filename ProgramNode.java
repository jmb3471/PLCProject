import java.util.ArrayList;


public class ProgramNode extends JottNode {

    private ArrayList<FunctionDefNode> funcDefs;

    public ProgramNode(ArrayList<FunctionDefNode> funcDefs) {
        this.funcDefs = funcDefs;
    }


    public static JottTree ParseProgram(ArrayList<Token> tokens) throws Exception {
        // While there are still tokens left, create new FunctionDefNodes
        // and add them to the ArrayList of FunctionDefNodes

        ArrayList<FunctionDefNode> funcDefs = new ArrayList<>();
        while (!tokens.isEmpty()){
            FunctionDefNode funcDefNode = FunctionDefNode.ParseFunctionDefNode(tokens);
            funcDefs.add(funcDefNode);
        }

        ProgramNode program = new ProgramNode(funcDefs);
        return program;
    }
    

    @Override
    public String convertToJott() {
        String jott = "";
        for (int i = 0; i < this.funcDefs.size(); i++) {
            jott += this.funcDefs.get(i).convertToJott();
        }
        return jott;
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
