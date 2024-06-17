package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Objects;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		int token = tokenizer.nextToken();
		Double a;
		Double b;
		while (!Objects.equals(tokenizer.sval, "quit") && token != StreamTokenizer.TT_EOF){
			switch (token){
				case StreamTokenizer.TT_NUMBER:
					myStack.push(tokenizer.nval);
					break;
				case '+':
					a = myStack.pop();
					b = myStack.pop();
					if(a==null || b==null){
						System.err.println(tokenizer);
						System.err.println(myStack);
						System.exit(1);

					}
					myStack.push(a+b);
					break;
				case '*':
					a = myStack.pop();
					b = myStack.pop();
					if(a==null || b==null){
						System.err.println(tokenizer);
						System.err.println(myStack);
						System.exit(1);

					}
					myStack.push(a*b);
					break;
				case '/':
					a = myStack.pop();
					b = myStack.pop();
					if(a==null || b==null){
						System.err.println(tokenizer);
						System.err.println(myStack);
						System.exit(1);

					}
					myStack.push(b/a);
					break;
				case '-':
					a = myStack.pop();
					b = myStack.pop();
					if(a==null || b==null){
						System.err.println(tokenizer);
						System.err.println(myStack);
						System.exit(1);

					}
					myStack.push(b-a);
					break;
			}
			token = tokenizer.nextToken();
		}
		if(myStack.isEmpty()){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		double temp = myStack.pop();
		if(!myStack.isEmpty()){
			System.err.println(tokenizer);
			System.err.println(myStack);
			System.exit(1);
		}
		else{
			System.out.println(temp);
		}

	}

}
