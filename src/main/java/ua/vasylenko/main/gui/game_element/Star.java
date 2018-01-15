package ua.vasylenko.main.gui.game_element;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image; 
import java.io.IOException; 

import ua.vasylenko.main.worker.image.ImageWorker;

/**
* Star class for game.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public class Star extends GameElement implements Checkable, Setable{
	
	/** Star image. */
	private final String STAR_IMAGE_PATH = "images/game/";
	private final String STAR_IMAGE_NAME = "star.png";  
	private Image starImage;
	
	/** Star location. */
	private int starX, starY;
	
	public Star(int starX, int starY) throws NullPointerException, IOException {
        this.starX = starX;
        this.starY = starY; 
        setImage(STAR_IMAGE_PATH, STAR_IMAGE_NAME);      
	}

	@Override
	public void draw(Graphics g) { 
		Graphics2D g2D = (Graphics2D) g;  
		g2D.drawImage(starImage, starX, starY, null);
    }
	
	@Override
	public void setImage(String imagePath, String imageName) throws NullPointerException, IOException {
		starImage = new ImageWorker().getImageFromPath(imagePath, imageName);	 
	}

	/**
     * Check coordinate square current star.
     * @param xF coordinate x.
     * @param yF coordinate y.
     * return boolean result.
     */
	public boolean isSquare(int xF, int yF) {
		if( 
			(xF >= starX) && (yF > starY) 
			&& (xF <= (starX+starImage.getWidth(null))) 
			&& (yF <= (starY+starImage.getHeight(null))) 
		)  
			return true;
		return false;	 
	}
	
	/**
     * Checking for entry Star to the region.
     * @param witch current area.
     * return boolean result.
     */
	public boolean checkEntry(Witch witch) {
		int witchWidth = witch.getWitchWidth();
		int witchHeight = witch.getWitchHeight();
		int currentWitchX = witch.getCurrentWitchX();
		int currentWitchY = witch.getCurrentWitchY();
		
		if( 
			(starX >= currentWitchX) && ((starX+starImage.getWidth(null)) <= currentWitchX + witchWidth)
									  &&
			(starY > currentWitchY)  && ((starY+starImage.getHeight(null)) <= currentWitchY + witchHeight)
		) 
			return true;
		return false;
	}
	
	/**
	 * Set the Star new coordinate.
	 * @param xNew new x.
	 * @param yNew new y.
	 */
	public void setCoordinateStar(int xNew, int yNew) {
		starX = xNew;
		starY = yNew;
	} 
		
}
