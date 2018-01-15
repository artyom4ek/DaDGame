package ua.vasylenko.main.gui.game_element;

import java.awt.event.KeyEvent;
import java.io.IOException;

/**
* Moveable interface for Witch class.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public interface Moveable {
	void moveWitch(KeyEvent evt) throws NullPointerException, IOException;
	int getCurrentWitchX();
	int getCurrentWitchY();
	int getWitchHeight();
	int getWitchWidth();
}
