import java.util.Stack;
import java.util.LinkedList;
public class Calculator {
	Stack<Double> numbers;
	Stack<Operator> operators;
	LinkedList<Double> results;	
// Constructor
	public Calculator() {
		this.numbers = new Stack<Double>();
		this.operators = new Stack<Operator>();
		this.results = new LinkedList<Double>();
	}
	@Override
	public String toString() {
		String num = this.numbers.toString();
		String op = this.operators.toString();
		return num + "\n" + op;
	}
// push
	public void pushDouble(double d) {
		this.numbers.push(d);
	}
	public void pushOperator(Operator o) {
		this.operators.push(o);
	}
// getResult	
	public double getResult() {
		if(this.numbers.isEmpty()) {
			throw new RuntimeException();
		}
		else {
			return this.numbers.peek();
		}
	}
// ExecuteBinOperator
	public void executeBinOperator(Operator op) {
		double x = this.numbers.pop();
		double y = this.numbers.pop();
		switch(op) {
		case PLUS:
			pushDouble(x + y);
			break;
		case MINUS:
			pushDouble(y - x);
			break;
		case MULT:
			pushDouble(x * y);
			break;
		case DIV:
			pushDouble(y / x);
			break;
		default:
			break;
		}
		return;
	}
// priority
	private static int precedence(Operator op) {
		switch(op) {
		case PLUS:
			return 0;
		case MINUS:
			return 0;
		case MULT:
			return 1;
		case DIV:
			return 1;
		default:
			return -1;
		}
	}
// command	
	void commandOperator(Operator op) {
		if (this.operators.isEmpty()) {
			this.pushOperator(op);
			return;
		}
		Operator optop = this.operators.peek();
		while (!this.operators.isEmpty() && precedence(optop) >= precedence(op)) {
			this.executeBinOperator(this.operators.pop());
			if (!this.operators.isEmpty()) {
				optop = this.operators.peek();				
			}
			
		}
		this.pushOperator(op);
	}
	
	void commandDouble(double d) {
		this.pushDouble(d);
	}
	
// Equal
	void commandEqual() {
		while(!this.operators.isEmpty()) {
			this.executeBinOperator(this.operators.pop());
		}
		this.results.addFirst(this.numbers.peek());
	}
// Parentheses
	void commandLPar() {
		this.pushOperator(Operator.OPEN);
	}
	void commandRPar() {
		while (this.operators.peek() != Operator.OPEN) {
			this.executeBinOperator(this.operators.pop());
		}
		this.operators.pop();
	}
// init
	void commandInit() {
		this.numbers = new Stack<Double>();
		this.operators = new Stack<Operator>();
	}
// readmemory
	void commandReadMemory(int i) {
		this.numbers.push(this.results.get(i - 1));
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.pushDouble(1);
		cal.commandOperator(Operator.PLUS);
		cal.pushDouble(2);
		cal.commandOperator(Operator.MULT);
		cal.pushDouble(3);
		cal.commandEqual();
		cal.commandReadMemory(1);
		cal.commandOperator(Operator.PLUS);
		cal.commandReadMemory(1);
		cal.commandOperator(Operator.PLUS);
		cal.pushDouble(1);
		cal.commandEqual();
		System.out.println(cal);

	}
}