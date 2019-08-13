import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class InstructionList {
	public ArrayList<Instruction> list;
	private Instruction temporaryInstruction;
	
	public ArrayList<Instruction> getList() {
		return list;
	}
	public void setList(Instruction lista) {
		this.list.add(lista);
	}
	public Instruction getTemporaryInstruction() {
		return temporaryInstruction;
	}
	public void setTemporaryInstruction(Instruction temporaryInstruction) {
		this.temporaryInstruction = temporaryInstruction;
	}
	public InstructionList(String fileName) {
		list = new ArrayList<Instruction>();
		try {
			this.readFile(fileName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error opening file:" + e.getMessage());
		}
		
	}
	public void readFile(String fileName) throws IOException {
		
		FileReader fileReader = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fileReader);
		String readContent = reader.readLine();
		String fileReturn = "";
		
		while (readContent != null) {
			fileReturn = fileReturn + readContent;
			fileReturn = fileReturn + "\n";
			readContent = reader.readLine();
		}
		
		fileReader.close();

		String separatedInstructions[] = fileReturn.split("\n");
		for (int i = 0; i < separatedInstructions.length; i++) {
			temporaryInstruction = new Instruction();
			
			String instructionNameArgument[] = separatedInstructions[i].split(" ");
			
			temporaryInstruction.setInstructionName(instructionNameArgument[0]);
			
			if (instructionNameArgument.length > 1) {		
				temporaryInstruction.setArgument1(instructionNameArgument[1]);
			
				if (instructionNameArgument.length > 2) {
					temporaryInstruction.setArgument1(instructionNameArgument[2]);
				} else {
					temporaryInstruction.setArgument2(null);
				}
			}else {
				temporaryInstruction.setArgument1(null);
			}
			
			this.setList(temporaryInstruction);
		}
	}
	
}
