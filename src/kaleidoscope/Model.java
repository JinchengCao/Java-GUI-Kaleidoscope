package kaleidoscope;

import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

import kaleidoscope.Piece;

public class Model extends Observable {
	public Circle[][] circleArray = new Circle[4][8];
	public Rectangle[][] rectArray = new Rectangle[2][8];
    public int yLimit ;
    public int xLimit ;
    private Timer timer;
    public int colorIndex = -1;
    
    public void setCircles() {
    	
    	Circle [] circle1 = addCircle(60,30,30,120,Color.BLUE) ;
    	Circle [] circle2 = addCircle(60,20,45,50,Color.RED) ;
    	Circle [] circle3 = addCircle(40,40,80,130,Color.GREEN);
    	Circle [] circle4 = addCircle(30,30,90,50,Color.DARK_GRAY);
    	circleArray[0] = circle1 ;
    	circleArray[1] = circle2 ;
    	circleArray[2] = circle3 ;
    	circleArray[3] = circle4 ;
    }
    
    public void setRectangles() {
    	Rectangle[] rect1 = addRect(65,10,60,30,Color.MAGENTA);
    	Rectangle[] rect2 = addRect(20,20,50,25,Color.ORANGE);
    	rectArray[0] = rect1 ;
    	rectArray[1] = rect2 ;
    }
    
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }
    
    public Color colorSource(){
    	Color[] colorArray ={Color.BLUE, Color.RED, Color.GREEN, Color.DARK_GRAY, 
    			Color.MAGENTA, Color.ORANGE}; 
   	    if (colorIndex >= 5){
   		    colorIndex = -1;
      	}
   	    colorIndex = colorIndex + 1;
    	return colorArray[colorIndex];
    }
       
    
//    private int[] makeEightSpots(int xPos, int yPos) {
//		int[] spotsArray = {xPos, yPos, xLimit - xPos,yPos, xPos, yLimit - yPos,
//				xLimit - xPos, yLimit - yPos, 
//				xLimit/2 + yLimit/2 - yPos, xLimit/2 + yLimit/2 - xPos,
//				xLimit/2 - yLimit/2 + yPos, xLimit/2 + yLimit/2 - xPos,
//				xLimit/2 + yLimit/2 - yPos, yLimit/2 + xPos - xLimit/2,
//				xLimit/2 - yLimit/2 + yPos, yLimit/2 + xPos - xLimit/2};
	  private int[] makeEightXSpots(int xPos, int yPos, int width, int height) {
			int[] spotsArray = {xPos, xLimit - xPos - width, xPos, 
						xLimit - xPos-width,  xLimit - yPos - height, 
						yPos, yPos, xLimit - yPos - height};

    	return spotsArray;
    }
	  private int[] makeEightYSpots(int xPos, int yPos, int width, int height) {
			int[] spotsArray = { yPos, yPos,  yLimit - yPos - height,
					 yLimit - yPos - height, 
					 yLimit - xPos - width,
					 yLimit - xPos - width,
					 xPos, xPos} ;
			return spotsArray ;
	  }
    public Circle[] addCircle(int width, int height, int xPos, int yPos, Color color) {
    	Circle[] circles = new Circle[8] ;
    		
    	int[] eightXSpots = makeEightXSpots(xPos, yPos, width, height);
    	int[] eightYSpots = makeEightYSpots(xPos, yPos, width, height);  
    	
    	for (int i = 0 ; i < eightXSpots.length/2 ; i++) {
    		Circle newCircle = new Circle(width, height, eightXSpots[i], eightYSpots[i], color);
    		circles[i] = newCircle ;

    	}
    	for (int i = eightXSpots.length/2 ;i < eightXSpots.length ; i++) {
    		Circle newCircle = new Circle(height, width, eightXSpots[i], eightYSpots[i], color);
    		circles[i] = newCircle ;

    	}
    	for (int i = 0 ; i < circles.length ; i++){
    		if (circles[i].getX() < xLimit/2){
    			circles[i].setXSpeed(-2);
    		} else {
    			circles[i].setXSpeed(2);
    		}
    		if (circles[i].getY() < yLimit/2) {
    			circles[i].setYSpeed(-4);
    		} else {
    			circles[i].setYSpeed(4);
    		}
    		
    	}
    	return circles ;
    }
 
