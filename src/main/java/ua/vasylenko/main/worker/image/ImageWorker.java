package ua.vasylenko.main.worker.image;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
* Image worker class for working with image.
* @Created by Asus on 13.01.2018
* @version 1.0
*/
public class ImageWorker {

	public Image getIconFromPath(String iconPath, String iconName) throws NullPointerException{
		URL iconURL = getClass().getClassLoader().getResource(iconPath + iconName); 
		return new ImageIcon(iconURL).getImage();
	}
	
	public Image getImageFromPath(String imagePath, String imageName) throws IOException, NullPointerException {
		URL imageURL = getClass().getClassLoader().getResource(imagePath+imageName);
	    return ImageIO.read(new File(imageURL.getPath()));
	}
	
	
}
