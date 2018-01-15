package ua.vasylenko.main.gui.game_element;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent; 
import java.io.IOException; 

import ua.vasylenko.main.worker.image.ImageWorker;

/**
* Witch class for game.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public class Witch extends GameElement implements Moveable{
	
	/** Witch image. */
	private final String WITCH_IMAGE_PATH = "images/game/";
	private final String WITCH_IMAGE_LEFT = "witch_left.png";
	private final String WITCH_IMAGE_RIGHT = "witch_right.png";
	
	private final int WITCH_SPEED = 7;

	private int witchX, witchY, currentWitchX;
	private Image backgroundImage, witchImage;
	
	public Witch(Image backgroundImage) throws NullPointerException, IOException { 
		setImage(WITCH_IMAGE_PATH, WITCH_IMAGE_LEFT);
		this.backgroundImage = backgroundImage;
		this.witchX = getWitchX();
		this.witchY = getWitchY(); 
		this.currentWitchX = witchX;
	}

	private int getWitchX() {
		return backgroundImage.getWidth(null) - witchImage.getWidth(null);
	}
	
	private int getWitchY() {
		return backgroundImage.getHeight(null) - witchImage.getHeight(null);
	}
	
	@Override
	public void draw(Graphics g) { 
		Graphics2D g2D = (Graphics2D) g; 
		g2D.drawImage(witchImage, witchX, witchY, null);
    }
	
	@Override
	public void setImage(String imagePath, String imageName) throws NullPointerException, IOException {
		witchImage = new ImageWorker().getImageFromPath(imagePath, imageName);	 
	}
	
	public int getWitchWidth() {
		return witchImage.getWidth(null);
	}
	
	public int getWitchHeight() {
		return witchImage.getHeight(null);
	}
	
	public int getCurrentWitchX() {
		return currentWitchX;
	}
	
	public int getCurrentWitchY() {
		return witchY;
	}
	
	/**
     * Change move Witch by keyboard.
     * @param evt keyboard event. 
     */
	public void moveWitch(KeyEvent evt) throws NullPointerException, IOException {
		switch (evt.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(currentWitchX > 0) {
					setImage(WITCH_IMAGE_PATH, WITCH_IMAGE_LEFT);
					witchX -= WITCH_SPEED;
					currentWitchX = witchX;
				}else {
					witchX = 0;
				}
				break;
			case KeyEvent.VK_RIGHT: 
				if(currentWitchX <= getWitchX()) { 
					setImage(WITCH_IMAGE_PATH, WITCH_IMAGE_RIGHT);
					witchX += WITCH_SPEED;
					currentWitchX = witchX;
				}
				break;
		}
	}
}
