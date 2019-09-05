import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine {
	Stack<Integer> dataStack;
	InstructionList instructions;
	public ArrayList<Integer> breakPoints;
	
	int programCounter;
	int stackPointer;
	int auxRegister;
	boolean executing;
	boolean virtualMachineOn;
	
	public VirtualMachine(String path) {
		dataStack = new Stack<Integer>();
		instructions = new InstructionList(path);
		breakPoints = new ArrayList<Integer>();
		
		for (int i = 0; i < instructions.list.size(); i++) {
			System.out.println(instructions.list.get(i).getLabel() + " " + instructions.list.get(i).getInstructionName() + " " + instructions.list.get(i).getArgument1String() + " " + instructions.list.get(i).getArgument2String());	
		}
		
		
		programCounter = 0;
		stackPointer = 0;
		auxRegister = 0;
		executing = true;
		virtualMachineOn = true;
	}
	
	public InstructionList getInstructionList() {
		return instructions;
	}
	public Stack<Integer> getDataStack() {
		return dataStack;
	}
	
	public void executeMachine() {
		while (virtualMachineOn) {
			
			if (breakPoints.contains(programCounter)) {
				executing = false;
			} else {
				execInstruction(instructions.getInstruction(programCounter));
				programCounter++;
			}
			
		}
		//clean everything
	}
	public void execInstruction(Instruction instruction) {
		
		switch (instruction.getInstructionName()) {
		case "LDC":
			if(dataStack.size() < stackPointer + 1) {
				dataStack.push(null);
			}
			stackPointer++;
			dataStack.set(stackPointer, instruction.getArgument1Int());
			break;
			
		case "LDV":
			if(dataStack.size() < stackPointer + 1) {
				dataStack.push(null);
			}
			stackPointer++;
			dataStack.set(stackPointer, dataStack.get(instruction.getArgument1Int()));
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
		case "HLT":
			System.exit(0);
			break;
		case "STR":
			auxRegister = dataStack.get(instruction.getArgument1Int());
			dataStack.push(auxRegister);
			stackPointer++;
			break;
		case "JMP":
			programCounter = instruction.getArgument1Int();
			break;
		case "JMPF":
			if (dataStack.get(stackPointer) == 0) {
				programCounter = instruction.getArgument1Int();
			}else {
				programCounter++;
			}
			dataStack.pop();
			break;
		case "NULL":
			
			break;
		case "RD":
//			Pegar dado do usuario
			break;
		case "PRN":
//			printar um resultado
			break;
		case "ALLOC":
			for (int k = 0; k < (instruction.getArgument2Int() - 1); k++) {
				stackPointer++;
				while (dataStack.size() < instruction.getArgument1Int() + instruction.getArgument2Int()) {
					dataStack.push(null);
				}
				auxRegister = dataStack.get(instruction.getArgument1Int() + k);
				dataStack.set(stackPointer, auxRegister);
			}
			break;
		case "DALLOC":
			for (int k = (instruction.getArgument2Int() - 1); k <  1; k--) {
				dataStack.set((instruction.getArgument1Int() + k), dataStack.get(stackPointer));
				dataStack.pop();
				stackPointer--;
			}
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
