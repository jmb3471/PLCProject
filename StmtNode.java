import java.util.ArrayList;
import java.util.HashMap;

public class StmtNode extends BodyStmtNode {

    private AsmtNode asmtNode;
    private VarDecNode varDecNode;
    private FuncCallNode funcCallNode;
    private HashMap<String, String> symTab;


    public StmtNode()
    {
        asmtNode = null;
        varDecNode = null;
        funcCallNode = null;
    }

    public static StmtNode ParseStmtNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        StmtNode stmtNode;
        if (tokens.get(2).getToken().equals("=") || (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD && (tokens.get(1).getToken().equals("=") || tokens.get(2).getToken().equals("=")))) {
            stmtNode = AsmtNode.ParseAsmtNode(tokens, symTab, depth, funcDefs);
        }
        else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            stmtNode = FuncCallNode.ParseFuncCallNode(tokens, symTab, depth, funcDefs);
        }
        else {
            stmtNode = VarDecNode.ParseVarDecNode(tokens, symTab);
        }
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            ExprNode.reportSyntaxError("Expected end of line", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
            return null;
        }

        // remove ;
        tokens.remove(0);
        stmtNode.depth = depth;
        return stmtNode;
    }

    @Override
    public String convertToJott() {
        String jott = "";
        if (this.asmtNode != null) {
            jott += this.asmtNode.convertToJott();
        } else if (this.varDecNode != null) {
            jott += this.varDecNode.convertToJott();
        } else if (this.funcCallNode != null) {
            jott += this.funcCallNode.convertToJott();
            jott += ";";
        }
        return jott;
    }

    @Override
    public String convertToJava() {
        String java = "";
        if (this.asmtNode != null) {
            java += this.asmtNode.convertToJava();
        } else if (this.varDecNode != null) {
            java += this.varDecNode.convertToJava();
        } else if (this.funcCallNode != null) {
            java += this.funcCallNode.convertToJava();
            java += ";";
        }
        return java;
    }

    @Override
    public String convertToC() {
        String c = "";
        if (this.asmtNode != null) {
            c += this.asmtNode.convertToC();
        } else if (this.varDecNode != null) {
            c += this.varDecNode.convertToC();
        } else if (this.funcCallNode != null) {
            c += this.funcCallNode.convertToC();
            c += ";";
        }
        return c;    }

    @Override
    public String convertToPython() {
        String python = "";
        String tabs = "";
        for (int i = 0; i < this.depth + 1; i++) {
            tabs += "\t";
        }
        if (this.asmtNode != null) {
            python += tabs + this.asmtNode.convertToJava();
        } else if (this.varDecNode != null) {
            python += tabs + this.varDecNode.convertToJava();
        } else if (this.funcCallNode != null) {
            python += tabs + this.funcCallNode.convertToJava();
            python += ";";
        }
        return python;
    }

    @Override
    public boolean validateTree() {
        System.out.println("Validating " + this.getClass());
        return asmtNode.validateTree() && varDecNode.validateTree() && funcCallNode.validateTree();
    }


}
