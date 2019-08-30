import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		JLabel lblInstrues = new JLabel("Instru\u00E7\u00F5es");
		lblInstrues.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstrues.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstrues.setLabelFor(this);
		lblInstrues.setBounds(10, 0, 400, 25);
		contentPane.add(lblInstrues);
		
		DefaultTableModel model = new DefaultTableModel();
		JTable instructionsTable = new JTable(model);
		instructionsTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		instructionsTable.setBounds(10, 22, 446, 162);
		
		TableColumn coluna1 = new TableColumn();
		model.addColumn("Instrução");
		model.addColumn("Parametro 1");
		model.addColumn("Parametro 2");
		instructionsTable.addColumn(coluna1);
		contentPane.add(instructionsTable);
		
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
		
		JButton jumpButton = new JButton("Jump");
		jumpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JTextArea breakArea = new JTextArea();
		breakArea.setBounds(361, 392, 99, 110);
		contentPane.add(breakArea);
		
		breakField = new JTextField();
		breakField.setBounds(361, 498, 99, 28);
		contentPane.add(breakField);
		breakField.setColumns(10);
		jumpButton.setBounds(350, 537, 89, 23);
		contentPane.add(jumpButton);
		
		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(203, 537, 99, 23);
		contentPane.add(continueButton);

		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(6, 535, 75, 29);
		contentPane.add(btnStart);
		
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(93, 534, 75, 29);
		contentPane.add(btnStop);
		
		InstructionList instructions = new InstructionList("D:\\Workspace\\VirtualMachine-dev\\VirtualMachine\\object.txt");
		for (Instruction list : instructions.list) {
//			System.out.println(list.instructionName + " " + list.argument1 + " " + list.argument2);
//			list.readInstruction();
		}
		
		
		JTextPane txtpnTeste = new JTextPane();
		txtpnTeste.setText("");
		txtpnTeste.setBounds(20, 196, 437, 139);
		contentPane.add(txtpnTeste);
		
		String aux = new String();
		
		txtpnTeste.setText("Oba1");
		aux = txtpnTeste.getText();
		txtpnTeste.setText(aux + "\n" + "Oba2");
	}
}
