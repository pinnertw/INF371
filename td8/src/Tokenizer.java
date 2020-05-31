public class Tokenizer {
	boolean isStart = true;
	boolean isIntNum = false;
	boolean isNonIntNum = false;
	int decimalDigits = 0;
	double num;
	double isNeg;
	boolean isMinUnary;
	boolean mem;
	Calculator calc;
	
	public Tokenizer() {
		this.isStart = true;
		this.isIntNum = false;
		this.num = 0;
		this.isNonIntNum = false;
		this.decimalDigits = 0;
		this.isNeg = 1;
		this.isMinUnary = false;
		this.mem = false;
		this.calc = new Calculator();
	}

	public void push_operator(Operator op) {
		if (this.mem) {
			this.calc.commandReadMemory((int) this.num);
			System.out.println(this.calc.numbers);
			this.isIntNum = false;
			this.isNonIntNum = false;
			this.num = 0;
			this.isNeg = 1.;
			this.isMinUnary = true;
			this.mem = false;
			this.calc.commandOperator(op);
		}
		else if (this.isIntNum || this.isNonIntNum) {
			this.calc.pushDouble(this.isNeg * this.num);
			this.isIntNum = false;
			this.isNonIntNum = false;
			this.num = 0;
			this.isNeg = 1.;
			this.isMinUnary = true;
			this.calc.commandOperator(op);
		}
		else {
			this.isIntNum = false;
			this.isNonIntNum = false;
			this.num = 0;
			this.isNeg = 1.;
			this.isMinUnary = true;
			this.calc.commandOperator(op);
		}
	}
	
	public void readChar(char c) {
		if (Character.isDigit(c) && this.isNonIntNum) {
			this.num += new Double(Character.getNumericValue(c)) *
				Math.pow(10., -this.decimalDigits - 1);
			this.decimalDigits += 1;
		}
		else if (Character.isDigit(c) && this.isIntNum) {
			this.num = 
				this.num * 10 + 
				new Double(Character.getNumericValue(c));
		}
		
		else if (Character.isDigit(c)) {
			this.isIntNum = true;
			this.isStart = false;
			this.num += 
				new Double(Character.getNumericValue(c));
			this.isMinUnary = false;
		}
		else if (c == '='){
			if (this.mem) {
				this.calc.commandReadMemory((int) this.num);
				this.calc.commandEqual();
				this.isStart = true;
				this.isNonIntNum = false;
				this.num = 0;
				this.isNeg = 1.;
				this.isMinUnary = false;
				this.isIntNum = false;
				this.mem = false;
			}
		// number before '='
			else if (this.isIntNum ||this.isNonIntNum) {
				this.calc.pushDouble(this.isNeg * this.num);
				this.calc.commandEqual();
				this.isStart = true;
				this.isNonIntNum = false;
				this.num = 0;
				this.isNeg = 1.;
				this.isMinUnary = false;
				this.isIntNum = false;
				this.mem = false;
			}
		// parenthese before '='
			else{
				this.calc.commandEqual();
				this.isIntNum = false;
				this.isNonIntNum = false;
				this.num = 0;
				this.isNeg = -1;
				this.isMinUnary = false;
				this.isStart = true;
				this.mem = false;
			}
			
		}
		else if (c == '+') {
			this.push_operator(Operator.PLUS);
		}

		else if (c == '-') {
			if (this.isStart || this.isMinUnary) {
				if (this.isIntNum ||this.isNonIntNum) {
					this.calc.pushDouble(this.isNeg * this.num);
				}
				this.isNeg = -1;
				this.num = 0;
				this.isIntNum = false;
				this.isNonIntNum = false;
				this.isStart = false;
				this.isMinUnary = false;
			}
			else {
				this.push_operator(Operator.MINUS);
			}
		}
		else if (c == '*') {
			this.push_operator(Operator.MULT);
		}
		else if (c == '/') {
			this.push_operator(Operator.DIV);
		}
		else if (c == '(') {
			this.calc.commandLPar();
		}
		else if (c == ')') {
		// number before ')'
			if(this.isIntNum || this.isNonIntNum) {
				this.calc.pushDouble(this.isNeg * this.num);
				this.isIntNum = false;
				this.isNonIntNum = false;
				this.num = 0;
				this.isNeg = 1.;
				this.isMinUnary = false;
				this.calc.commandRPar();
			}
			else {
				this.calc.commandRPar();
			}
		}
		else if (c == '.') {
			this.decimalDigits = 0;
			this.isStart = false;
			this.isNonIntNum = true;
		}
		else if (c == 'C') {
			this.isStart = true;
			this.isIntNum = false;
			this.num = 0;
			this.isNonIntNum = false;
			this.decimalDigits = 0;
			this.isNeg = 1;
			this.isMinUnary = false;
			this.mem = false;
			this.calc = new Calculator();
		}
		else if (c == '$') {
			this.mem = true;
		}
	}
	
	public void readString(String s) {
		for (int i = 0; i < s.length(); i++) {
			readChar(s.charAt(i));
		}
	}
	public static void main(String[] args) {
		Tokenizer cal = new Tokenizer();
		cal.readString("1+2=$1+4=$1-2=");
		System.out.println(cal.calc.numbers);
	}
}
