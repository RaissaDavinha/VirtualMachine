import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;

@SuppressWarnings({ "unused", "serial" })
public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField inputField;
	private JTable instructionsTable;
	private JTextField breakField;
	private JTable breakArea;
	String path = "object.txt";
	private JTable stackTable;
	private Stack<Integer> dataStack;
	private InstructionList instructions;
	private VirtualMachine machine = new VirtualMachine(path);
	private ArrayList<Integer> breakPoints = new ArrayList<Integer>();
	private JTable outputTable;
	private JTable inputTable;
	private String argumento;
	private boolean inputWasInserted = false;
	private int readValue = 0;
	private int stackPointer;
	
	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setResizable(false);
		setTitle("Virtual Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/*==== Instrucoes ====*/
		JScrollPane scroll = new JScrollPane();
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 446, 318);
		contentPane.add(scrollPane_1);
		@SuppressWarnings({ "unchecked", "rawtypes"})
		JTable instructionsTable_1 = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Linha", "Label", "Instrução", "Atributo 1", "Atributo 2", "Comentário" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, String.class, String.class };
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(instructionsTable_1);
		instructionsTable_1.setGridColor(Color.BLACK);
		instructionsTable_1.setShowGrid(true);
		
		JLabel lblInstrucoes = new JLabel("Instru\u00E7\u00F5es");
		lblInstrucoes.setBounds(0, 0, 396, 21);
		contentPane.add(lblInstrucoes);
		lblInstrucoes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrucoes.setLabelFor(this);

