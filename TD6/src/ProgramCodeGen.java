import java.util.List;

import edu.polytechnique.mjava.ast.Instruction;
import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.STOP;

public class ProgramCodeGen {
  public final CodeGen cg = new CodeGen();

  public static String labelOfProcName(String name) {
    return String.format("__%s", name);
  }

  public void codegen(TProcDef<AbstractExpr, AbstractInstruction> proc) {
    final List<VarDecl> args = proc.getArgs(); // Proc. arguments
    final List<VarDecl> locals = proc.getLocals(); // Proc. locals
    final AbstractInstruction is = proc.getBody(); // Proc. body
    
    this.cg.proc = proc;
    
    int k = 0;
    for (VarDecl i: args) {
    	this.cg.pushLocalVariable(i.getName(), k - args.size());
    	k += 1;
    }
    
    this.cg.pushInstruction(new GSB(labelOfProcName(proc.getName())));
    
    int j = 0;
    for (VarDecl i: locals) {
    	this.cg.pushLocalVariable(i.getName(), j - locals.size());
    	j += 1;
    }
    
    is.codegen(this.cg);
    
    IReturn r = new IReturn();
    r.codegen(this.cg);
    
    this.cg.proc = null;
    //throw new UnsupportedOperationException(); // FIXME
  }

  public void codegen(List<TProcDef<AbstractExpr, AbstractInstruction>> procs) {
    for (TProcDef<AbstractExpr, AbstractInstruction> proc : procs)
      this.codegen(proc);
  }

  public ProgramCodeGen() {
    this.cg.pushInstruction(new GSB(labelOfProcName("main")));
    this.cg.pushInstruction(new STOP());
  }
}
