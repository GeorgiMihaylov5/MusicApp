import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Database.DbManager;
import Frames.ArtistFrame;

public class App {

	public static void main(String[] args) {
		String filePath = "src/SqlScripts/CreateDb.sql";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
           
            while (true) {
            	String line = reader.readLine();
            	
            	if(line == null) {
            		break;
            	}
            	
            	sb.append(line);
            }
            
            DbManager.Execute(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }	
		
		ArtistFrame artistFrame = new ArtistFrame();
		artistFrame.frame.setVisible(true);
	}

}
