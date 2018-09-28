import java.util.ArrayList;
import java.util.List;

public class LLParser<T> {

	/**
	 * loads a list of tokens into the parser, returns a parsed token of the list
	 * 
	 * @param tokens a list of tokenized tokens
	 * @return a single parsed token
	 * @throws ParseError 
	 */
	public Token<?> load(List<Token<?>> tokens) throws ParseError{

		try{
			return parse(tokens).get(0);
		}
		catch(IndexOutOfBoundsException e){
			throw new ParseError("ERROR: invalid input format");
		}
	}
	private List<Token<?>> parse(List<Token<?>> tokens) throws ParseError{
		
		return evaluate(tokens);
	}
	
	private Token<?> addTokens(Token<?> one, Token<?> two) throws ParseError{
		
		
		if((one.type != Token.TOKEN_TYPE.STRING && one.type != Token.TOKEN_TYPE.DOUBLE && one.type != Token.TOKEN_TYPE.INTEGER && one.type != Token.TOKEN_TYPE.LPAREN && one.type != Token.TOKEN_TYPE.RPAREN) || (two.type != Token.TOKEN_TYPE.STRING && two.type != Token.TOKEN_TYPE.DOUBLE && two.type != Token.TOKEN_TYPE.INTEGER  && two.type != Token.TOKEN_TYPE.LPAREN  && two.type != Token.TOKEN_TYPE.RPAREN)){
			throw new ParseError("ERROR: invalid token type");
		}
		else if(one.type == Token.TOKEN_TYPE.STRING || two.type == Token.TOKEN_TYPE.STRING){
			Token<String> t = new Token<String>();
			t.type = Token.TOKEN_TYPE.STRING;
			t.data = one.data.toString() + two.data.toString();
			return t;
		}
		else if(one.type == Token.TOKEN_TYPE.DOUBLE || two.type == Token.TOKEN_TYPE.DOUBLE){
			Token<Double> t = new Token<Double>();
			t.type = Token.TOKEN_TYPE.DOUBLE;
			t.data = Double.parseDouble(one.data.toString()) + Double.parseDouble(two.data.toString());
			return t;
		}
		else if(one.type == Token.TOKEN_TYPE.INTEGER || two.type == Token.TOKEN_TYPE.INTEGER){
			Token<Integer> t = new Token<Integer>();
			t.type = Token.TOKEN_TYPE.INTEGER;
			t.data = Integer.parseInt(one.data.toString()) + Integer.parseInt(two.data.toString());
			return t;
		}
		else{
			throw new ParseError("Invalid token type");
		}
	}
	
	private Token<?> subTokens(Token<?> one, Token<?> two) throws ParseError{
		
		if((one.type != Token.TOKEN_TYPE.STRING && one.type != Token.TOKEN_TYPE.DOUBLE && one.type != Token.TOKEN_TYPE.INTEGER && one.type != Token.TOKEN_TYPE.LPAREN && one.type != Token.TOKEN_TYPE.RPAREN) || (two.type != Token.TOKEN_TYPE.STRING && two.type != Token.TOKEN_TYPE.DOUBLE && two.type != Token.TOKEN_TYPE.INTEGER  && two.type != Token.TOKEN_TYPE.LPAREN  && two.type != Token.TOKEN_TYPE.RPAREN)){
			throw new ParseError("ERROR: invalid token type");
		}
		else if(one.type == Token.TOKEN_TYPE.STRING || two.type == Token.TOKEN_TYPE.STRING){
			throw new ParseError("ERROR: cannot subtract Strings");
		}
		else if(one.type == Token.TOKEN_TYPE.DOUBLE || two.type == Token.TOKEN_TYPE.DOUBLE){
			Token<Double> t = new Token<Double>();
			t.type = Token.TOKEN_TYPE.DOUBLE;
			t.data = Double.parseDouble(one.data.toString()) - Double.parseDouble(two.data.toString());
			return t;
		}
		else if(one.type == Token.TOKEN_TYPE.INTEGER || two.type == Token.TOKEN_TYPE.INTEGER){
			Token<Integer> t = new Token<Integer>();
			t.type = Token.TOKEN_TYPE.INTEGER;
			t.data = Integer.parseInt(one.data.toString()) - Integer.parseInt(two.data.toString());
			return t;
		}
		else{
			throw new ParseError("Invalid token type");
		}
	}
	
