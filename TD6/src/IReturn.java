import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;

public final class IReturn extends AbstractInstruction {
  public final Optional<AbstractExpr> result; // Value to return

  public IReturn() {
    this(Optional.empty());
  }

  public IReturn(Optional<AbstractExpr> result) {
    this.result = result;
  }

  @Override
  public void codegen(CodeGen cg) {
	  cg.pushInstruction(new PXR());
	  for (int i = 0; i < cg.getNb(); i++) {
		  cg.pushInstruction(new POP());
	  }
	  
    //throw new UnsupportedOperationException(); // FIXME
  }
}
