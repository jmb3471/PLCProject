import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FuncCallNode extends StmtNode {
    
    public IdNode id;
    private ArrayList<ExprNode> exprNodes;
    private HashMap symTab;
    public ArrayList<FunctionDefNode> funcdefs;

    public Boolean endStmt;
    public FuncCallNode(IdNode id, ArrayList<ExprNode> exprNodes, HashMap symTab)
    {
        this.id = id;
        this.exprNodes = exprNodes;
        this.endStmt = true;
        this.symTab = symTab;
    }

    public void setEndStmt() {
        this.endStmt = false;
    }

    public static FuncCallNode ParseFuncCallNode(ArrayList<Token> tokens, HashMap symTab, int depth, ArrayList<FunctionDefNode> funcDefs) throws Exception {
        IdNode idNode = IdNode.ParseIdNode(tokens);

        // remove id and [
        tokens.remove(1);
        tokens.remove(0);

        ArrayList<ExprNode> exprNodes = new ArrayList<>();

        while (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            ExprNode exprNode = ExprNode.ParseExprNode(tokens, symTab, depth, funcDefs);
            exprNodes.add(exprNode);
        }

        // remove ]
        tokens.remove(0);

        FuncCallNode funcCallNode = new FuncCallNode(idNode, exprNodes, symTab);
        funcCallNode.funcdefs = funcDefs;
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
        String java = this.id.convertToJava() + "(";
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
        String c = this.id.convertToJava() + "(";
        for (int i = 0; i < exprNodes.size(); i++) {
            if (i == exprNodes.size() - 1) {
                c += this.exprNodes.get(i).convertToJava();
            }
            else {
                c += this.exprNodes.get(i).convertToJava() + ",";
            }
        }
        c += ")";
        if (this.endStmt) {
            c += ';';
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = this.id.convertToPython() + "(";
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
        FunctionDefNode functionDefNode = null;
        for (int i = 0; i < this.funcdefs.size(); i++) {
            if (Objects.equals(this.funcdefs.get(i).ID, this.id.getId())) {
                functionDefNode = this.funcdefs.get(i);
            }
        }
        Boolean funcDefExists = functionDefNode != null;
        Boolean numCorrectParams = this.exprNodes.size() == this.funcdefs.size();
        Boolean correctParamTypes = true;
        for (int i = 0; i < functionDefNode.params.size(); i++) {
            if (this.exprNodes.get(i).evaluate() == functionDefNode.params.get(i).type) {
                correctParamTypes = true;
            }
            else {
                correctParamTypes = false;
            }
        }
         return funcDefExists && numCorrectParams && correctParamTypes;
    }

}
