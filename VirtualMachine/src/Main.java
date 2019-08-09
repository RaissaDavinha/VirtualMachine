
public class Main {
	public static void main(String[] args) {
		InstructionList instructions = new InstructionList("object.txt");
		
		for (Instruction list : instructions.list) {
			System.out.println(list.instructionName + " " + list.argument1 + " " + list.argument2);
		}
	}
}
