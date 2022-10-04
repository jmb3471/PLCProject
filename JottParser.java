/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;
import java.util.Objects;

public class JottParser {

    private static void reportError(String message, String filename, int lineNumber) {
        System.err.println("Syntax Error:" + "\n" + message + "\n" + filename + ":" + lineNumber);
    }

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */

    public static JottTree parse(ArrayList<Token> tokens) {
		
		ProgramNode root = new ProgramNode();
        Boolean success = parseHelper(tokens, root);
        if (!success) {
            return null;
        }
      	return root;
    }

    public static Boolean parseHelper(ArrayList<Token> tokens, JottNode node) {
        if (Objects.equals(node.type, "Program")) {
            FunctionListNode functionListNode = new FunctionListNode();
            node.addChild(functionListNode);
            return parseHelper(tokens, node.getChildren().get(0));
        }
        else if (Objects.equals(node.type, "FunctionList")) {
            // THIS LOGIC NEEDS FLESHED OUT, Figure out how to add the tokens
            FunctionDefNode functionDefNode = new FunctionDefNode();
            node.addChild(functionDefNode);

            FunctionListNode functionListNode = new FunctionListNode();
            node.addChild(functionListNode);

            Boolean def_suc = parseHelper(tokens, node.getChildren().get(0));
            Boolean list_suc = parseHelper(tokens, node.getChildren().get(1));
            return def_suc && list_suc;
        }
        else if (Objects.equals(node.type, "FunctionDef")) {
            // Continue fleshing out the token logic
            IdNode idNode = new IdNode();
            node.addChild(idNode);
            ((FunctionDefNode)node).setIdNode(idNode);
            Boolean suc = parseHelper(tokens, node.getChildren().get(0));
            if (!suc) {
                return false;
            }
            tokens.remove(0);
            tokens.remove(0);

            FunctionDefParamsNode functionDefParamsNode = new FunctionDefParamsNode();
            node.addChild(functionDefParamsNode);
            ((FunctionDefNode)node).setFuncDefParamsNode(functionDefParamsNode);
            suc = parseHelper(tokens, node.getChildren().get(1));
            if (!suc) {
                return false;
            }
            while (!tokens.get(0).getTokenType().equals(TokenType.R_BRACKET)) {
                tokens.remove(0);
            }
            tokens.remove(0);
            tokens.remove(0);

            FunctionReturnNode functionReturnNode = new FunctionReturnNode();
            node.addChild(functionReturnNode);
            ((FunctionDefNode)node).setFuncReturnNode(functionReturnNode);
            suc = parseHelper(tokens, node.getChildren().get(2));
            if (!suc) {
                return false;
            }
            tokens.remove(0);
            tokens.remove(0);

            BodyNode bodyNode = new BodyNode();
            node.addChild(bodyNode);
            ((FunctionDefNode)node).setBodyNode(bodyNode);
            suc = parseHelper(tokens, node.getChildren().get(3));
            if (!suc) {
                return false;
            }
            return true;
        }
        else if (Objects.equals(node.type, "Id")) {
            Token token = tokens.get(0);
            if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
                if (!tokens.get(1).getTokenType().equals(TokenType.L_BRACKET)) {
                    reportError("Left bracket expected", token.getFilename(), token.getLineNum());
                    return false;
                }
                ((IdNode) node).setId(token);
                tokens.remove(0);
                return true;
            }
            else {
                reportError("Id expected", token.getFilename(), token.getLineNum());
                return false;
            }
        }
        else if (Objects.equals(node.type, "FunctionDefParam")) {
            if (tokens.get(0).getTokenType().equals(TokenType.R_BRACKET)) {
                return true;
            }
            IdNode idNode = new IdNode();
            node.addChild(idNode);
            ((FunctionDefParamsNode)node).setIdNode(idNode);
            Boolean suc = parseHelper(tokens, node.getChildren().get(0));
            if (!suc) {
                return false;
            }
            tokens.remove(0);
            tokens.remove(0);

            VarTypeNode varTypeNode = new VarTypeNode();
            node.addChild(varTypeNode);
            ((FunctionDefParamsNode)node).setVarTypeNode(varTypeNode);
            suc = parseHelper(tokens, node.getChildren().get(1));
            if (!suc) {
                return false;
            }
            tokens.remove(0);

            FunctionDefParamsTNode functionDefParamsTNode = new FunctionDefParamsTNode();
            node.addChild(functionDefParamsTNode);
            ((FunctionDefParamsNode)node).setFunctionDefParamsTNode(functionDefParamsTNode);
            suc = parseHelper(tokens, node.getChildren().get(2));
            if (!suc) {
                return false;
            }
            return true;

        }
        else if (Objects.equals(node.type, "FunctionDefParamT")) {
            //essentially same as FunctionDefParam
        }
        else if (Objects.equals(node.type, "BodyStmt")) {
            Token token = tokens.get(0);
            if (token.getToken().equals("if")) {
                IfStmtNode ifStmtNode = new IfStmtNode();
                node.addChild(ifStmtNode);
            }
            else if (token.getToken().equals("while")) {
                While_Loop_Node while_loop_node = new While_Loop_Node();
                node.addChild(while_loop_node);
            }
            else {
                // Insert Stmt node when it is made
            }
            return runChildren(tokens, node);

        }
        else if (Objects.equals(node.type, "Body")){
            // Insert token logic choose either body or return or empty based on token

            BodyNode bodyNode = new BodyNode();
            BodyStmtNode bodyStmtNode = new BodyStmtNode();
            node.addChild(bodyStmtNode);
            node.addChild(bodyNode);
            return runChildren(tokens, node);
        }
        else if (Objects.equals(node.type, "ReturnStmt")) {
            ExprNode exprNode = new ExprNode();
            node.addChild(exprNode);
            EndStmtNode endStmtNode = new EndStmtNode();
            node.addChild(endStmtNode);
            return runChildren(tokens, node);
        }
        return true;
    }

    public static boolean runChildren(ArrayList<Token> tokens, JottNode node) {
        Boolean passed = true;
        for (int i = 0; i < node.getChildren().size(); i++) {
            passed = parseHelper(tokens, node.getChildren().get(i));
        }
        return passed;
    }
}
