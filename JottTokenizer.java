import java.io.File;
import java.util.Scanner;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Jonathan Baxley
 **/

import java.util.ArrayList;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		ArrayList<Token> tokenList = new ArrayList<Token>();
		int i = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			int j = 0;
			while (j < line.length()) {
				String token = "";
				if (line[j] == " ") {
					j++;
				}
				else if (line[j] == "#") {
					break;
				}
				else if (line[j] == ",") {
					token = ",";
					Token token1 = new Token(token, filename, i, TokenType.COMMA);
					tokenList.add(token1);
				}
				else if (line[j] == "[") {
					token = "[";
					Token token1 = new Token(token, filename, i, TokenType.L_BRACKET);
					tokenList.add(token1);
				}
				else if (line[j] == "]") {
					token = "]";
					Token token1 = new Token(token, filename, i, TokenType.R_BRACKET);
					tokenList.add(token1);
				}
				else if (line[j] == "{") {
					token = "{";
					Token token1 = new Token(token, filename, i, TokenType.L_BRACE);
					tokenList.add(token1);
				}
				else if (line[j] == "}") {
					token = "}";
					Token token1 = new Token(token, filename, i, TokenType.R_BRACE);
					tokenList.add(token1);
				}
				j++;
			}
			i++;
		}
		return null;
	}
}
