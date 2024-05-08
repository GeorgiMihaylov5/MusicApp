import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArtistFrame {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistFrame window = new ArtistFrame();
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
	public ArtistFrame() {
		System.out.print(1);
		initialize();
		System.out.print(2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1935, 1096);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton_1_1 = new JButton("Song");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				SongFrame songFrame = new SongFrame();
				songFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(293, 963, 89, 23);
		desktopPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("Genre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				GenreFrame genreFrame = new GenreFrame();
				genreFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(152, 963, 89, 23);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Artists");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				ArtistFrame artistFrame = new ArtistFrame();
				artistFrame.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 963, 89, 23);
		desktopPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Artists");
		lblNewLabel.setBounds(43, 21, 46, 14);
		desktopPane.add(lblNewLabel);
	}

}
