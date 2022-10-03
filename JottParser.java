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
        parseHelper(tokens, root);
      	return root;
    }

    public static void parseHelper(ArrayList<Token> tokens, JottNode node) {
        if (Objects.equals(node.type, "Program")) {
            FunctionListNode functionListNode = new FunctionListNode();
            node.addChild(functionListNode);
            for (int i = 0; i < node.getChildren().size(); i++) {
                parseHelper(tokens, node.getChildren().get(i));
            }
        }
        else if (Objects.equals(node.type, "FunctionList")) {
            // THIS LOGIC NEEDS FLESHED OUT, Figure out how to add the tokens
            FunctionDefNode functionDefNode = new FunctionDefNode();
            node.addChild(functionDefNode);

            FunctionListNode functionListNode = new FunctionListNode();
            node.addChild(functionListNode);

            for (int i = 0; i < node.getChildren().size(); i++) {
                parseHelper(tokens, node.getChildren().get(i));
            }
        }
        else if (Objects.equals(node.type, "FunctionDef")) {
            // Continue fleshing out the token logic
            IdNode idNode = new IdNode();
            node.addChild(idNode);
            ((FunctionDefNode)node).setIdNode(idNode);
            parseHelper(tokens, node.getChildren().get(0));
            tokens.remove(0);
            tokens.remove(0);

            FunctionDefParamsNode functionDefParamsNode = new FunctionDefParamsNode();
            node.addChild(functionDefParamsNode);
            ((FunctionDefNode)node).setFuncDefParamsNode(functionDefParamsNode);
            parseHelper(tokens, node.getChildren().get(0));
            while (!tokens.get(0).getTokenType().equals(TokenType.R_BRACKET)) {
                tokens.remove(0);
            }
            tokens.remove(0);
            tokens.remove(0);

            FunctionReturnNode functionReturnNode = new FunctionReturnNode();
            node.addChild(functionReturnNode);
            ((FunctionDefNode)node).setFuncReturnNode(functionReturnNode);
            parseHelper(tokens, node.getChildren().get(0));
            tokens.remove(0);
            tokens.remove(0);

            BodyNode bodyNode = new BodyNode();
            node.addChild(bodyNode);
            ((FunctionDefNode)node).setBodyNode(bodyNode);

            for (int i = 0; i < node.getChildren().size(); i++) {
                parseHelper(tokens, node.getChildren().get(i));
            }
        }
        else if (Objects.equals(node.type, "Id")) {
            Token token = tokens.get(0);
            if (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
                if (!tokens.get(1).getTokenType().equals(TokenType.L_BRACKET)) {
                    reportError("Left bracket expected", token.getFilename(), token.getLineNum());
                    return;
                }
                ((IdNode) node).setId(token);
                tokens.remove(0);
            }
            else {
                reportError("Id expected", token.getFilename(), token.getLineNum());
            }
        }
        else if (Objects.equals(node.type, "FunctionDefParam")) {
            Token token = tokens.get(0);
            while (token.getTokenType().equals(TokenType.ID_KEYWORD)) {
                IdNode idNode = new IdNode();
                node.addChild(idNode);
                // Not sure how to set up func def params and func def params t
            }
            IdNode idNode = new IdNode();
        }
        else if (Objects.equals(node.type, "FunctionDefParamT")) {

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
            for (int i = 0; i < node.getChildren().size(); i++) {
                parseHelper(tokens, node.getChildren().get(i));
            }
        }
        else if (Objects.equals(node.type, "Body")){
            // Insert token logic
            BodyNode bodyNode = new BodyNode();
            BodyStmtNode bodyStmtNode = new BodyStmtNode();
            node.addChild(bodyStmtNode);
            node.addChild(bodyNode);
            for (int i = 0; i < node.getChildren().size(); i++) {
                parseHelper(tokens, node.getChildren().get(i));
            }
        }
    }
}
