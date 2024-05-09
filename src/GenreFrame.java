import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;

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
		desktopPane.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton_1_1 = new JButton("Song");
		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				SongFrame songFrame = new SongFrame();
				songFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(1320, 883, 175, 51);
		desktopPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("Genre");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				GenreFrame genreFrame = new GenreFrame();
				genreFrame.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(201, 883, 175, 51);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Artists");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				ArtistFrame artistFrame = new ArtistFrame();
				artistFrame.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(758, 883, 175, 51);
		desktopPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("GENRES");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(10, 11, 190, 64);
		desktopPane.add(lblNewLabel);
		
		table = new JTable();

		table.setCellSelectionEnabled(true);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBounds(52, 110, 126, 209);
		
		GenreTM genreTM = new GenreTM(dbManager);
		table.setModel(genreTM);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setLocation(201, 209);
		sp.setSize(1294, 499);
		
		desktopPane.add(sp);
		//frame.setSize(200, 500);
        // Frame Visible = true
		//frame.setVisible(true);
		
		//desktopPane.add(table);
		
		JButton btnNewButton_2 = new JButton("Add item");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbManager.Add(new Genre());
				
				genreTM.refresh(null);
			}
		});
		btnNewButton_2.setBounds(1649, 336, 130, 51);
		desktopPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_3.setBackground(UIManager.getColor("Button.background"));
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
		btnNewButton_3.setBounds(1649, 563, 130, 51);
		desktopPane.add(btnNewButton_3);
		
		
		//TODO Use parameters in sql queries
		//TODO Add search
	
	}
}
