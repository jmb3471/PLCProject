import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class FuncCallNode extends StmtNode {

    public IdNode getId() {
        return id;
    }

    public IdNode id;
    private ArrayList<ExprNode> exprNodes;
    private HashMap<String, String> symTab;
    public ArrayList<FunctionDefNode> funcdefs;
    private int args;
    private String fileName;
    private int lineNumber;

    private ArrayList<String> builtinfuncs = new ArrayList<>(Arrays.asList("print", "input", "concat", "length"));

    public Boolean endStmt;
    public FuncCallNode(IdNode id, ArrayList<ExprNode> exprNodes, HashMap<String, String> symTab, int args)
    {
        this.id = id;
        this.exprNodes = exprNodes;
        this.endStmt = true;
        this.symTab = symTab;
        this.args = args;
    }

    public void setEndStmt() {
        this.endStmt = false;
    }

    public static FuncCallNode ParseFuncCallNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        Token token = tokens.get(0);
        String fileName = token.getFilename();
        int lineNumber = token.getLineNum();

        IdNode idNode = IdNode.ParseIdNode(tokens);

        // remove id and [
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<ExprNode> exprNodes = new ArrayList<>();

        int args = 0;

        while (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ExprNode exprNode = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);
            exprNodes.add(exprNode);
            args++;
        }

        // remove ]
        tokens.remove(0);

        FuncCallNode funcCallNode = new FuncCallNode(idNode, exprNodes, symTab, args);
        funcCallNode.funcdefs = funcDefs;
        funcCallNode.fileName = fileName;
        funcCallNode.lineNumber = lineNumber;
        return funcCallNode;

    }

    @Override
    public String convertToJott() {
        String jott = this.id.convertToJott() + "[";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                jott += this.exprNodes.get(i).convertToJott();
            }
            else {
                jott += this.exprNodes.get(i).convertToJott() + ",";
            }
        }
        jott += "]";
        if (this.endStmt) {
            jott += ';';
        }
        return jott;

    }

    @Override
    public String convertToJava() {
        String java = "";
        if (this.builtinfuncs.contains(this.id.getId())) {
            if (Objects.equals(this.id.getId(), "print")) {
                java += "System.out.println(";
            }
            if (Objects.equals(this.id.getId(), "input")) {
                java += "input(";
            }
            if (Objects.equals(this.id.getId(), "concat")) {
                java += this.exprNodes.get(0).convertToJava() + " + " + this.exprNodes.get(1).convertToJava();
            }
            if (Objects.equals(this.id.getId(), "length")) {
                java += this.exprNodes.get(0).convertToJava() + " .length()";
                if (this.endStmt) {
                    java += ';';
                }
                return java;
            }
        }
        else {
            java = this.id.convertToJava() + "(";
        }
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                java += this.exprNodes.get(i).convertToJava();
            }
            else {
                java += this.exprNodes.get(i).convertToJava() + ",";
            }
        }
        java += ")";
        if (this.endStmt) {
            java += ';';
        }
        return java;
    }

    @Override
    public String convertToC() {
        String c = "";
        if (this.builtinfuncs.contains(this.id.getId())) {
            if (Objects.equals(this.id.getId(), "print")) {
                c += "printf(";
            }
            if (Objects.equals(this.id.getId(), "input")) {

            }
            if (Objects.equals(this.id.getId(), "concat")) {
                c += "strcat(";
            }
            if (Objects.equals(this.id.getId(), "length")) {
                c += "strlen(";
            }
        }
        else {
            c += this.id.convertToC() + "(";
        }
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                c += this.exprNodes.get(i).convertToC();
            }
            else {
                c += this.exprNodes.get(i).convertToC() + ",";
            }
        }
        c += ")";
        if (this.endStmt) {
            c += ';';
        }
        if (Objects.equals(this.id.getId(), "print")) {
            c += "printf(\"\n\")";
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String tabs = "";
        for (int i = 0; i < this.depth; i++) {
            tabs += "\t";
        }
        String python = "";
        if (this.builtinfuncs.contains(this.id.getId())) {
            if (Objects.equals(this.id.getId(), "print")) {
                python += "print(";
            }
            if (Objects.equals(this.id.getId(), "input")) {
                python += "input(" + this.exprNodes.get(0).convertToPython() + ")";
                if (this.endStmt) {
                    python += '\n';
                }
                return python;
            }
            if (Objects.equals(this.id.getId(), "concat")) {
                python += this.exprNodes.get(0).convertToPython() + " + " + this.exprNodes.get(1).convertToPython();
            }
            if (Objects.equals(this.id.getId(), "length")) {
                python += "len(";
            }
        }
        else {
            python += this.id.convertToPython() + "(";
        }
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                python += this.exprNodes.get(i).convertToPython();
            }
            else {
                python += this.exprNodes.get(i).convertToPython() + ",";
            }
        }
        python += ")";
        if (this.endStmt) {
            python += '\n';
        }
        return python;    }

    @Override
    public boolean validateTree() {
        try {
            FunctionDefNode functionDefNode = null;
            for (int i = 0; i < this.funcdefs.size(); i++) {
                if (Objects.equals(this.funcdefs.get(i).ID, this.id.getId())) {
                    functionDefNode = this.funcdefs.get(i);
                }
            }
            Boolean funcDefExists = functionDefNode != null;
            if (this.builtinfuncs.contains(this.id.getId())) {
                if (Objects.equals(this.id.getId(), "print")) {
                    return this.exprNodes.size() == 1;
                }
                if (Objects.equals(this.id.getId(), "input")) {
                    if (this.exprNodes.size() == 2) {
                        if (Objects.equals(this.exprNodes.get(0).type, "String") &&
                                Objects.equals(this.exprNodes.get(1).type, "Integer")) {
                            return true;
                        }
                    }
                    return false;
                }
                if (Objects.equals(this.id.getId(), "concat")) {
                    if (this.exprNodes.size() == 2) {
                        if (Objects.equals(this.exprNodes.get(0).type, "String") &&
                                Objects.equals(this.exprNodes.get(1).type, "String")) {
                            return true;
                        }
                    }
                    return false;
                }
                if (Objects.equals(this.id.getId(), "length")) {
                    if (this.exprNodes.size() == 1) {
                        return Objects.equals(this.exprNodes.get(0).type, "String");
                    }
                    return false;
                }
            }
            Boolean numCorrectParams = this.exprNodes.size() == functionDefNode.params.size();
            if (!numCorrectParams) {
                return false;
            }
            if (funcDefExists) {
                for (int i = 0; i < functionDefNode.params.size(); i++) {
                    if (!(this.exprNodes.get(i).getType().equals(functionDefNode.params.get(i).type))) {
                        reportSemanticError("Argument type does not match parameter type", this.fileName, this.lineNumber);
                        return false;
                    }
                }
            }
            else {
                reportSemanticError("Call to undefined function " + this.id.getId(), this.fileName, this.lineNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
