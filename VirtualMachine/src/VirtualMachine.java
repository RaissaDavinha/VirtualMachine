import java.util.Stack;

public class VirtualMachine {
	Stack<Integer> dataStack = new Stack<Integer>();
	InstructionList instructions = new InstructionList("D:\\Workspace\\VirtualMachine-dev\\VirtualMachine\\object.txt");
	int programCounter = 0;
	int stackPointer = 0;
	int auxRegister = 0;
	
	
	public void execInstruction(Instruction instruction) {
		
		switch (instruction.instructionName) {
		case "LDC":
			stackPointer++;
			dataStack.push(instruction.getArgument1());
			break;
			
		case "LDV":
			stackPointer++;
			dataStack.push(instruction.getArgument1());
			break;
			
		case "ADD":
			auxRegister = dataStack.get(stackPointer - 1) + dataStack.get(stackPointer);
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "SUB":
			auxRegister = dataStack.get(stackPointer - 1) - dataStack.get(stackPointer);
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "MULT":
			auxRegister = dataStack.get(stackPointer - 1) * dataStack.get(stackPointer);
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "DIVI":
			auxRegister = dataStack.get(stackPointer - 1) / dataStack.get(stackPointer);
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "INV":
			auxRegister = dataStack.pop();
			auxRegister = -auxRegister;
			dataStack.push(auxRegister);
			break;
			
		case "AND":
			if (dataStack.get(stackPointer - 1) == 1 && dataStack.get(stackPointer) == 1) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "OR":
			if(dataStack.get(stackPointer - 1) == 1 || dataStack.get(stackPointer) == 1) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "NEG":
			auxRegister = dataStack.pop();
			auxRegister = 1 - auxRegister;
			dataStack.push(auxRegister);
			break;
		
		case "CME":
			if(dataStack.get(stackPointer - 1) < dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "CMA":
			if(dataStack.get(stackPointer - 1) > dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "CEQ":
			if(dataStack.get(stackPointer - 1) == dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "CDIF":
			if(dataStack.get(stackPointer - 1) != dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
			
		case "CMEQ":
			if(dataStack.get(stackPointer - 1) <= dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
		
		case "CMAQ":
			if(dataStack.get(stackPointer - 1) >= dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			dataStack.pop();
			dataStack.push(auxRegister);
			break;
		case "START":
			stackPointer = -1;
			break;
		case "HILT":
			System.exit(0);
			break;
		case "STR":
			dataStack.push(instruction.argument1);
			stackPointer++;
			break;
		case "JMP":
			programCounter = instruction.argument1;
			break;
		case "JMPF":
			if (dataStack.get(stackPointer) == 0) {
				programCounter = instruction.argument1;
			}else {
				programCounter++;
			}
			dataStack.pop();
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
