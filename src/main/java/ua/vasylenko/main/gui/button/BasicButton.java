package ua.vasylenko.main.gui.button;

import java.awt.Color;
import java.awt.Cursor; 
import java.awt.Font; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 
import javax.swing.JButton;

/**
* Custom button class for main window.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class BasicButton extends JButton {

	public BasicButton(String buttonText) {
		super(buttonText); 
		
		setFocusPainted(false);
		setBackground(new java.awt.Color(0, 0, 0));
		setFont(new Font("Times New Roman", Font.BOLD, 22));
		setForeground(new Color(100, 149, 237)); 
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}		
		});
			         
	}
 
}
