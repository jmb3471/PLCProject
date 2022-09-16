import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Jonathan Baxley, Jake Hunter, Jose Estevez, Sam Siock
 **/

import java.util.ArrayList;

public class JottTokenizer {

	/**
	 * Prints out an error message
	 * @param errType the type of error
	 * @param message the error message
	 * @param filename the filename where the error occurred
	 * @param lineNumber the line number where the error occured
	 */
	private static void reportError(String errType, String message, String filename, int lineNumber) {
		System.err.println(errType + "Error:" + "\n" + message + "\n" + filename + ":" + lineNumber);
	}

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename)throws Exception
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
			int i = 1;	// line number
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				int j = 0;
				while (j < line.length()) {
					char ch = line.charAt(j);
					String token = "";
					if (ch == ' ') {
						// do nothing
					}
					if (ch == '#') {
						break;
					}
					if (ch == ',') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.COMMA));
					}
					if (ch == '[') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.L_BRACKET));
					}
					if (ch == ']') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.R_BRACKET));
					}
					if (ch == '{') {
						token = Character.toString(ch);

						tokenList.add(new Token(token, filename, i, TokenType.L_BRACE));
					}
					if (ch == '}') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.R_BRACE));
					}
					if (ch == '=') {
						token = Character.toString(ch);
						if(j+1 < line.length())
						{
							ch = line.charAt(j+1);
							if (ch == '=') {
								token += "=";
								j++;
								tokenList.add(new Token(token, filename, i, TokenType.REL_OP));
							}
							else {
								tokenList.add(new Token(token, filename, i, TokenType.ASSIGN));
							}
						}
						else {
							tokenList.add(new Token(token, filename, i, TokenType.ASSIGN));
						}
					}
					if (ch == '<' || ch == '>') {
						token = Character.toString(ch);
						ch = line.charAt(j+1);
						if (ch == '=') {
							token += "=";
							j++;
							tokenList.add(new Token(token, filename, i, TokenType.REL_OP));
						}
						else {
							tokenList.add(new Token(token, filename, i, TokenType.REL_OP));
						}
					}
					if (ch == '/' || ch == '+' || ch == '-' || ch == '*') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.MATH_OP));
					}
					if (ch == ';') {
						token = ";";
						tokenList.add(new Token(token, filename, i, TokenType.SEMICOLON));
					}
					if (ch == '.') {
						token = Character.toString(ch);
						if(j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (Character.isDigit(ch)) {
								token += Character.toString(ch);
								j++;
								while (j+1 < line.length()) {
									ch = line.charAt(j+1);
									if (Character.isDigit(ch)) {
										token += Character.toString(ch);
										j++;
									}
									else {
										tokenList.add(new Token(token, filename, i, TokenType.NUMBER));
										break;
									}
								}
							}
							else {
								reportError("Syntax", "Missing a digit after the '.'", filename, i);
								return null;
							}
						}
						else {
							reportError("Syntax", "Missing a digit after the '.'", filename, i);
							return null;
						}
					}
					if (Character.isDigit(ch)) {
						token = Character.toString(ch);
						while (j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (Character.isDigit(ch)) {
								token += Character.toString(ch);
								j++;
							}
							else if (ch == '.') {
								token += ".";
								j++;
								while (j+1 < line.length()) {
										
									ch = line.charAt(j+1);
									if (Character.isDigit(ch)) {
										token += Character.toString(ch);
										j++;
									}
									else {
										break;
									}
								}
								tokenList.add(new Token(token, filename, i, TokenType.NUMBER));
								break;
							}
							else {
								tokenList.add(new Token(token, filename, i, TokenType.NUMBER));
								break;
							}
						}
					}
					if (Character.isLetter(ch)) {
						token = Character.toString(ch);
						while (j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (Character.isLetterOrDigit(ch)) {
								token += Character.toString(ch);
								j++;
							}
							else {
								break;
							}
						}
						tokenList.add(new Token(token, filename, i, TokenType.ID_KEYWORD));
					}
					if (ch == ':') {
						token = Character.toString(ch);
						tokenList.add(new Token(token, filename, i, TokenType.COLON));
					}
					if (ch == '!') {
						token = Character.toString(ch);
						if (j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (ch == '=') {
								token += Character.toString(ch);
								j++;
								tokenList.add(new Token(token, filename, i, TokenType.REL_OP));
							}
							else {
								reportError("Syntax", "Missing '=' after the '!'", filename, i);
								return null;
							}
						}
						else {
							reportError("Syntax", "Missing '=' after the '!'", filename, i);
							return null;
						}
						
					}
					if (ch == '"') {
						token = Character.toString(ch);
						boolean tok_added = false;
						while (j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (Character.isLetterOrDigit(ch) || ch == ' ') {
								token += Character.toString(ch);
								j++;
							}
							else if (ch == '"') {
								token += Character.toString(ch);
								j++;
								tokenList.add(new Token(token, filename, i, TokenType.STRING));
								tok_added = true;
								break;
							}
							else {
								reportError("Synatx", "Invalid string contents", filename, i);
								return null;
							}
						}
						if (!tok_added) {
							reportError("Synatx", "Missing closing quotation", filename, i);
							return null;
						}
					}
					j++;
				}
				i++;
			}
		}
		return tokenList;
	}
}