	private Token<?> multTokens(Token<?> one, Token<?> two) throws ParseError{
		
		
		if((one.type != Token.TOKEN_TYPE.STRING && one.type != Token.TOKEN_TYPE.DOUBLE && one.type != Token.TOKEN_TYPE.INTEGER && one.type != Token.TOKEN_TYPE.LPAREN && one.type != Token.TOKEN_TYPE.RPAREN) || (two.type != Token.TOKEN_TYPE.STRING && two.type != Token.TOKEN_TYPE.DOUBLE && two.type != Token.TOKEN_TYPE.INTEGER  && two.type != Token.TOKEN_TYPE.LPAREN  && two.type != Token.TOKEN_TYPE.RPAREN)){
			throw new ParseError("ERROR: invalid token type");
		}
		else if(one.type == Token.TOKEN_TYPE.STRING || two.type == Token.TOKEN_TYPE.STRING){
			Token<String> t = new Token<String>();
			t.type = Token.TOKEN_TYPE.STRING;
			t.data = "";
			if(one.type == Token.TOKEN_TYPE.STRING){		
				if(two.type == Token.TOKEN_TYPE.STRING){
					throw new ParseError("ERROR: cannot multiply String by a String");
				}
				else{
					for(int i = 0; i < Integer.parseInt(two.data.toString()); i++){
						t.data = t.data.toString() + one.data.toString();
					}
					return t;
				}	
			}
			else{
				for(int i = 0; i < Integer.parseInt(one.data.toString()); i++){
					t.data = t.data.toString() + two.data.toString();
				}
				return t;
			}
		}
		else if(one.type == Token.TOKEN_TYPE.DOUBLE || two.type == Token.TOKEN_TYPE.DOUBLE){
			Token<Double> t = new Token<Double>();
			t.type = Token.TOKEN_TYPE.DOUBLE;
			t.data = Double.parseDouble(one.data.toString()) * Double.parseDouble(two.data.toString());
			return t;
		}
		else if(one.type == Token.TOKEN_TYPE.INTEGER || two.type == Token.TOKEN_TYPE.INTEGER){
			Token<Integer> t = new Token<Integer>();
			t.type = Token.TOKEN_TYPE.INTEGER;
			t.data = Integer.parseInt(one.data.toString()) * Integer.parseInt(two.data.toString());
			return t;
		}
		else{
			throw new ParseError("Invalid token type");
		}
	}
	
	private Token<?> divTokens(Token<?> one, Token<?> two) throws ParseError{
		
		if((one.type != Token.TOKEN_TYPE.STRING && one.type != Token.TOKEN_TYPE.DOUBLE && one.type != Token.TOKEN_TYPE.INTEGER && one.type != Token.TOKEN_TYPE.LPAREN && one.type != Token.TOKEN_TYPE.RPAREN) || (two.type != Token.TOKEN_TYPE.STRING && two.type != Token.TOKEN_TYPE.DOUBLE && two.type != Token.TOKEN_TYPE.INTEGER  && two.type != Token.TOKEN_TYPE.LPAREN  && two.type != Token.TOKEN_TYPE.RPAREN)){
			throw new ParseError("ERROR: invalid token type");
		}
		
		if(Double.parseDouble(two.data.toString()) == 0.0){
			throw new ParseError("ERROR: cannot divide by zero");
		}
		
		else if(one.type == Token.TOKEN_TYPE.STRING || two.type == Token.TOKEN_TYPE.STRING){
			throw new ParseError("ERROR: cannot divide Strings");
		}
		else if(one.type == Token.TOKEN_TYPE.DOUBLE || two.type == Token.TOKEN_TYPE.DOUBLE){
			Token<Double> t = new Token<Double>();	
			t.type = Token.TOKEN_TYPE.DOUBLE;
			t.data = Double.parseDouble(one.data.toString()) / Double.parseDouble(two.data.toString());
			return t;
		}
		else if(one.type == Token.TOKEN_TYPE.INTEGER || two.type == Token.TOKEN_TYPE.INTEGER){
			Token<Integer> t = new Token<Integer>();
			t.type = Token.TOKEN_TYPE.INTEGER;
			t.data = Integer.parseInt(one.data.toString()) / Integer.parseInt(two.data.toString());
			return t;
		}
		else{
			throw new ParseError("Invalid token type");
		}
	}
	
