/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;
import java.util.Objects;

public class JottParser {

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

    public static JottNode parseHelper(ArrayList<Token> tokens, JottNode node) {
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

        }
}