//		InstructionList instructions = new InstructionList("D:\\Workspace\\VirtualMachine-dev\\VirtualMachine\\object.txt");
		instructions = machine.getInstructionList();
		int count = 1;
		for (Instruction list : instructions.getList()) {
			DefaultTableModel model = (DefaultTableModel) instructionsTable_1.getModel();
			model.addRow(new Object[]{count, list.getLabel(), list.getInstructionName(), list.getArgument1String(), list.getArgument2String(), "NUll"});
			count++;
		}

		//===== Pilha =====
		JLabel lblPilha = new JLabel("Pilha");
		lblPilha.setBounds(452, -3, 187, 30);
		lblPilha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPilha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPilha);
			
		JScrollPane scrollStack = new JScrollPane();
		scrollStack.setBounds(468, 22, 161, 501);
		contentPane.add(scrollStack);
		
		stackTable = new JTable();
		stackTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Dados"}
		) {
			Class[] columnTypes = new Class[] { Integer.class };
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollStack.setViewportView(stackTable);
				
		JScrollPane scrollPane_input = new JScrollPane();
		scrollPane_input.setBounds(12, 368, 156, 134);
		contentPane.add(scrollPane_input);
		
		//==== Input ====
		inputTable = new JTable();
		inputTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Entrada"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_input.setViewportView(inputTable);

		inputField = new JTextField();
		inputField.setBounds(12, 499, 156, 25);
		contentPane.add(inputField);
		inputField.setColumns(1);
				
		inputEnter inputEnter = new inputEnter();
		inputField.addKeyListener(inputEnter);
		
		//==== BreakPoints ====
		JScrollPane scrollPane_breakPoint = new JScrollPane();
		scrollPane_breakPoint.setBounds(337, 368, 119, 134);
		contentPane.add(scrollPane_breakPoint);

		breakArea = new JTable();
		breakArea.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"BreakPoints"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_breakPoint.setViewportView(breakArea);
				
		breakField = new JTextField();
		breakField.setBounds(337, 498, 119, 28);
		contentPane.add(breakField);
		breakField.setColumns(10);
		
		breakEnter breakEnter = new breakEnter();
		breakField.addKeyListener(breakEnter);
		
		//==== Output =====
		JScrollPane scrollPane_output = new JScrollPane();
		scrollPane_output.setBounds(180, 368, 145, 152);
		contentPane.add(scrollPane_output);
		
		outputTable = new JTable();
		outputTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Sa\u00EDda"}) {Class[] columnTypes = new Class[] {String.class};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_output.setViewportView(outputTable);

		//Jump button
		JButton jumpButton = new JButton("Jump");
		jumpButton.setBounds(236, 535, 89, 30);
		contentPane.add(jumpButton);
		botaoJUMP botaoJump = new botaoJUMP();
		jumpButton.addActionListener(botaoJump);

		//Start button
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(31, 536, 75, 29);
		contentPane.add(btnStart);
		botaoSTART botaoStart = new botaoSTART();
		btnStart.addActionListener(botaoStart);
		
		//Stop button
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(135, 536, 75, 29);
		contentPane.add(btnStop);
		botaoSTOP botaoStop = new botaoSTOP();
		btnStop.addActionListener(botaoStop);
		
		//Clear BP button
		JButton btnClearBreakpoint = new JButton("Clear BP");
		btnClearBreakpoint.setBounds(349, 535, 100, 30);
		contentPane.add(btnClearBreakpoint);
		botaoCLEARBP botaoCLEARBP = new botaoCLEARBP();
		btnClearBreakpoint.addActionListener(botaoCLEARBP);
	}
	
	private class botaoJUMP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//acoes
			
		}	
	}
	
	//Limpa lista de breakPoints 
		private class botaoCLEARBP implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) breakArea.getModel();
				model.setRowCount(0);
				breakPoints = new ArrayList<Integer>();
			}	
		}
	
	private class botaoSTART implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (machine.getVirtualMachineOn() == false) {
				
			}
			machine.setExecutingTrue();
			while (machine.getExecuting()) {
				try {
					machine.execInstruction();
				}
				catch (Exception e) {
					Instruction instruction = machine.getInstruction();
					JOptionPane.showMessageDialog(null, "Error at instruction: " + machine.getProgramPointer() + " " + instruction.getInstructionName());
				}
				if (machine.isPrintInstruction()) {
					JOptionPane.showMessageDialog(null, "print");
					int printValue = machine.getPrintValue();
					// print to field
					DefaultTableModel model = (DefaultTableModel) outputTable.getModel();
					model.addRow(new Object[]{printValue});
				}
				machine.programCounterIncrement();
				// updateInstructionList();
				updateStack();
				if (machine.isBreakLine()) {
					machine.setExecutingFalse();
				}
			}
		}
	}
	
	private class botaoSTOP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//parar looping
			
		}
	}
	
	//==== recebe um breakpoint ao ser clicado o enter e salva em um arrayList ====
	private class breakEnter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			try {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ 
					int aux = Integer.parseInt(breakField.getText());
					breakPoints.add(aux); 					//adiciona o valor recebido a lista de breakpoints
					DefaultTableModel model = (DefaultTableModel) breakArea.getModel();
					model.setRowCount(0);
					for(int i = 0; i < breakPoints.size(); i++) {
						model.addRow(new Object[]{breakPoints.get(i)});
					}
					repaint();
					breakField.setText("");
				}
			} catch (Exception e) {
				breakField.setText("");
				JOptionPane.showMessageDialog(null, "Not an valid number!");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			JTextField jtf = (JTextField) arg0.getSource();
			char key = arg0.getKeyChar();
			boolean press = (key == KeyEvent.VK_ENTER || (key == KeyEvent.VK_0)|| (key == KeyEvent.VK_1)|| (key == KeyEvent.VK_2) 
		            || (key == KeyEvent.VK_3)|| (key == KeyEvent.VK_4)|| (key == KeyEvent.VK_5)|| (key == KeyEvent.VK_6)|| (key == KeyEvent.VK_7)
		            || (key == KeyEvent.VK_8)|| (key == KeyEvent.VK_9));
			if (!press) {
				arg0.consume();
			}
		}
	}
	
	private void updateStack() {
		Stack<Integer> dataStack = machine.getDataStack();
		stackPointer = machine.getStackPointer();
		if (stackPointer >= 0) {
			DefaultTableModel model = (DefaultTableModel) stackTable.getModel();
			model.setRowCount(0);
			for(int i = 0; i <= stackPointer; i++) {
				model.addRow(new Object[]{dataStack.get(i)});
			}
		}
		repaint();
	}
	
	//==== recebe input ao ser clicado o enter e salva em uma variável ====
	private class inputEnter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			try {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ 
					argumento = inputField.getText();
					readValue = Integer.parseInt(argumento);
					inputWasInserted = true;
					DefaultTableModel model = (DefaultTableModel) inputTable.getModel();
					model.addRow(new Object[]{argumento});
					repaint();
					inputField.setText("");
				}
			}
			catch (Exception e) {
				inputField.setText("");
				argumento = null;
				JOptionPane.showMessageDialog(null, "Not an valid number!");
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
	}
}