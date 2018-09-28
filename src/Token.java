
public class Token<T> {

	/**
	 *list of possible Token types
	 */
	public enum TOKEN_TYPE{
		ADDOP,
		SUBOP,
		MULTOP,
		DIVOP,
		MODOP,
		COP,
		LPAREN,
		RPAREN,
		LBRACKET,
		RBRACKET,
		INTEGER, 
		DOUBLE, 
		STRING, 
		ID
	}
	public TOKEN_TYPE type;
	public T data;
	
	/**
	 * returns a String representation of the Token
	 */
	public String toString(){
		String rtn = "<" + type;
		if(data != null){
			rtn = rtn + ":" + data;
		}
		rtn = rtn + ">";
		return rtn;
		
	}
}
