import edu.polytechnique.xvm.asm.opcodes.*;

public final class IPrint extends AbstractInstruction {
  public final AbstractExpr expr; // Expression (int) to print
  
  public IPrint(AbstractExpr expr) {
    this.expr = expr;
  }

  @Override
  public void codegen(CodeGen cg) {
	  System.out.print(this.expr);
    //throw new UnsupportedOperationException(); // FIXME
  }
}
