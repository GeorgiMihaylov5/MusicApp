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

import TableModels.SongTM;
import javax.swing.border.LineBorder;

import Database.DbConnection;
import Database.DbManager;
import Models.Song;
import Models.Artist;
import Models.Genre;
import Controls.ComboBoxItem;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;


public class SongFrame {

	public JFrame frame;
	private JTable table;
	private JButton btnNewButton_3;
	private JTextField titleField;
	private JTextField albumField;

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
		DbManager<Song> songManager = new DbManager<Song>(new DbConnection(), Song.tableName, Song::SetResultSetValues);
		DbManager<Artist> artistManager = new DbManager<Artist>(new DbConnection(), Artist.tableName, Artist::SetResultSetValues);
		DbManager<Genre> genreManager = new DbManager<Genre>(new DbConnection(), Genre.tableName, Genre::SetResultSetValues);
		SongTM songTM = new SongTM(songManager, artistManager, genreManager);
		
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
		
		JLabel lblNewLabel = new JLabel("SONGS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(10, 11, 190, 64);
		desktopPane.add(lblNewLabel);
		
		table = new JTable();
		

		table.setCellSelectionEnabled(true);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBounds(52, 110, 126, 209);
		
		table.setModel(songTM);
		
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
				songManager.Add(new Song());
				
				songTM.refresh(null);
			}
		});
		btnNewButton_2.setBounds(1535, 252, 130, 51);
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
					Song song = songTM.getAtRow(row);
					
					if(song != null ) {
						songManager.Delete(song.id);
						songTM.refresh(null);
					}
				}
			}		
		});
		btnNewButton_3.setBounds(1737, 252, 130, 51);
		desktopPane.add(btnNewButton_3);
		
		JComboBox<ComboBoxItem> artistComboBox = new JComboBox<ComboBoxItem>();
		artistComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int row = table.getSelectedRow();
				
				if(row != -1) {
					ComboBoxItem item = (ComboBoxItem)artistComboBox.getSelectedItem();
					table.setValueAt(item.getValue(), row, 1);
				}
			}
		});
		artistComboBox.setToolTipText("Artist ComboBox");
		artistComboBox.setBounds(1631, 418, 236, 37);
		desktopPane.add(artistComboBox);
		
		List<Artist> artists = artistManager.GetAll();
		
		for (int i = 0; i < artists.size(); i++) {
			Artist item = artists.get(i);
			artistComboBox.addItem(new ComboBoxItem(item.id, item.artistName));
		}
		
		JComboBox<ComboBoxItem> genreComboBox = new JComboBox<ComboBoxItem>();
		genreComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int row = table.getSelectedRow();
				
				if(row != -1) {
					ComboBoxItem item = (ComboBoxItem)genreComboBox.getSelectedItem();
					table.setValueAt(item.getValue(), row, 3);
				}
			}
		});
		genreComboBox.setToolTipText("Genre ComboBox");
		genreComboBox.setBounds(1631, 506, 236, 37);
		desktopPane.add(genreComboBox);
		
		List<Genre> genres = genreManager.GetAll();
		
		for (int i = 0; i < genres.size(); i++) {
			Genre item = genres.get(i);
			genreComboBox.addItem(new ComboBoxItem(item.id, item.genreName));
		}
		
		JLabel lblNewLabel_2 = new JLabel("Artists");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(1535, 418, 70, 37);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Genres");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(1535, 501, 70, 37);
		desktopPane.add(lblNewLabel_3);
		
		titleField = new JTextField();
		titleField.setBounds(201, 128, 86, 20);
		desktopPane.add(titleField);
		titleField.setColumns(10);
		
		albumField = new JTextField();
		albumField.setBounds(342, 128, 86, 20);
		desktopPane.add(albumField);
		albumField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> map = new HashMap<>();
				map.put("Title",titleField.getText());
				map.put("Album",albumField.getText());
				
				List<Song> songs = songManager.Search(map);
				
				songTM.refresh(songs);
			}
		});
		searchBtn.setBounds(486, 103, 139, 45);
		desktopPane.add(searchBtn);
		
		JLabel lblNewLabel_4 = new JLabel("Title");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_4.setBounds(223, 103, 47, 14);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Album");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_5.setBounds(355, 103, 56, 14);
		desktopPane.add(lblNewLabel_5);
		
		
		
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
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable source = (JTable)e.getSource();
	            int row = source.rowAtPoint( e.getPoint() );

	            Song song = songTM.getAtRow(row);
	            boolean artistFlag = false;
	            boolean genreFlag = false;
	            		
	            for (int i = 0; i < artists.size(); i++) {
	        		Artist item = artists.get(i);
	        			
	        		if (item.id == song.artistId) {
	        			artistComboBox.setSelectedIndex(i);
	        			artistFlag = true;
	        			break;
	        		}
	        	}
	            
	            if (!artistFlag) {
	            	artistComboBox.setSelectedIndex(-1);
	            }
	            	
	            for (int i = 0; i < genres.size(); i++) {
	        		Genre item = genres.get(i);
	        			
	        		if (item.id == song.genreId) {
	        			genreComboBox.setSelectedIndex(i);
	        			genreFlag = true;
	        			break;
	        		}	        			
	        	}
	            
	            if(!genreFlag) {
	            	genreComboBox.setSelectedIndex(-1);
	            }    
			}
		});
	
	}
}
