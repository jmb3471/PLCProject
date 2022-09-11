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
						Token token1 = new Token(token, filename, i, TokenType.COMMA);
						tokenList.add(token1);
					}
					if (ch == '[') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.L_BRACKET);
						tokenList.add(token1);
					}
					if (ch == ']') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.R_BRACKET);
						tokenList.add(token1);
					}
					if (ch == '{') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.L_BRACE);
						tokenList.add(token1);
					}
					if (ch == '}') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.R_BRACE);
						tokenList.add(token1);
					}
					if (ch == '=') {
						token = Character.toString(ch);
						if(j+1 < line.length())
						{
							ch = line.charAt(j+1);
							if (ch == '=') {
								token += "=";
								j++;
								Token token1 = new Token(token, filename, i, TokenType.REL_OP);
								tokenList.add(token1);
							}
							else {
								Token token1 = new Token(token, filename, i, TokenType.ASSIGN);
								tokenList.add(token1);
							}
						}
						else {
							Token token1 = new Token(token, filename, i, TokenType.ASSIGN);
							tokenList.add(token1);
						}
					}
					if (ch == '<' || ch == '>') {
						token = Character.toString(ch);
						ch = line.charAt(j+1);
						if (ch == '=') {
							token += "=";
							j++;
							Token token1 = new Token(token, filename, i, TokenType.REL_OP);
							tokenList.add(token1);
						}
						else {
							Token token1 = new Token(token, filename, i, TokenType.REL_OP);
							tokenList.add(token1);
						}
					}
					if (ch == '/' || ch == '+' || ch == '-' || ch == '*') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.MATH_OP);
						tokenList.add(token1);
					}
					if (ch == ';') {
						token = ";";
						Token token1 = new Token(token, filename, i, TokenType.SEMICOLON);
						tokenList.add(token1);
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
										Token token1 = new Token(token, filename, i, TokenType.NUMBER);
										tokenList.add(token1);
										break;
									}
								}
							}
							else {
								throw new Exception("Missing a digit after the '.'");
							}
						}
						else {
							throw new Exception("Missing a digit after the '.'");
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
										Token token1 = new Token(token, filename, i, TokenType.NUMBER);
										tokenList.add(token1);
										break;
									}
								}
								break;
							}
							else {
								Token token1 = new Token(token, filename, i, TokenType.NUMBER);
								tokenList.add(token1);
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
								Token token1 = new Token(token, filename, i, TokenType.ID_KEYWORD);
								tokenList.add(token1);
								break;
							}
						}
					}
					if (ch == ':') {
						token = Character.toString(ch);
						Token token1 = new Token(token, filename, i, TokenType.COLON);
						tokenList.add(token1);
					}
					if (ch == '!') {
						token = Character.toString(ch);
						ch = line.charAt(j+1);
						if (ch == '=') {
							token += Character.toString(ch);
							j++;
							Token token1 = new Token(token, filename, i, TokenType.REL_OP);
							tokenList.add(token1);
						}
						else {
							//throw error
						}
					}
					if (ch == '"') {
						token = Character.toString(ch);
						while (j+1 < line.length()) {
							ch = line.charAt(j+1);
							if (Character.isLetterOrDigit(ch) || ch == ' ') {
								token += Character.toString(ch);
								j++;
							}
							else if (ch == '"') {
								token += Character.toString(ch);
								j++;
								Token token1 = new Token(token, filename, i, TokenType.STRING);
								tokenList.add(token1);
								break;
							}
							else {
								// throw error
							}
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
