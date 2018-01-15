package ua.vasylenko.main.gui.panel;

import java.awt.Graphics;
import java.awt.Image; 
import java.io.IOException; 
import javax.swing.JPanel; 

import ua.vasylenko.main.RootLogger;
import ua.vasylenko.main.worker.image.ImageWorker;

/**
* Basic panel class.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class BasicPanel extends JPanel {
	
	private Image backgroundImage; 

	public BasicPanel(String backgroundImagePath, String backgroundImageName){
		setLayout(null);  // Set absolute layout.
		try {
			backgroundImage = new ImageWorker().getImageFromPath(backgroundImagePath, backgroundImageName);
		} catch (NullPointerException e) { 
			RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
		} catch (IOException e) {
			RootLogger.getRootLogger().warn("IOException: " + e.getMessage()); 		
		}	 
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
	
	public Image getBackgroundImage( ) {
		return backgroundImage;
	}

}
