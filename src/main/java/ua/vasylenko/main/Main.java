package ua.vasylenko.main;

import java.awt.EventQueue; 

import ua.vasylenko.main.gui.BasicFrame;
import ua.vasylenko.main.gui.panel.MainPanel;

/**
* Main app window.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class Main extends BasicFrame {
	
	/** Main background image. */
	private final String MAIN_BACKGROUND_PATH = "images/background/";
	private final String MAIN_BACKGROUND_NAME = "main_bgd.jpg"; 
	
	public Main() {  
		add(new MainPanel(MAIN_BACKGROUND_PATH, MAIN_BACKGROUND_NAME));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Main frame = new Main();
				frame.setVisible(true);
			} catch (Exception e) {
				RootLogger.getRootLogger().error("App start error: " + e.getMessage());
				System.exit(1);
			}
		});
	}

}
