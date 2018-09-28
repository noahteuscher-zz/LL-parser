import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {

	
	
	/**
	 * allows the token to input Strings, which will be tokenized and subsequently parsed
	 * 
	 * CTRL+D will quit the program
	 * 
	 * spaces between tokens are optional 
	 * 
	 * @param args
	 * @throws InvalidToken attempted to tokenize String that does not fit grammar
	 * @throws IOException 
	 * @throws ParseError attempted to parse tokens that do not fit grammar
	 */
	public static void main(String[] args) throws InvalidToken, IOException, ParseError{
		Scanner scanny = new Scanner(System.in);
		Tokenizer tokenizer = new Tokenizer();
		LLParser<?> parser = new LLParser();
		
		loop: while(true){
			try{
				String input = scanny.nextLine();
				try{
					tokenizer.load(input);
					List<Token<?>> tokens = tokenizer.allTokens();
					if(tokens.size() == 0){
						System.out.println();
					}
					else{
						System.out.println();
						System.out.println("TOKENIZING...");
						for(int i = 0; i < tokens.size(); i++){
							System.out.print(tokens.get(i) + "     ");
						}
						System.out.println();
						System.out.println();
						System.out.println("PARSING...");
						System.out.println(parser.load(tokens));
						System.out.println();
					}
				}
				catch(InvalidToken e){
					System.out.println(e.getMessage());
					System.out.println();
				}
				catch(ParseError e){
					System.out.println(e.getMessage());
					System.out.println();
				}
			}
			catch(NoSuchElementException e){
				scanny.close();
				break loop;
			}
			
		}
	}
}
