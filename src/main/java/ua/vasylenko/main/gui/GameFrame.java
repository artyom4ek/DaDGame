package ua.vasylenko.main.gui;

import java.io.IOException;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane; 

import ua.vasylenko.main.RootLogger;
import ua.vasylenko.main.gui.panel.GamePanel; 

/**
* Game frame class for game scene. 
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class GameFrame extends BasicFrame {
	
	/** Game background image. */
	private final String GAME_BACKGROUND_PATH= "images/background/";
	private final String GAME_BACKGROUND_NAME = "game_bgd.jpg";
	
	public GameFrame(){  
		setBounds(100, 100, 703, 443);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		try {
			getContentPane().add(
				new GamePanel(this, GAME_BACKGROUND_PATH, GAME_BACKGROUND_NAME), 
				BorderLayout.CENTER
			);
		} catch (NullPointerException e) { 
			RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
		} catch (IOException e) {
			RootLogger.getRootLogger().warn("IOException: " + e.getMessage()); 		
		}	
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener((e) -> System.exit(0) );
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener((e) -> 
			JOptionPane.showMessageDialog(GameFrame.this, "For Faifly...", "About", 1) 
		);
		mnHelp.add(mntmAbout);
	}

}
