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
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 20, 336, 284);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(354, 20, 90, 29);
		contentPane.add(btnNewButton);
		
		JButton btnStep = new JButton("Step");
		btnStep.setBounds(354, 61, 90, 29);
		contentPane.add(btnStep);
		
		JButton btnStepOver = new JButton("Step Over");
		btnStepOver.setBounds(354, 102, 90, 29);
		contentPane.add(btnStepOver);
		
		JLabel lblNewLabel = new JLabel("A=$00");
		lblNewLabel.setBounds(6, 321, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblS = new JLabel("X=$00");
		lblS.setBounds(6, 338, 61, 16);
		contentPane.add(lblS);
		
		JLabel lblY = new JLabel("Y=$00");
		lblY.setBounds(6, 356, 61, 16);
		contentPane.add(lblY);
	}
}
