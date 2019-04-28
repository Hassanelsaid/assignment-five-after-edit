package eg.edu.alexu.csd.datastructure.stack;
import java.lang.Math;
public class convert implements IExpressionEvaluator{
	String expession ;
	boolean end;
	int i=0;
	stk exp= new stk();
	/**
	 * this method takes input a string in the infix form and converts it into postfix form
	 * it uses two stacks a global one exp where the final postfix expression is stored
	 * and a local one op where operators are stored until they are popped into  the final stack
	 * @param expression
	 * @return postfix expression
	 */
	
	
public String infixToPostfix(String expression) {
	stk op=new stk();
	op.u=op.m;
	System.out.println(expression.length());
	System.out.println();
	System.out.println(expression.charAt(0));
	System.out.println(expression.charAt(1));
	System.out.println(expression.charAt(2));
	System.out.println(expression.charAt(3));
	System.out.println(expression.charAt(4));
	System.out.println();
	
	while(i<expression.length()&&expression.charAt(i)!=')') {
		if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*'||expression.charAt(i)=='/') {
			if(op.isEmpty()) {
				op.push(expression.charAt(i));
				
			}
			else {
				while(((((char)op.peek()=='+'||(char)op.peek()=='-')&&(expression.charAt(i)=='+'||expression.charAt(i)=='-'))||(((char)op.peek()=='/'||(char)op.peek()=='*')&&(expression.charAt(i)=='/'||expression.charAt(i)=='*'))||(((char)op.peek()=='/'||(char)op.peek()=='*')&&(expression.charAt(i)=='-'||expression.charAt(i)=='+')))) {
					exp.push(op.pop());
					if(op.isEmpty()) {
						break;
					}
					
				}
				op.push(expression.charAt(i));
			}
			
			i++;
		}
		else if(expression.charAt(i)=='(') {
			
			i++;
			infixToPostfix(expression);
			i++;
		}
		else {
			int fn=0;
			while(expression.charAt(i)!=' '&&expression.charAt(i)!='	'&&expression.charAt(i)!='+'&&expression.charAt(i)!='/'&&expression.charAt(i)!='*'&&expression.charAt(i)!='-'&&expression.charAt(i)!='('&&expression.charAt(i)!=')') {
				fn=1;
				exp.push(expression.charAt(i));
				i++;
				if(i>=expression.length()) {
					break;
				}
			}
			if(fn==0) {
				i++;
			}
		}
	}
	while(!op.isEmpty()) {
		exp.push(op.pop());
	}
	if(i>=expression.length()) {
		String postfix = new String();
		char[] a= new char[exp.size()];
		for(int j=exp.size()-1;j>=0&&exp.isEmpty()==false;j--) {
			a[j]=(char)exp.pop();
		}
		postfix=String.valueOf(a);
		
		return postfix;
	}
	else {
		return "";
	}
}
/**
 * this method takes string expression but this expression will be numeric with only one space separating at most 
 * it uses one local stack named w where values are stored until there
 *  will be only one value remaining the one which will be returned by the method
 * @param expression
 * @return the expression evaluated value
 */
public int evaluate(String expression) {
	stk w=new stk();
	w.u=w.m;
	for(int i=0;i<expression.length();i++) {
		if(expression.charAt(i)=='+') {
			int tempone=(int)w.pop();
			int temptwo=(int)w.pop();
			int tempthree=tempone+temptwo;
			w.push(tempthree);
		}
		else if(expression.charAt(i)=='*') {
			int tempone=(int)w.pop();
			int temptwo=(int)w.pop();
			int tempthree=tempone*temptwo;
			w.push(tempthree);
		}
		else if(expression.charAt(i)=='/') {
			int tempone=(int)w.pop();
			int temptwo=(int)w.pop();
			int tempthree=temptwo/tempone;
			w.push(tempthree);
		}
		else if(expression.charAt(i)=='-') {
			int tempone=(int)w.pop();
			int temptwo=(int)w.pop();
			int tempthree=temptwo-tempone;
			w.push(tempthree);
		}
		else {
			int cn=0;
			int in=i;
			int found=1;
			while(expression.charAt(i)!=' '&&expression.charAt(i)!='	'&&expression.charAt(i)!='+'&&expression.charAt(i)!='/'&&expression.charAt(i)!='*'&&expression.charAt(i)!='-') {
				found=0;
				cn++;
				i++;
				if(i==expression.length()) {
					break;
				}
			}
			if(found==0) {
				i--;
			}
			if(cn!=0) {
				char[]n=new char[cn];
				int kl=0;
				while(expression.charAt(in)!=' '&&expression.charAt(i)!='	'&&expression.charAt(i)!='+'&&expression.charAt(i)!='/'&&expression.charAt(i)!='*'&&expression.charAt(i)!='-') {
					n[kl]=expression.charAt(in);
					in++;
					if(in==expression.length()) {
						break;
					}
					kl++;
				}
				int v;
				v=0;
				for(int o=cn-1;o>=0;o--) {
					v=v+(n[o]-'0')*(int)(Math.pow((double)10,(double)(cn-1-o)));
				}
				w.push(v);
			}
		}
	}
	return(int)w.peek();
}
	
}