public Rectangle[] addRect(int xPosIn, int yPosIn, int widthIn, int heightIn, Color color) {
    	Rectangle[] rects = new Rectangle[8] ;
    	int [] eightXSpots = makeEightXSpots(xPosIn, yPosIn, widthIn, heightIn);
    	int [] eightYSpots = makeEightYSpots(xPosIn, yPosIn, widthIn, heightIn);
    	
    	for (int i = 0 ; i < eightXSpots.length/2 ; i++ ) {
    		Rectangle newRect = new Rectangle(eightXSpots[i], eightYSpots[i], widthIn, heightIn, color) ;
    		rects[i] = newRect ;
    	}
    	for (int i = eightXSpots.length/2 ; i < eightXSpots.length ; i++ ) {
    		Rectangle newRect = new Rectangle(eightXSpots[i], eightYSpots[i], heightIn, widthIn, color) ;
    		rects[i] = newRect ;
    	}
    	for (int i = 0; i < rects.length ; i++) {
    		if (rects[i].getX() < xLimit/2){
    			rects[i].setXSpeed(-2);
    		} else {
    			rects[i].setXSpeed(2);
    		}
    		if (rects[i].getY() < yLimit/2) {
    			rects[i].setYSpeed(-4);
    		} else {
    			rects[i].setYSpeed(4);
    		}
    		}
    	return rects ;
    }
     
    /**
     * Tells the objects to start moving. This is done by starting a Timer
     * that periodically executes several TimerTasks. The TimerTasks tell
     * different objects to move one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new moveCircle(0),  0, 40); // 25 times a second   
        timer.schedule(new moveCircle(1),  0, 20);
        timer.schedule(new moveCircle(2),  0, 30);
        timer.schedule(new moveCircle(3),  0, 50);
        timer.schedule(new moveRect(0), 0, 20);
        timer.schedule(new moveRect(1), 0, 30);
	}
    
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    /**
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStepCircles(int circ) {
    	
    		
    	int newX = circleArray[circ][0].getX() + circleArray[circ][0].getXSpeed() ;
    	int newY = circleArray[circ][0].getY() + circleArray[circ][0].getYSpeed() ;
    	
    	if ((newX < 0 ) || (newX + circleArray[circ][0].getWidth() > xLimit) ){
    		circleArray[circ][0].setXSpeed(-circleArray[circ][0].getXSpeed()) ;
    	}
    	if ((newY <= 0 ) || (newY + circleArray[circ][0].getHeight() >= yLimit) ){
       		circleArray[circ][0].setYSpeed(-circleArray[circ][0].getYSpeed());
    	}
    	circleArray[circ][0].setX(circleArray[circ][0].getX() + circleArray[circ][0].getXSpeed());
    	circleArray[circ][0].setY(circleArray[circ][0].getY() + circleArray[circ][0].getYSpeed());
    	
    	
    	
//    	//if (circleArray[circ][0].getX() < 0 || circleArray[circ][0].getX() >= xLimit) {
//    		//			circleArray[circ][0].setXSpeed(-circleArray[circ][0].getXSpeed());
//       			}
//    		//	for (int j = 0 ;j < circleArray[circ].length ; j++ ) {
//    		//		circleArray[circ][j].setX(circleArray[circ][j].getX() + circleArray[circ][j].getXSpeed()); 
//       		//	}
//   			circleArray[circ][0].setY( circleArray[circ][0].getY() + circleArray[circ][0].getYSpeed());
//    			if (circleArray[circ][0].getY() < 0 || circleArray[circ][0].getY() >= yLimit) {
//    					circleArray[circ][0].setYSpeed(-circleArray[circ][0].getYSpeed());
//    			}
//    			//for (int j = 0 ;j < circleArray[circ].length ; j++ ) {
//        	//		circleArray[circ][j].setY(circleArray[circ][j].getY() + circleArray[circ][j].getYSpeed()); 
//    		//	}
//    		circleArray[circ][0].setX(circleArray[circ][0].getX() + circleArray[circ][0].getXSpeed());
//    		circleArray[circ][0].setY( circleArray[circ][0].getY() + circleArray[circ][0].getYSpeed());
        		
			int[] updatedEightXSpots = makeEightXSpots(circleArray[circ][0].getX(), circleArray[circ][0].getY(),
					circleArray[circ][0].getWidth(), circleArray[circ][0].getHeight());
			int[] updatedEightYSpots = makeEightYSpots(circleArray[circ][0].getX(), circleArray[circ][0].getY(),
					circleArray[circ][0].getWidth(), circleArray[circ][0].getHeight());
			
			for (int j = 0 ; j < circleArray[circ].length ; j++){
            circleArray[circ][j].setX(updatedEightXSpots[j]); // - circleArray[circ][j].getWidth()/2);
            circleArray[circ][j].setY(updatedEightYSpots[j]); // - circleArray[circ][j].getHeight()/2);
            }

			
		
        // Notify observers
        setChanged();
        notifyObservers();
    }
    public void makeOneStepRects(int rect) {

    	int newX = rectArray[rect][0].getX() + rectArray[rect][0].getXSpeed() ;
    	int newY = rectArray[rect][0].getY() + rectArray[rect][0].getYSpeed() ;

    	if ((newX < 0 ) || (newX + rectArray[rect][0].getWidth() > xLimit) ){
    		for (int j = 0 ; j < rectArray[rect].length ; j++) {
    			rectArray[rect][j].setXSpeed(-rectArray[rect][j].getXSpeed());
    	}}
       	if ((newY <= 0 ) || (newY + rectArray[rect][0].getHeight() >= yLimit) ){
       		for (int j = 0 ; j < rectArray[rect].length ; j++) {
       			rectArray[rect][j].setYSpeed(-rectArray[rect][j].getYSpeed());
    	}}
//       	for (int i = 0 ; i < rectArray[rect].length ; i++) {
//       		rectArray[rect][i].updateX();
//       		rectArray[rect][i].updateY();
//       	}
//       
       	rectArray[rect][0].updateX();
       	rectArray[rect][0].updateY();
		int[] updatedEightXSpots = makeEightXSpots(rectArray[rect][0].getX(), rectArray[rect][0].getY(),
				rectArray[rect][0].getWidth(), rectArray[rect][0].getHeight());
		int[] updatedEightYSpots = makeEightYSpots(rectArray[rect][0].getX(), rectArray[rect][0].getY(),
				rectArray[rect][0].getWidth(), rectArray[rect][0].getHeight());
		
		for (int j = 0 ; j < rectArray[rect].length ; j++){
        rectArray[rect][j].setX(updatedEightXSpots[j]); // - rectArray[rect][j].getWidth()/2);
        rectArray[rect][j].setY(updatedEightYSpots[j]); // - rectArray[rect][j].getHeight()/2);
    	}



    	// Notify observers
    	setChanged();
    	notifyObservers();
    }
    


    private class moveCircle extends TimerTask {
    	int circ;
    	public moveCircle(int circ) {
    		this.circ = circ;
    	}
    	@Override
    	public void run() {
    		makeOneStepCircles(circ);
    	}
    }

    private class moveRect extends TimerTask {
    	int rect;
    	public moveRect(int rect) {
    		this.rect = rect;
    	}
    	@Override
    	public void run() {
    		makeOneStepRects(rect);
    	}
    }
}

