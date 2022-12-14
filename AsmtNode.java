import java.util.ArrayList;
import java.util.HashMap;

public class AsmtNode extends StmtNode {
    
    private String id;
    private ExprNode cond;
    private String type;

    public AsmtNode(String id, ExprNode cond, String type)
    {
        this.id = id;
        this.cond = cond;
        this.type = type;
    }

    public static AsmtNode ParseAsmtNode(ArrayList<Token> tokens, HashMap<String, String> symTab, int depth, ArrayList<FunctionDefNode> funcdef) throws Exception {

        ExprNode cond = null;
        String id = null;
        String type = null;

        ArrayList<String> types = new ArrayList<>();
        types.add("Double");
        types.add("Integer");
        types.add("String");
        types.add("Boolean");

        if (types.contains(tokens.get(0).getToken()))
        {
            type = tokens.get(0).getToken();
            id = tokens.get(1).getToken();
            symTab.put(id, type);

            // remove "=" and <id> and string value
            tokens.remove(2);
            tokens.remove(1);
            tokens.remove(0);
            cond = ExprNode.ParseExprNode(tokens, symTab, depth, funcdef);
        }
        // if the assignment begins with an <id>
        else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            id = tokens.get(0).getToken();
            if (symTab.containsKey(id)) {
                type = symTab.get(id);
            }
            // remove <id> and "="
            tokens.remove(1);
            tokens.remove(0);
            cond = ExprNode.ParseExprNode(tokens, symTab, depth, funcdef);
        }
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            AsmtNode.reportSyntaxError("Expected Semicolon, found none", tokens.get(0).getFilename(), tokens.get(0).getLineNum());
        }
        AsmtNode asmtNode = new AsmtNode(id, cond, type);
        asmtNode.depth = depth;
        return asmtNode;
    }

    @Override
    public String convertToJott() {
        String jott = "";
        if (type != null)
            jott += type + " ";
        jott += id + " = ";
        jott += cond.convertToJott();
        if (jott.charAt(jott.length() - 1) != ';') {
            jott += ';';
        }

        return jott;
    }

    @Override
    public String convertToJava() {
        String java = "";
        if (type != null) {
            String j_type;
            switch(type) {
                case "Integer":
                    j_type= "int";
                    break;

                case "String":
                    j_type = "String";
                    break;

                case "Boolean":
                    j_type = "boolean";
                    break;

                case "Double":
                    j_type = "double";
                    break;

                default:
                    System.out.println("Invalid type");
                    return "";
            }
            java += j_type + " ";
        }
        java += id + " = ";
        java += cond.convertToJava();
        if (java.charAt(java.length() - 1) != ';') {
            java += ';';
        }

        return java;
    }

    @Override
    public String convertToC() {
        String c = "";
        if (type != null) {
            String c_type;
            switch(type) {
                case "Integer":
                    c_type= "int";
                    break;

                case "String":
                    c_type = "char*";
                    break;

                case "Boolean":
                    c_type = "bool";
                    break;

                case "Double":
                    c_type = "double";
                    break;

                default:
                    System.out.println("Invalid type");
                    return "";
            }
            c += c_type + " ";
        }  
        c += id + " = ";
        c += cond.convertToC();
        if (c.charAt(c.length() - 1) != ';') {
            c += ';';
        }

        return c;
    }

    @Override
    public String convertToPython() {
        String python = "";

        python += id + " = ";
        python += cond.convertToJava();
        if (python.charAt(python.length() - 1) == ';') {
            python = python.replace(python.substring(python.length()-1), "");
        }
        return python + "\n";
    }

    @Override
    public boolean validateTree() {
        if (this.type != null) {
            if (this.type.equals(cond.type)) {
                return cond.validateTree();
            } else {
                return false;
            }
        }
        return cond.validateTree();
    }

}
