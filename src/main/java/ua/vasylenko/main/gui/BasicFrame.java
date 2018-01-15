package ua.vasylenko.main.gui;

import javax.swing.JFrame;

import ua.vasylenko.main.RootLogger;
import ua.vasylenko.main.worker.image.ImageWorker;

/**
* Basic frame class for set window base visual settings. 
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class BasicFrame extends JFrame {

	/** App icon. */
	private final String ICON_PATH = "images/icon/";
	private final String ICON_NAME = "app.png";
	
	public BasicFrame(){
		setTitle("Drag&Drop Game v1.0"); 
		
		try {
			setIconImage(new ImageWorker().getIconFromPath(ICON_PATH, ICON_NAME));
		} catch (NullPointerException e) { 
			RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
		} 
		
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
