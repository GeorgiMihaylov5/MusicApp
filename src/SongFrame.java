
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class SongFrame {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongFrame window = new SongFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SongFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1931, 1092);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Song");
		lblNewLabel.setBounds(51, 41, 46, 14);
		desktopPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Artists");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				ArtistFrame artistFrame = new ArtistFrame();
				artistFrame.frame.setVisible(true);

			}
		});
		btnNewButton.setBounds(10, 959, 89, 23);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Genre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				GenreFrame genreFrame = new GenreFrame();
				genreFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(152, 959, 89, 23);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Song");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				SongFrame songFrame = new SongFrame();
				songFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(293, 959, 89, 23);
		desktopPane.add(btnNewButton_1_1);
		
		table = new JTable();
		table.setBounds(72, 171, 127, 144);
		
		desktopPane.add(table);
	}
}
