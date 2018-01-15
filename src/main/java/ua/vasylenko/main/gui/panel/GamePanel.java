package ua.vasylenko.main.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import ua.vasylenko.main.RootLogger;
import ua.vasylenko.main.gui.game_element.Star;
import ua.vasylenko.main.gui.game_element.Witch;

/**
* Main game panel.
* @Created by Asus on 14.01.2018
* @version 1.0
*/
public class GamePanel extends BasicPanel{
	
	private final int MAX_SECONDS = 15;
	private int currentSeconds = 0;
	private int countStar = 12;
	
	private int panelWith = 668;
	private int panelHeight = 365;
	
	private Witch witch; 
	private Star star;
	
	private String backgroundImagePath;
	private String backgroundImageName;
	
	private boolean isSquare;
	private Timer timerUp;
	
	private ArrayList<Star> starsList = new ArrayList<Star>();
    private Random randomLocationStars = new Random();
   
	private JFrame gameFrame;
	
	public GamePanel(JFrame gameFrame, String backgroundImagePath, String backgroundImageName) throws NullPointerException, IOException{  
		super(backgroundImagePath, backgroundImageName);
		this.gameFrame = gameFrame;
		this.backgroundImageName = backgroundImageName;
		this.backgroundImagePath = backgroundImagePath;
		
		setFocusable(true); 
		
		witch = new Witch(getBackgroundImage());
		populateStars(countStar);
        
		TimerUpTask timerUpTask = new TimerUpTask();
		timerUp = new Timer();
		timerUp.schedule(timerUpTask, 1000, 1000);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				// "Searching" particular star and get it.
			    for (Star s : starsList) { 
			    	star = (Star) s; 
			    	if (star.isSquare(evt.getX(), evt.getY())) { 
			    		isSquare = true;
			    		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
			    		break;
			    	}else {
			    		isSquare = false;
			    	}
		        }  
			}
			
			public void mouseReleased(MouseEvent evt) {
				// Release star and check.
				setCursor(Cursor.getDefaultCursor());	
				if(checkPanelBounds(evt.getX(), evt.getY()) && isSquare) { 
					star.setCoordinateStar(evt.getX(), evt.getY()); 
					if(star.checkEntry(witch))
						removeStar(star);
					repaint();
				}else {
					isSquare = false;
				}

			}
		});
		 
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) { 
				// Change star location.
				if(checkPanelBounds(evt.getX(), evt.getY()) && isSquare) { 
					star.setCoordinateStar(evt.getX(), evt.getY()); 
					repaint();
				}
			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// Change Witch location.
				try {
					witch.moveWitch(evt);
				} catch (NullPointerException e) { 
					RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
				} catch (IOException e) {
					RootLogger.getRootLogger().warn("IOException: " + e.getMessage()); 		
				}	
				repaint();		
			}
		});
			 
	}

	private boolean checkPanelBounds(int currentX, int currentY) {
		if(currentX >= 0 && currentY >= 0  && currentX < panelWith && currentY < panelHeight )  
			return true;
		return false;
	}
	
	private void populateStars(int countStars) throws NullPointerException, IOException {
		for (int j = 0; j < countStars; j++) 
            addStar(panelWith, panelHeight/2); 
	}
	
	public void addStar(int maxX, int maxY) throws NullPointerException, IOException {
        starsList.add(new Star(randomLocationStars.nextInt(maxX), randomLocationStars.nextInt(maxY)));
        //repaint();
    }
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		witch.draw(g); 
		starsList.forEach(s-> s.draw(g));
	
		drawTimer(g);
		drawScore(g);
	}
	
	private void drawScore(Graphics g) {
		g.setColor(new Color(220, 241, 250));
		g.setFont(new Font("Verdana", Font.BOLD, 18));
		g.drawString("Stars: " + String.valueOf(countStar), 10, 40);
	}
	
	private void drawTimer(Graphics g) {
		g.setColor(new Color(220, 241, 250));
		g.setFont(new Font("Verdana", Font.BOLD, 18));
		g.drawString("Timer: " + String.valueOf(currentSeconds +"/"+MAX_SECONDS), 10, 20);
	}
	
	/**
	 * Remove star from scene.
	 * @param star current Star.
	 */
	private void removeStar(Star star) {
		for(int i=0; i<starsList.size(); i++) {
			if(star.equals(starsList.get(i)) ) { 
				starsList.remove(star);
				starsList.trimToSize();
				
				if(--countStar == 0) {
					timerUp.cancel();
					repaint();
					
					int confirmDialogResult =  getConfirmDialogResult("WIN!", "You WIN! Start new game?");
					switch(confirmDialogResult) {
						case 0:
							try {
								newGame();
							} catch (NullPointerException e) { 
								RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
							} catch (IOException e) {
								RootLogger.getRootLogger().warn("IOException: " + e.getMessage()); 		
							}	
							break;
						case 1:
							System.exit(0);
							break;
					}
				}
				break;
			} 
		}
	}
	
	private void newGame() throws NullPointerException, IOException {
		gameFrame.getContentPane().removeAll();
		gameFrame.getContentPane().add(new GamePanel(gameFrame, backgroundImagePath, backgroundImageName), BorderLayout.CENTER);
		gameFrame.revalidate();
	}
	
	private int getConfirmDialogResult(String title, String message) {
		int i = JOptionPane.showConfirmDialog(this,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
		
		return i;
	}
	
	class TimerUpTask extends TimerTask {
		public void run() { 
			if (++currentSeconds >= MAX_SECONDS && countStar>0) {
				timerUp.cancel();
				repaint();
				
				int confirmDialogResult =  getConfirmDialogResult("GAME OVER!", "You LOSE! Start new game?");
				switch(confirmDialogResult) {
					case 0:
						try {
							newGame();
						} catch (NullPointerException e) { 
							RootLogger.getRootLogger().warn("NullPointerException: " + e.getMessage());
						} catch (IOException e) {
							RootLogger.getRootLogger().warn("IOException: " + e.getMessage()); 		
						}	
						break;
					case 1:
						System.exit(0);
						break;
				}
			}
			repaint();
		}
	}
	 
}
