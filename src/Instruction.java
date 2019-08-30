
public class Instruction {
	public String instructionName;
	public int argument1;
	public int argument2;
	
	public String getInstructionName() {
		return instructionName;
	}
	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
	}
	public int getArgument1() {
		return argument1;
	}
	public void setArgument1(String argument1) {
		int aux = Integer.parseInt(argument1);
		this.argument1 = aux;
	}
	public int getArgument2() {
		return argument2;
	}
	public void setArgument2(String argument2) {
		int aux = Integer.parseInt(argument2);
		this.argument2 = aux;
	}
	

}