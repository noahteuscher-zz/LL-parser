import java.util.ArrayList;
import java.util.List;

public class Tokenizer{

	private List<Token<?>> tokens = new ArrayList<Token<?>>();

	/**
	 * loads a String into the Tokenizer and creates a List of tokens out of it
	 * 
	 * @param str takes a String and tokenizes it
	 * @throws InvalidToken invalid inputs that do not fit token grammar
	 */
	public void load(String str) throws InvalidToken{

		
		char firstchar; 

		int length = str.length(); 
		
		for(int i = 0; i < length; i++){

			if(str.length() > 0){
				firstchar = str.charAt(0);
			}
			else{
				break;
			}

			if(isQuote(firstchar)){
				str = str.substring(create_STRING_token(str.substring(1)));
			}

			else if(isDigit(firstchar)){
				str = str.substring(create_NUMBER_token(str));
			}
			
			else if(isLetter(firstchar)){
				str = str.substring(create_ID_token(str));
			}

			else if(isBracket(firstchar)){
				str = str.substring(create_BRACKET_token(str));
			}

			else if(isParentheses(firstchar)){
				str = str.substring(create_PAREN_token(str));
			}

			else if(isOperator(firstchar)){
				str = str.substring(create_OP_token(str));
			}
			else if(firstchar == ' '){
				str = str.substring(1);
			}
			else{
				tokens.clear();
				throw new InvalidToken("GENERAL ERROR [not in grammar]");
			}
		}
	}

	public boolean isDigit(char input){
		if(input - '0' < 10 && input - '0' >= 0){
			return true;
		}
		return false;
	}

	public boolean isOperator(char input){
		if((input >= '*' && input <= '/' && input != '.' && input != '\'') || input == '%'){
			return true;
		}
		return false;
	}

	public boolean isParentheses(char input){
		if(input >= '(' && input <= ')'){
			return true;
		}
		return false;
	}

	public boolean isBracket(char input){
		if(input >= '[' && input <= ']'){
			return true;
		}
		return false;
	}

	public boolean isLetter(char input){
		if((input >= 'A' && input <= 'Z') || (input >= 'a' && input <= 'z')){
			return true;
		}
		return false;
	}

	public boolean isQuote(char input){
		if(input == '"'){
			return true;
		}
		return false;
	}

	private int create_OP_token(String str){
		char data = str.charAt(0);

		if(data == '+'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.ADDOP;
			tokens.add(token);
		}

		else if(data == '-'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.SUBOP;
			tokens.add(token);
		}

		else if(data == '*'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.MULTOP;
			tokens.add(token);
		}

		else if(data == '/'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.DIVOP;
			tokens.add(token);
		}

		else if(data == '%'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.MODOP;
			tokens.add(token);
		}
		else if(data == ','){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.COP;
			tokens.add(token);
		}

		return 1;
	}

	private int create_PAREN_token(String str){
		char data = str.charAt(0);

		if(data == '('){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.LPAREN;
			token.data = data + "";
			tokens.add(token);
		}
		else if(data == ')'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.RPAREN;
			token.data = data + "";
			tokens.add(token);
		}

		return 1;
	}

	private int create_BRACKET_token(String str){

		char data = str.charAt(0);

		if(data == '['){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.LBRACKET;
			token.data = data + "";
			tokens.add(token);
		}
		else if(data == ']'){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.RBRACKET;
			token.data = data + "";
			tokens.add(token);
		}

		return 1;
	}

	private int create_NUMBER_token(String str) throws InvalidToken{
		boolean isDouble = false;
		String substring = "";
		String data = "";
		int index = 0;
		forloop: for(int i = 0; i < str.length(); i++){
			substring = str.substring(i,  i + 1);
			if(!isDigit(substring.charAt(0))){
				if(!isDigit(substring.charAt(0))){
					if(substring.equals(".") && !isDouble){
						isDouble = true;
					}
					else if(substring.equals(" ") || substring.equals("+") || substring.equals("-") || substring.equals("/") || substring.equals("%") || substring.equals("*") || substring.equals("(") || substring.equals(")")){
						break forloop;
					}
					else{
						tokens.clear();
						throw new InvalidToken("INVALID NUMBER ERROR [non-digit character]");
					}
				}
			}
			data = data + substring;
			index++;
		}


		if(!isDouble){
			Token<Integer> token = new Token<Integer>();
			token.type = Token.TOKEN_TYPE.INTEGER;
			token.data = Integer.parseInt(data);
			tokens.add(token);
		}
		else{
			Token<Double> token = new Token<Double>();
			token.type = Token.TOKEN_TYPE.DOUBLE;
			token.data = Double.parseDouble(data);
			tokens.add(token);
		}

		return index;
	}


	private int create_STRING_token(String str) throws InvalidToken{

		boolean success = false;
		String substring = "";
		String data = ""; 
		int index = 0;
		for(int i = 0; i < str.length(); i++){
			substring = str.substring(i,  i + 1);
			if(substring.equals("\"")){
				success = true;
				break;
			}
			data += substring;
			index++;
		}
		if(success){
			Token<String> token = new Token<String>();
			token.type = Token.TOKEN_TYPE.STRING;
			token.data = data;
			tokens.add(token);
			return index + 2;
		}
		else{
			tokens.clear();
			throw new InvalidToken("INVALID STRING ERROR [unclosed string]");
		}
	}

	private int create_ID_token(String str) throws InvalidToken{
		String substring = "";
		String data = ""; 
		int index = 0;
		forloop: for(int i = 0; i < str.length(); i++){
			substring = str.substring(i,  i + 1);
			if(substring.equals(" ")){
				break forloop;
			}
			else if(!isDigit(substring.charAt(0)) && !isLetter(substring.charAt(0))){
				tokens.clear();
				throw new InvalidToken("INVALID IDENTIFIER ERROR [invalid character]");
			}
			data += substring;
			index++;
		}
		Token<String> token = new Token<String>();
		token.type = Token.TOKEN_TYPE.ID;
		token.data = data;
		tokens.add(token);
		return index;
	}


	/**
	 * 
	 * @return a list of all Tokens created since last call of this method; clears the list when called
	 * @throws InvalidToken
	 */
	public List<Token<?>> allTokens() throws InvalidToken{
		List<Token<?>> temptokens = new ArrayList<Token<?>>();
		temptokens.addAll( tokens );
		tokens.clear();
		return temptokens;
	}
	
	/**
	 * returns the first token in the List
	 * @return
	 */
	public Token<?> nextToken(){
		
		Token<?> t = tokens.remove(0);
		return t;
}


}
