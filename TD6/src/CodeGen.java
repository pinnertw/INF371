import java.util.*;
import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.xvm.asm.*;
import edu.polytechnique.xvm.asm.interfaces.*;

public final class CodeGen {
  private Vector<AsmInstruction> instructions;
  private Map<String, Integer>   labels;
  private Map<String, Integer>   offsets;
  public TProcDef<AbstractExpr, AbstractInstruction>  proc;

  public CodeGen() {
    this.instructions = new Vector<AsmInstruction>();
    this.labels = new HashMap<String, Integer>();
    this.offsets = new HashMap<String, Integer>();
    this.proc = null;
  }

  private static int labelc = 0;

  public static String generateLabel() {
	  if (labelc >= 100){
		  labelc += 1;
		  return "l"+ Integer.toString(labelc - 1);
	  }
	  else if (labelc >= 10){
		  labelc += 1;
		  return "l0" + Integer.toString(labelc - 1);
	  }
	  else {
		  labelc += 1;
		  return "l00" +Integer.toString(labelc - 1); 
	  }
    // Generate a fresh label using `labelc'.
    // For example, lXXX where XXX is the value of labelc.
    // Two generated labels should never be equal.
    // A label must start with a lowercase letter.
    // throw new UnsupportedOperationException();
  }

  public void pushLabel(String label) {
	  this.labels.put(label, this.instructions.size());
    //throw new UnsupportedOperationException(); // FIXME
  }

  public void pushInstruction(AsmInstruction asm) {
	  this.instructions.add(asm);
    //throw new UnsupportedOperationException(); // FIXME
  }

  public void pushLocalVariable(String name, int offset) {
	  this.offsets.put(name, offset);
    //throw new UnsupportedOperationException(); // FIXME
  }
  
  public void clearLocals() {
    this.offsets.clear();
  }
  
  public int getOffset(String var) {
	  return this.offsets.get(var);
  }

  public int getNb() {
	  return this.proc.getLocals().size();
  }
  
  @Override
  public String toString() {
    return Printer.programToString(this.instructions, this.labels);
  }
}
