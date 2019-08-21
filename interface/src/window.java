import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;

public class window extends JFrame {

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
					window frame = new window();
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
	public window() {
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
		
		instructionsTable = new JTable();
		instructionsTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		instructionsTable.setBounds(10, 22, 446, 335);
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
		inputField.setBounds(10, 498, 154, 25);
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
		continueButton.setBounds(213, 537, 89, 23);
		contentPane.add(continueButton);
	}
}
