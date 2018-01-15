package ua.vasylenko.main.gui.game_element;

/**
* Checkable interface for Star class.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public interface Checkable {
	boolean isSquare(int xF, int yF);
	boolean checkEntry(Witch witch);
}
