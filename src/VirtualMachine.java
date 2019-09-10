import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

public class VirtualMachine {
	Stack<Integer> dataStack;
	InstructionList instructions;
	public ArrayList<Integer> breakPoints;
	private int readValue;
	private int printValue;
	
	int programCounter;
	int stackPointer;
	int auxRegister;
	private boolean executing;
	boolean virtualMachineOn;
	int count;
	String leitor;
	String jumpPos;
	int stackRecover;
	
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
	public void setReadValue(int readValue) {
		this.readValue = readValue;
	}
	public int getPrintValue() {
		return this.printValue;
	}
	public boolean getExecuting() {
		return executing;
	}
	public void setExecutingTrue() {
		executing = true;
	}
	public void setExecutingFalse() {
		executing = false;
	}
	public boolean isBreakLine() {
		return breakPoints.contains(programCounter);
	}
	public InstructionList getInstructionList() {
		return instructions;
	}
	public Stack<Integer> getDataStack() {
		return dataStack;
	}
	public boolean isReadInstruction() {
		if (instructions.getInstruction(programCounter).getInstructionName() == "RD") {
			return true;
		} else {
			return false;
		}
	}
	public boolean isPrintInstruction() {
		if (instructions.getInstruction(programCounter).getInstructionName() == "PRN") {
			return true;
		} else {
			return false;
		}
	}
	public void executeMachine() {
		if (executing == true) {
			execInstruction(instructions.getInstruction(programCounter));
			programCounter++;
		} else {
			executing = false;
		}
	}
		//clean everything
	
	public ArrayList<Integer> getBreakPoints() {
		return breakPoints;
	}

	public void setBreakPoints(ArrayList<Integer> breakPoints) {
		this.breakPoints = breakPoints;
	}

public void execInstruction(Instruction instruction) {
		
		switch (instruction.getInstructionName()) {
		case "LDC":
//			if(dataStack.size() < stackPointer + 1) {
//				dataStack.push(null);
//			}
			stackPointer++;
//			dataStack.push(instruction.getArgument1Int());
			dataStack.push(0);
			dataStack.set(stackPointer, instruction.getArgument1Int());
			break;
			
		case "LDV":
//			if(dataStack.size() < stackPointer + 1) {
//				dataStack.push(null);
//			}
			stackPointer++;
//			dataStack.push(dataStack.get(instruction.getArgument1Int()));
//			dataStack.push(2222);
			dataStack.set(stackPointer, dataStack.get(instruction.getArgument1Int()));
			
			break;
			
		case "ADD":
			auxRegister = dataStack.get(stackPointer - 1) + dataStack.get(stackPointer);
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
//			dataStack.set(stackPointer, auxRegister);
			System.out.println("Resultado add " + auxRegister);
			System.out.println("Topo da pilha dps do add " + dataStack.get(stackPointer));
			break;
			
		case "SUB":
			auxRegister = dataStack.get(stackPointer - 1) - dataStack.get(stackPointer);
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			break;
			
		case "MULT":
			auxRegister = dataStack.get(stackPointer - 1) * dataStack.get(stackPointer);
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			break;
			
		case "DIVI":
			auxRegister = dataStack.get(stackPointer - 1) / dataStack.get(stackPointer);
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
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
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
			
		case "OR":
			if(dataStack.get(stackPointer - 1) == 1 || dataStack.get(stackPointer) == 1) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
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
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
			
		case "CMA":
			if(dataStack.get(stackPointer - 1) > dataStack.get(stackPointer)) {
				auxRegister = 1;
				System.out.println("=============================");
				System.out.println("CMA -1 eh maior");
				System.out.println("=============================");
			}else {
				auxRegister = 0;
				System.out.println("=============================");
				System.out.println("CMA -1 eh menor");
				System.out.println("=============================");
			}
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
			
		case "CEQ":
			if(dataStack.get(stackPointer - 1) == dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
			
		case "CDIF":
			if(dataStack.get(stackPointer - 1) != dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
			
		case "CMEQ":
			if(dataStack.get(stackPointer - 1) <= dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
		
		case "CMAQ":
			if(dataStack.get(stackPointer - 1) >= dataStack.get(stackPointer)) {
				auxRegister = 1;
			}else {
				auxRegister = 0;
			}
			dataStack.pop();
			stackPointer--;
			dataStack.pop();
			stackPointer--;
			dataStack.push(auxRegister);
			stackPointer++;
			break;
		case "START":
			stackPointer = -1;
			break;
		case "HLT":
			System.exit(0);
			break;
		case "STR":
//			auxRegister = dataStack.get(instruction.getArgument1Int());
			
//			dataStack.push(auxRegister);
			
			auxRegister = dataStack.get(stackPointer);
			dataStack.set(instruction.getArgument1Int(), auxRegister);
			System.out.println("===========================================");
			System.out.println("Valor do str " + auxRegister);
			System.out.println("StackPointer dentro do str " + stackPointer);
			System.out.println("===========================================");
			stackPointer--;
			break;
		case "JMP":
			jumpPos = instruction.getArgument1String();
			for (Instruction list : instructions.list) {
				if (instruction.getInstructionName() == jumpPos) {
					stackPointer = count;
				}
				count++;
			}
			break;
		case "JMPF":
			System.out.println(stackPointer);
			System.out.println(dataStack.size());
			if (dataStack.get(stackPointer) == 0) {
				jumpPos = instruction.getArgument1String();
				
				for (Instruction list : instructions.list) {
					if (instruction.getInstructionName() == jumpPos) {
						stackPointer = count;
					}
					count++;
				}
			}else {
				programCounter++;
			}
			dataStack.pop();
			break;
		case "NULL":
			
			break;
		case "RD":
			stackPointer++;
			leitor = JOptionPane.showInputDialog(null, "Digite um valor ");
			dataStack.push(Integer.parseInt(leitor));
			System.out.println("Leitura do dado"+ dataStack.get(stackPointer));
			break;
		case "PRN":
//			printar um resultado
			JOptionPane.showMessageDialog(null, dataStack.pop());
			stackPointer--;
			break;
		case "ALLOC":
			for (int k = 0; k < (instruction.getArgument2Int() - 1); k++) {
				stackPointer++;
//				while (dataStack.size() < instruction.getArgument1Int() + instruction.getArgument2Int()) {
//					dataStack.push(0);
//				}
				if((instruction.getArgument1Int() + k) >= 0){
					auxRegister = 0;
				}else {
					auxRegister = dataStack.get(instruction.getArgument1Int() + k);
					System.out.println(auxRegister);
				}
				dataStack.push(auxRegister);
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
			stackPointer++;
			stackRecover = stackPointer;
			jumpPos = instruction.getArgument1String();
			for (Instruction list : instructions.list) {
				if (instruction.getInstructionName() == jumpPos) {
					stackPointer = count;
				}
				count++;
			}
			
			break;
		case "RETURN":
			stackPointer--;
			stackPointer = stackRecover;
			break;
	
		default:
			break;
		}
	}
}