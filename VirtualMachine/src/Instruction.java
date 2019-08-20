
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
	
	public void readInstruction() {
		switch (this.instructionName) {
		case "LDC":
			
			break;
			
		case "LDV":
			
			break;
			
		case "ADD":
			break;
			
		case "SUB":
			
			break;
			
		case "MULT":
			
			break;
			
		case "DIVI":
			
			break;
			
		case "INV":
			
			break;
			
		case "AND":
			
			break;
			
		case "OR":
			
			break;
			
		case "NEG":
			
			break;
		
		case "CME":
			
			break;
			
		case "CMA":
			
			break;
			
		case "CEQ":
			
			break;
			
		case "CDIF":
			
			break;
			
		case "CMEQ":
			
			break;
		
		case "CMAQ":
			
			break;
		case "START":
			
			break;
		case "HILT":
			
			break;
		case "STR":
			
			break;
		case "JMP":
			
			break;
		case "JMPF":
			
			break;
		case "NULL":
			
			break;
		case "RD":
			
			break;
		case "PRN":
			
			break;
		case "ALLOC":
			
			break;
		case "DALLOC":
			
			break;
		case "CALL":
			
			break;
		case "RETURN":
			
			break;
	
		default:
			break;
		}
	}

}