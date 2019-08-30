import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		InstructionList instructions = new InstructionList("D:\\Workspace\\VirtualMachine-dev\\VirtualMachine\\object.txt");
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Window window = new Window();
				window.setVisible(true);
			}
		});
		
		for (Instruction list : instructions.list) {
//			System.out.println(list.instructionName + " " + list.argument1 + " " + list.argument2);
//			list.readInstruction();
		}
		
		
		
	}
}
