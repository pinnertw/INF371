import edu.polytechnique.xvm.asm.opcodes.*;

public final class EVar extends AbstractExpr {
  public final String name; // variable name

  public EVar(String name) {
    this.name = name;
  }

  @Override
  public void codegen(CodeGen cg) {
	  cg.pushInstruction(new RFR(cg.getOffset(this.name)));
    //throw new UnsupportedOperationException(); // FIXME
  }
}
