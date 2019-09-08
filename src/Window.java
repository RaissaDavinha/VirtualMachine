import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JList;
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
	private InstructionList instructions;
	private VirtualMachine machine = new VirtualMachine(path);
	private ArrayList<Integer> breakPoints = new ArrayList<Integer>();
	
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
		for (Instruction list : instructions.list) {
			System.out.println(list.getLabel() + "" +list.getInstructionName() + " " + list.getArgument1String() + " " + list.getArgument2String());
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
		
		//==== Entrada de dados ====
		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setBounds(21, 368, 129, 25);
		lblEntrada.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEntrada);

		JTextPane inputArea = new JTextPane();
		inputArea.setBounds(10, 392, 154, 110);
		contentPane.add(inputArea);

		inputField = new JTextField();
		inputField.setBounds(10, 499, 158, 25);
		contentPane.add(inputField);
		inputField.setColumns(1);

		//==== Saida de dados ====
		JLabel lblSaida = new JLabel("Sa\u00EDda");
		lblSaida.setBounds(203, 368, 129, 25);
		lblSaida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaida.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSaida);

		JTextPane outputArea = new JTextPane();
		outputArea.setBounds(188, 392, 163, 131);
		contentPane.add(outputArea);

		//==== Break Point ====
		JLabel lblBreakPoint = new JLabel("Break Point");
		lblBreakPoint.setBounds(361, 368, 99, 25);
		lblBreakPoint.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBreakPoint.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBreakPoint);

		breakArea = new JTable();
		breakArea.setBounds(361, 392, 99, 110);
		contentPane.add(breakArea);
		
		breakField = new JTextField();
		breakField.setBounds(361, 498, 99, 28);
		contentPane.add(breakField);
		breakField.setColumns(10);
		
		breakEnter breakEnter = new breakEnter();
		breakField.addKeyListener(breakEnter);

		//Jump button
		JButton jumpButton = new JButton("Jump");
		jumpButton.setBounds(350, 537, 89, 23);
		contentPane.add(jumpButton);
		botaoJUMP botaoJump = new botaoJUMP();
		jumpButton.addActionListener(botaoJump);

		//Continue button
		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(203, 537, 99, 23);
		contentPane.add(continueButton);
		botaoCONTINUE botaoContinue = new botaoCONTINUE();
		continueButton.addActionListener(botaoContinue);

		//Start button
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(6, 535, 75, 29);
		contentPane.add(btnStart);
		botaoSTART botaoStart = new botaoSTART();
		btnStart.addActionListener(botaoStart);

		//Stop button
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(93, 534, 75, 29);
		contentPane.add(btnStop);
		botaoSTOP botaoStop = new botaoSTOP();
		btnStop.addActionListener(botaoStop);
	}
	
	private class botaoJUMP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//acoes
			
		}	
	}
	
	private class botaoCONTINUE implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//continuar execucao apos breakpoint
			
		}
	}
	
	private class botaoSTART implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//inciar um loop
			//nao pode travar a interface
			//contador no loop para representar as linhas
			//se contador for igual a um dos breakpoints, parar
			//transmitir argumentos para serem colocados na pilha
			for (Instruction list : instructions.list) {
				machine.execInstruction(list);
				updateStack();
			}
		}
	}
	
	private class botaoSTOP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//parar looping
			
		}
	}
	
	private class breakEnter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ 
				int aux = Integer.parseInt(breakField.getText()); 
				System.out.println(aux);
				breakPoints.add(aux); 					//adiciona o valor recebido a lista de breakpoints
				DefaultTableModel model = (DefaultTableModel) breakArea.getModel();
				model.setRowCount(0);
				for(int i : breakPoints) {
					System.out.println(breakPoints.get(i));
					model.addRow(new Object[]{breakPoints.get(i)});
				}
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
			boolean press = (key == KeyEvent.VK_ENTER || (arg0.getKeyChar() == KeyEvent.VK_0)|| (arg0.getKeyChar() == KeyEvent.VK_1)|| (arg0.getKeyChar() == KeyEvent.VK_2) 
		            || (arg0.getKeyChar() == KeyEvent.VK_3)|| (arg0.getKeyChar() == KeyEvent.VK_4)|| (arg0.getKeyChar() == KeyEvent.VK_5)|| (arg0.getKeyChar() == KeyEvent.VK_6)|| (arg0.getKeyChar() == KeyEvent.VK_7)
		            || (arg0.getKeyChar() == KeyEvent.VK_8)|| (arg0.getKeyChar() == KeyEvent.VK_9));
			if (!press) {
				arg0.consume();
				System.out.println("nao eh numero");
			}
		}
	}
	
	private void updateStack() {
		Stack<Integer> dataStack = machine.getDataStack();
		DefaultTableModel model = (DefaultTableModel) stackTable.getModel();
		model.setRowCount(0);
		for(Integer i: dataStack) {
			System.out.println(dataStack.get(i));
			model.addRow(new Object[]{dataStack.get(i)});
		}
	}
}
