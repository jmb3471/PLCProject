import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ProgramNode extends JottNode {

    private ArrayList<FunctionDefNode> funcDefs;
    public static boolean hasDuplicate = false;
    public static boolean hasMain = false;
    private String fileName;
    private int lineNumber;

    public String c_input_func = "char* input(char* str, int numchars) {\n\tprintf(\"%s\", str);\n\tchar userin[numchars + 1]\n\tscanf(\"%s\", userin);\n\treturn userin;\n}\n\n";

    public ProgramNode(ArrayList<FunctionDefNode> funcDefs, String fileName, int lineNumber) {
        this.funcDefs = funcDefs;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }


    public static JottTree ParseProgram(ArrayList<Token> tokens) throws Exception {
        // While there are still tokens left, create new FunctionDefNodes
        // and add them to the ArrayList of FunctionDefNodes

        ArrayList<FunctionDefNode> funcDefs = new ArrayList<>();
        String fileName = "";
        int lineNumber = 0;
        while (!tokens.isEmpty()){
            fileName = tokens.get(0).getFilename();
            FunctionDefNode funcDefNode = FunctionDefNode.ParseFunctionDefNode(tokens, funcDefs);
            for (FunctionDefNode funcDef : funcDefs) {
                if (Objects.equals(funcDef.ID, funcDefNode.ID)) {
                    hasDuplicate = true;
                    lineNumber = funcDef.getLineNumber();
                }
            }
            if (Objects.equals(funcDefNode.ID, "main")) {
                hasMain = true;
            }
            funcDefs.add(funcDefNode);
        }

        ProgramNode program = new ProgramNode(funcDefs, fileName, lineNumber);
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
        String java = "import java.util.Scanner;\npublic class Program { public String input(String prompt, int chars){ Scanner scanner = new Scanner(System.in); System.out.println(prompt); return scanner.nextLine(); }\n";
        for (int i = 0; i < this.funcDefs.size(); i++) {
            java += this.funcDefs.get(i).convertToJava();
        }
        return java += "}";
    }

    @Override
    public String convertToC() {
        String c = "#include <string.h>\n#include <stdio.h>\n#include <stdlib.h>\n\n" + c_input_func;
        for (int i = 0; i < this.funcDefs.size(); i++) {
            c += this.funcDefs.get(i).convertToC();
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = "";
        for (int i = 0; i < this.funcDefs.size(); i++) {
            python += this.funcDefs.get(i).convertToPython();
        }
        python += "main()";
        return python;
    }

    @Override
    public boolean validateTree() {
        //System.out.println("Validating " + this.getClass());
        if (hasMain && !hasDuplicate) {
            for (FunctionDefNode funcDef : funcDefs) {
                if (funcDef.ID.equals("main")) {
                    if (!funcDef.return_type.equals("Void")) {
                        try {
                            reportSemanticError("Main should be Void", this.fileName, funcDef.getLineNumber());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
                if (!funcDef.validateTree()) {
                    return false;
                }
            }
        }
        else {
                try {
                    if (!hasMain) {
                        reportSemanticError("Missing main function", this.fileName, 0);
                    }
                    if (hasDuplicate) {
                        reportSemanticError("Duplicate functions declared", this.fileName, this.lineNumber);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return false;
        }
        return true;
    }
}
