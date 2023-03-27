package memorygame;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class mainMenu extends JFrame {
	
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
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
	public mainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("START");	
		btnNewButton.setBounds(126, 256, 207, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Instructions");
		btnNewButton_1.setBounds(10, 26, 154, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("easy");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(41, 146, 104, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("medium");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(171, 146, 107, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("hard");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setBounds(288, 146, 104, 29);
		contentPane.add(btnNewButton_4);
		btnNewButton_2.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	btnNewButton_2.setForeground(Color.white); // button text color
	            btnNewButton_2.setBackground(Color.red); // button background color
	            btnNewButton_3.setForeground(Color.black); // button text color
	            btnNewButton_3.setBackground(Color.white); // button background color
	            btnNewButton_4.setForeground(Color.black); // button text color
	            btnNewButton_4.setBackground(Color.white); // button background color
	            btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainFrame fr = new MainFrame(2);
						dispose();
						fr.setVisible(true);
					}
				});	
	        }

	    });
		btnNewButton_3.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	btnNewButton_2.setForeground(Color.black); // button text color
	            btnNewButton_2.setBackground(Color.white); // button background color
	            btnNewButton_3.setForeground(Color.white); // button text color
	            btnNewButton_3.setBackground(Color.red); // button background color
	            btnNewButton_4.setForeground(Color.black); // button text color
	            btnNewButton_4.setBackground(Color.white); // button background color
	            btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainFrame fr = new MainFrame(4);
						dispose();
						fr.setVisible(true);
					}
				});	
	        }

	    });
		btnNewButton_4.addMouseListener(new MouseAdapter(){
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	btnNewButton_2.setForeground(Color.black); // button text color
	            btnNewButton_2.setBackground(Color.white); // button background color
	            btnNewButton_3.setForeground(Color.black); // button text color
	            btnNewButton_3.setBackground(Color.white); // button background color
	            btnNewButton_4.setForeground(Color.white); // button text color
	            btnNewButton_4.setBackground(Color.red); // button background color
	            btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainFrame fr = new MainFrame(6);
						dispose();
						fr.setVisible(true);
					}
				});	
	        }
	    });
	}

}
