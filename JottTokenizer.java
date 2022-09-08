import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Jonathan Baxley, Jake Hunter
 **/

import java.util.ArrayList;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename)
	{
		ArrayList<Token> tokenList = new ArrayList<Token>();
		File file = new File(filename);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(sc != null)
		{
			int i = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				int j = 0;
				while (j < line.length()) {
					char ch = line.charAt(j);
					String token = "";
					if (ch == ' ') {
						j++;
					}
					else if (ch == '#') {
						break;
					}
					else if (ch == ',') {
						token = ",";
						Token token1 = new Token(token, filename, i, TokenType.COMMA);
						tokenList.add(token1);
					}
					else if (ch == '[') {
						token = "[";
						Token token1 = new Token(token, filename, i, TokenType.L_BRACKET);
						tokenList.add(token1);
					}
					else if (ch == ']') {
						token = "]";
						Token token1 = new Token(token, filename, i, TokenType.R_BRACKET);
						tokenList.add(token1);
					}
					else if (ch == '{') {
						token = "{";
						Token token1 = new Token(token, filename, i, TokenType.L_BRACE);
						tokenList.add(token1);
					}
					else if (ch == '}') {
						token = "}";
						Token token1 = new Token(token, filename, i, TokenType.R_BRACE);
						tokenList.add(token1);
					}
					j++;
				}
				i++;
			}
		}
		return null;
	}
}
