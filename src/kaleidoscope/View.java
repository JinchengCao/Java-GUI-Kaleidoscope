package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author Your name goes here
 * @author Your name goes here
 */
public class View extends JPanel implements Observer {
    
	Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.PINK} ;
	 
    /** This is what we will be observing. */
    Model model;
    Image img;
    int bgImageIndex = 0;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Model model) {
        this.model = model;
    }



    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
    		try {
    			img = ImageIO.read(new File(fileName(bgImageIndex)));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		g.drawImage(img, 0, 0, null);
         //g.setColor(Color.CYAN);
         //g.fillRect(0, 0, getWidth(), getHeight());
      
        for (int i = 0 ; i < model.circleArray.length ; i++) {
        	g.setColor(model.colorSource());//model.circleArray[i][0].getColor());
        	for (int j = 0; j < model.circleArray[i].length ; j++) {
    				g.fillOval(model.circleArray[i][j].getX(),
    						model.circleArray[i][j].getY(),
    						model.circleArray[i][j].getWidth(), model.circleArray[i][j].getHeight());
    		}
        }
    	
    	for (int i = 0 ; i < model.rectArray.length ; i++) {
    		//Color color = model.rectArray[i][0].getColor();
    			g.setColor(model.colorSource());//color);
    			for (int j = 0; j < model.rectArray[i].length ; j++) {
    				g.fillRect(model.rectArray[i][j].getX(), model.rectArray[i][j].getY(),
    						model.rectArray[i][j].getWidth(), model.rectArray[i][j].getHeight());
    			}
    	}
    }
    
    public String fileName(int i){
    	String[] names = {"a.jpg", "b.jpg", "c.jpg", "d.jpg"};
   	    if (i >= 4){
   		    i = i % 4;
      	}
    	return names[i];
    }
    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable obs, Object arg) {
        repaint();
    }
}
