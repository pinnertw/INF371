import edu.polytechnique.xvm.asm.opcodes.*;

public final class EBool extends AbstractExpr {
  public final boolean value; // Literal value

  public EBool() {
    this(false);
  }

  public EBool(boolean value) {
    this.value = value;
  }

  @Override
  public void codegen(CodeGen cg) {
    //throw new UnsupportedOperationException(); // FIXME
	  if (this.value) {
		  cg.pushInstruction(new PUSH(1));
	  }
	  else {
		  cg.pushInstruction(new PUSH(0));
	  }
  }
}
