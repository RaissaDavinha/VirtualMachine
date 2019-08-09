import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class InstructionList {
	public ArrayList<Instruction> list;
	private Instruction temporaryInstruction;
	
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
			
			temporaryInstruction.instructionName = instructionNameArgument[0];
			
			if (instructionNameArgument.length > 1) {
				String instructionArguments[] = instructionNameArgument[1].split(",");
				
				temporaryInstruction.argument1 = instructionArguments[0];
			
				
				if (instructionArguments.length > 1) {
					temporaryInstruction.argument2 = instructionArguments[1];
				} else {
					temporaryInstruction.argument2 = null;
				}
			}
			
			list.add(temporaryInstruction);
		}
	}
	
}
