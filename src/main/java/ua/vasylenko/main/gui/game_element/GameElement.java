package ua.vasylenko.main.gui.game_element;

import java.awt.Graphics;
import java.io.IOException;

/**
* Abstract class for game elements.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public abstract class GameElement {

	protected abstract void setImage(String imagePath, String imageName) throws NullPointerException, IOException;
	protected abstract void draw(Graphics g);
	
}
