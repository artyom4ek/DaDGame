package ua.vasylenko.main.gui.panel;

import java.awt.Component; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
import ua.vasylenko.main.gui.GameFrame;
import ua.vasylenko.main.gui.button.BasicButton;

/**
* Panel class for main window.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class MainPanel extends BasicPanel {
	
	public MainPanel(String backgroundImagePath, String backgroundImageName){
		super(backgroundImagePath, backgroundImageName);
		
		JButton startGameButton = new BasicButton("Start game");
		startGameButton.setBounds(105, 145, 240, 50); 
		startGameButton.addActionListener((e) -> {
			Component component = (Component) e.getSource();
		    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		    frame.setVisible(false);
		        
		    GameFrame gameFrame = new GameFrame();
		    gameFrame.setVisible(true); 
		});
		
		JButton exitGameButton = new BasicButton("Exit");
		exitGameButton.setBounds(105, 200, 240, 50);
		exitGameButton.addActionListener((e) -> System.exit(0) );
		
		add(startGameButton);
		add(exitGameButton);
	}
}
