import edu.polytechnique.xvm.asm.opcodes.*;
import edu.polytechnique.mjava.ast.BinOp;

public final class EBinOp extends AbstractExpr {
  public final BinOp        op   ;    // operator (enum)
  public final AbstractExpr left ;    // left operand
  public final AbstractExpr right;    // right operand

  public EBinOp(BinOp op, AbstractExpr left, AbstractExpr right) {
    this.op = op;
    this.left = left;
    this.right = right;
  }

  @Override
  public void codegen(CodeGen cg) {
    switch (this.op) {
    /** Addition */
    case ADD:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new ADD());
    	break;
    /** Subtraction */
    case SUB:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new SUB());
    	break;    	
    /** Multiplication */
    case MUL:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new MULT());
    	break;
    /** Division */
    case DIV:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new DIV());
    	break;
    /** (logical) and */
    case AND:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new AND());
    	break;
    /** (logical) or */
    case OR:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new OR());
    	break;
    /** Equal */
    case EQ:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new EQ());
    	break;
    /** Not equal */
    case NEQ:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new EQ());
    	cg.pushInstruction(new NOT());
    	break;
    /** Lower than */
    case LT:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new LT());
    	break;
    /** Lower or equal than */
    case LE:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new LT());
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new EQ());
    	cg.pushInstruction(new OR());
    	break;
    /** Greater than */
    case GT:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new LT());
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new EQ());
    	cg.pushInstruction(new OR());
    	cg.pushInstruction(new NOT());
    	break;
    /** Greater or equal than */
    case GE:
    	this.left.codegen(cg);
    	this.right.codegen(cg);
    	cg.pushInstruction(new LT());
    	cg.pushInstruction(new NOT());
    	break;
    }
  }
}
