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
     * Parses an ArrayList of Jott tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */

    public static JottTree parse(ArrayList<Token> tokens) {
        try {
            JottTree root = ProgramNode.ParseProgram(tokens);
            return root;
        }
        catch (Exception e) {
            return null;
        }
    }
}
