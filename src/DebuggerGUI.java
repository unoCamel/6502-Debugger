import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class DebuggerGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebuggerGUI frame = new DebuggerGUI();
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
	public DebuggerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 490);
		setTitle("6502 Debugger");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 20, 336, 284);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(354, 102, 90, 29);
		contentPane.add(btnNewButton);
		
		JButton btnStep = new JButton("Step");
		btnStep.setBounds(354, 20, 90, 29);
		contentPane.add(btnStep);
		
		JButton btnStepOver = new JButton("Step Over");
		btnStepOver.setBounds(354, 61, 90, 29);
		contentPane.add(btnStepOver);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(252, 316, 192, 146);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A=$00");
		lblNewLabel.setBounds(83, 40, 43, 16);
		panel.add(lblNewLabel);
		
		JLabel lblS = new JLabel("X=$00");
		lblS.setBounds(83, 68, 42, 16);
		panel.add(lblS);
		
		JLabel lblY = new JLabel("Y=$00");
		lblY.setBounds(83, 96, 42, 16);
		panel.add(lblY);
		
		JLabel lblRegisters = new JLabel("Registers:");
		lblRegisters.setBounds(9, 6, 85, 16);
		panel.add(lblRegisters);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(6, 316, 237, 146);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMemory = new JLabel("Memory:");
		lblMemory.setBounds(6, 6, 54, 16);
		panel_1.add(lblMemory);
	}
}
