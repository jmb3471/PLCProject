import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ProgramNode extends JottNode {

    private ArrayList<FunctionDefNode> funcDefs;
    public static boolean hasDuplicate = false;
    public static boolean hasMain = false;

    public ProgramNode(ArrayList<FunctionDefNode> funcDefs) {
        this.funcDefs = funcDefs;
    }


    public static JottTree ParseProgram(ArrayList<Token> tokens) throws Exception {
        // While there are still tokens left, create new FunctionDefNodes
        // and add them to the ArrayList of FunctionDefNodes

        ArrayList<FunctionDefNode> funcDefs = new ArrayList<>();
        while (!tokens.isEmpty()){
            FunctionDefNode funcDefNode = FunctionDefNode.ParseFunctionDefNode(tokens);
            for (FunctionDefNode funcDef : funcDefs) {
                if (Objects.equals(funcDef.ID, funcDefNode.ID)) {
                    hasDuplicate = true;
                }
            }
            if (Objects.equals(funcDefNode.ID, "main")) {
                hasMain = true;
            }
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
        if (hasMain && !hasDuplicate) {
            for (FunctionDefNode funcDef : funcDefs) {
                if (!funcDef.validateTree()) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
}