	private List<Token<?>> parentheses(List<Token<?>> tokens) throws ParseError{
		Token<?> t;
		for(int i = 0; i < tokens.size(); i++){
			 t = tokens.get(i);
			 if(isParen(t)){
				 	if(t.type == Token.TOKEN_TYPE.LPAREN){
						 for(int j = i + 1; j < tokens.size(); j++){
							 t = tokens.get(j);
							 if(t.type == Token.TOKEN_TYPE.LPAREN){

								 List<Token<?>> newtokens = new ArrayList<Token<?>>();
								 for(int q = 0; q <= i; q++){
									 newtokens.add(tokens.get(q));
								 } 
								 parentheses(tokens.subList(j, tokens.size()));
							 }
							 
							 else if(t.type == Token.TOKEN_TYPE.RPAREN){
								Token<?> toadd = evaluate(tokens.subList(i + 1, j)).get(0);
								for(int k = 0; k < j - i + 1; k++){
									tokens.remove(i);
								}
								tokens.add(i, toadd);
								return tokens;
							 }
						 }
						 throw new ParseError("ERROR: did not close parentheses");
				 	}
					else if(t.type == Token.TOKEN_TYPE.RPAREN){
						throw new ParseError("ERROR: did not open parentheses");
					}
			 } 
		}
		return tokens;
	}
	
	
	private List<Token<?>> evaluate(List<Token<?>> tokens) throws ParseError{
		Token<?> t = null;
		List<Token<?>> returnlist = new ArrayList<Token<?>>();
		
		
		if(tokens.size() == 1){
			if(isTerminal(tokens.get(0))){
				return tokens;
			}
			else{
				throw new ParseError("ERROR: unparsable token");
			}
		}
		
		while(hasParentheses(tokens)){
			tokens = parentheses(tokens);
		}
		
		if(tokens.size() == 1){
			return tokens;
		}
		
			
			for(int i = tokens.size() - 1; i >= 0; i--){
				 t = tokens.get(i);
				 if(isOperator(t)){
					 	if(t.type == Token.TOKEN_TYPE.SUBOP){
					 		returnlist.add(subTokens(evaluate(tokens.subList(0, i)).get(0), evaluate(tokens.subList(i + 1, tokens.size())).get(0)));
					 	}
					 	if(t.type == Token.TOKEN_TYPE.ADDOP){
					 		returnlist.add(addTokens(evaluate(tokens.subList(0, i)).get(0), evaluate(tokens.subList(i + 1, tokens.size())).get(0)));
						}
				 } 
			}
			
			
			for(int i = tokens.size() - 1; i >= 0; i--){
				 t = tokens.get(i);
				 if(isOperator(t)){
					 	if(t.type == Token.TOKEN_TYPE.MULTOP){
					 		returnlist.add(multTokens(evaluate(tokens.subList(0, i)).get(0), evaluate(tokens.subList(i + 1, tokens.size())).get(0)));
					 	}
					 	if(t.type == Token.TOKEN_TYPE.DIVOP){
					 		returnlist.add(divTokens(evaluate(tokens.subList(0, i)).get(0), evaluate(tokens.subList(i + 1, tokens.size())).get(0)));
						}
				 } 
			}
			
		return returnlist;
	}
	
	
	private boolean hasParentheses(List<Token<?>> tokens) {
		for(int i = 0; i < tokens.size(); i++){
			if(tokens.get(i).type == Token.TOKEN_TYPE.LPAREN || tokens.get(i).type == Token.TOKEN_TYPE.RPAREN){
				return true;
			}
		}
		return false;
	}
	private boolean isTerminal(Token<?> t){
		return (t.type == Token.TOKEN_TYPE.STRING || t.type == Token.TOKEN_TYPE.INTEGER || t.type == Token.TOKEN_TYPE.DOUBLE);
	}
	private boolean isOperator(Token<?> t){
		return (t.type == Token.TOKEN_TYPE.ADDOP || t.type == Token.TOKEN_TYPE.SUBOP || t.type == Token.TOKEN_TYPE.MULTOP || t.type == Token.TOKEN_TYPE.DIVOP);
	}
	private boolean isParen(Token<?> t){
		return (t.type == Token.TOKEN_TYPE.LPAREN || t.type == Token.TOKEN_TYPE.RPAREN);
	}

}
