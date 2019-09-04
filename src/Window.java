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
import java.awt.event.WindowAdapter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;

@SuppressWarnings("unused")
public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField inputField;
	private JTable instructionsTable;
	private JTable pilhaTable;
	private JTextField breakField;

	/**
	 * Launch the application.
	 */
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
		contentPane.setLayout(null);

//		DefaultTableModel model = new DefaultTableModel();
//		JTable instructionsTable = new JTable(model);
//		instructionsTable.setBorder(new LineBorder(new Color(0, 0, 0)));
//		instructionsTable.setBounds(10, 22, 446, 323);
//
//		instructionsTable.setModel(new DefaultTableModel(new Object[][] {
//
//		}, new String[] { "Instrucao", "Parametro1", "Parametro2" }));
//		
//		Vector row = new Vector();
//		row.add("Instrucao1");
//		row.add("Para1");
//		row.add("Para2");
//		model.addRow(row);
//		contentPane.add(instructionsTable);

		JScrollPane scroll = new JScrollPane();
//		DefaultTableModel model = new DefaultTableModel();

//		TableColumn coluna1 = new TableColumn();
//		model.addColumn("Instrução");
//		model.addColumn("Parametro 1");
//		model.addColumn("Parametro 2");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 446, 318);
		contentPane.add(scrollPane_1);
		JTable instructionsTable_1 = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "Linha", "Instrução", "Atributo 1", "Atributo 2", "Comentário" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, Integer.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(instructionsTable_1);
//		instructionsTable_1.addColumn(coluna1);
		instructionsTable_1.setGridColor(Color.BLACK);
		instructionsTable_1.setShowGrid(true);

		JLabel lblPilha = new JLabel("Pilha");
		lblPilha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPilha.setHorizontalAlignment(SwingConstants.CENTER);
		lblPilha.setBounds(452, -3, 187, 30);
		contentPane.add(lblPilha);

		pilhaTable = new JTable();
		pilhaTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		pilhaTable.setBounds(466, 22, 163, 501);
		contentPane.add(pilhaTable);

		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrada.setBounds(21, 368, 129, 25);
		contentPane.add(lblEntrada);

		JTextArea inputArea = new JTextArea();
		inputArea.setBounds(10, 392, 154, 110);
		contentPane.add(inputArea);

		inputField = new JTextField();
		inputField.setBounds(10, 499, 158, 25);
		contentPane.add(inputField);
		inputField.setColumns(1);

		JLabel lblSada = new JLabel("Sa\u00EDda");
		lblSada.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSada.setHorizontalAlignment(SwingConstants.CENTER);
		lblSada.setBounds(203, 368, 129, 25);
		contentPane.add(lblSada);

		JTextArea outputArea = new JTextArea();
		outputArea.setBounds(188, 392, 163, 131);
		contentPane.add(outputArea);

		JLabel lblBreakPoint = new JLabel("Break Point");
		lblBreakPoint.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBreakPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblBreakPoint.setBounds(361, 368, 99, 25);
		contentPane.add(lblBreakPoint);

		JTextArea breakArea = new JTextArea();
		breakArea.setBounds(361, 392, 99, 110);
		contentPane.add(breakArea);

		//Jump button
		JButton jumpButton = new JButton("Jump");
		botaoJUMP botaoJump = new botaoJUMP();
		jumpButton.addActionListener(botaoJump);
		
		breakField = new JTextField();
		breakField.setBounds(361, 498, 99, 28);
		contentPane.add(breakField);
		breakField.setColumns(10);
		jumpButton.setBounds(350, 537, 89, 23);
		contentPane.add(jumpButton);

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

		JLabel lblInstrues = new JLabel("Instru\u00E7\u00F5es");
		lblInstrues.setBounds(0, 0, 396, 21);
		contentPane.add(lblInstrues);
		lblInstrues.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstrues.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrues.setLabelFor(this);

//		InstructionList instructions = new InstructionList("D:\\Workspace\\VirtualMachine-dev\\VirtualMachine\\object.txt");
		InstructionList instructions = new InstructionList("‎⁨/Users/pedro/Desktop/object.txt");
		for (Instruction list : instructions.list) {
			int count = 1;
			System.out.println(list.instructionName + " " + list.argument1 + " " + list.argument2);
			DefaultTableModel model = (DefaultTableModel) instructionsTable_1.getModel();
			model.addRow(new Object[]{count, list.instructionName, list.argument1, list.argument2, "NUll"});
			count++;
//			list.readInstruction();
			
		}

		String aux = new String();
	}
	
	private class botaoJUMP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		//acoes
			
		}	
	}
	
	private class botaoCONTINUE implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		//acoes
			
		}
	}
	
	private class botaoSTART implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		//acoes
			
		}
	}
	
	private class botaoSTOP implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		//acoes
			
		}
	}
}
