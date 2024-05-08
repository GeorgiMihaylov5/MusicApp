import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import TableModels.GenreTM;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import Database.DbConnection;
import Database.DbManager;
import Models.Genre;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GenreFrame {

	public JFrame frame;
	private JTable table;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenreFrame window = new GenreFrame();
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
	public GenreFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DbManager<Genre> dbManager = new DbManager<Genre>(new DbConnection(), Genre.tableName, Genre::SetResultSetValues);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1928, 1093);
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
		btnNewButton_1_1.setBounds(293, 961, 89, 23);
		desktopPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("Genre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				GenreFrame genreFrame = new GenreFrame();
				genreFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(152, 961, 89, 23);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Artists");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				ArtistFrame artistFrame = new ArtistFrame();
				artistFrame.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 961, 89, 23);
		desktopPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("genre");
		lblNewLabel.setBounds(32, 40, 46, 14);
		desktopPane.add(lblNewLabel);
		
		table = new JTable();

		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBounds(52, 110, 126, 209);
		
		GenreTM genreTM = new GenreTM(dbManager);
		table.setModel(genreTM);
		
		desktopPane.add(table);
		
		JButton btnNewButton_2 = new JButton("Add item");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbManager.Add(new Genre());
				
				genreTM.refresh(null);
			}
		});
		btnNewButton_2.setBounds(398, 193, 89, 23);
		desktopPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.isEditing()) {
					return;
				}
				
				int row = table.getSelectedRow();
				
				if(row != -1) {
					Genre genre = genreTM.getAtRow(row);
					
					if(genre != null ) {
						dbManager.Delete(genre.id);
						genreTM.refresh(null);
					}
				}
			}		
		});
		btnNewButton_3.setBounds(398, 240, 89, 23);
		desktopPane.add(btnNewButton_3);
		
		
		//TODO Add scroll bar if it is out of bounds
		//TODO Use parameters in sql queries
		//TODO Add column title
		//TODO Add search
	
	}
}
