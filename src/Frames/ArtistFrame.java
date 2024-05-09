package Frames;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

import TableModels.ArtistTM;
import javax.swing.border.LineBorder;

import Database.DbConnection;
import Database.DbManager;
import Models.Artist;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;

public class ArtistFrame {

	public JFrame frame;
	private JTable table;
	private JButton btnNewButton_3;

	/**
	 * Create the application.
	 */
	public ArtistFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DbManager<Artist> dbManager = new DbManager<Artist>(new DbConnection(), Artist.tableName, Artist::SetResultSetValues);
		
		
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
		
		JLabel lblNewLabel = new JLabel("ARTISTS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(10, 11, 190, 64);
		desktopPane.add(lblNewLabel);
		
		table = new JTable();

		table.setCellSelectionEnabled(true);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBounds(52, 110, 126, 209);
		
		ArtistTM artistTM = new ArtistTM(dbManager);
		table.setModel(artistTM);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setLocation(201, 209);
		sp.setSize(1294, 499);
		
		desktopPane.add(sp);
		
		JButton btnNewButton_2 = new JButton("Add item");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbManager.Add(new Artist());
				
				artistTM.refresh(null);
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
					Artist genre = artistTM.getAtRow(row);
					
					if(genre != null ) {
						dbManager.Delete(genre.id);
						artistTM.refresh(null);
					}
				}
			}		
		});
		btnNewButton_3.setBounds(1649, 563, 130, 51);
		desktopPane.add(btnNewButton_3);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.WHITE);
		frame.getContentPane().add(desktopPane_1, BorderLayout.NORTH);
		
		JButton btnNewButton_1_1_1 = new JButton("Song");
		btnNewButton_1_1_1.setForeground(Color.BLACK);
		btnNewButton_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1_1_1.setBounds(1320, 883, 175, 51);
		desktopPane_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Genre");
		btnNewButton_1_2.setForeground(Color.BLACK);
		btnNewButton_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1_2.setBounds(201, 883, 175, 51);
		desktopPane_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_4 = new JButton("Artists");
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_4.setBounds(758, 883, 175, 51);
		desktopPane_1.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("GENRES");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(10, 11, 190, 64);
		desktopPane_1.add(lblNewLabel_1);
		
		JScrollPane sp_1 = new JScrollPane((Component) null);
		sp_1.setBounds(201, 209, 1294, 499);
		desktopPane_1.add(sp_1);
		
		JButton btnNewButton_2_1 = new JButton("Add item");
		btnNewButton_2_1.setForeground(Color.BLACK);
		btnNewButton_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_2_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_2_1.setBounds(1649, 336, 130, 51);
		desktopPane_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("Delete");
		btnNewButton_3_1.setForeground(Color.BLACK);
		btnNewButton_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_3_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_3_1.setBounds(1649, 563, 130, 51);
		desktopPane_1.add(btnNewButton_3_1);
		
		
		JTextField nameField = new JTextField();
		nameField.setBounds(201, 128, 86, 20);
		desktopPane.add(nameField);
		nameField.setColumns(10);
		
		JTextField countryField = new JTextField();
		countryField.setBounds(342, 128, 86, 20);
		desktopPane.add(countryField);
		countryField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> map = new HashMap<>();
				map.put("Name",nameField.getText());
				map.put("Country",countryField.getText());
				
				List<Artist> songs = dbManager.Search(map);
				
				artistTM.refresh(songs);
			}
		});
		searchBtn.setBounds(486, 103, 139, 45);
		desktopPane.add(searchBtn);
		
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_4.setBounds(223, 103, 47, 14);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("County");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_5.setBounds(355, 103, 73, 21);
		desktopPane.add(lblNewLabel_5);
	}
}
