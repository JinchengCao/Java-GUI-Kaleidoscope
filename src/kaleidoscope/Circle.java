package kaleidoscope;

import java.awt.Color;

  	public class Circle extends Piece {
	    	public int width ;
	    	public int height ;
	    	public int xDelta ;
	    	public int yDelta ;
	    	
	    	public Circle (int widthIn, int heightIn, int xPosIn, int yPosIn, Color color){
	    		super(xPosIn, yPosIn, color);
	    		width = widthIn ;
	    		height = heightIn ;
	    	}
	    	public int getWidth() {
	    		return width;
	    	}
	    	public int getHeight() {
	    		return height ;
	    	}
	    	public void setXSpeed(int speed) {
	    		xDelta = speed ;
	    	}
	    	public void setYSpeed(int speed) {
	    		yDelta = speed ;
	    	}
	    	public int getXSpeed() {
	    		return xDelta ;
	    	}
	    	public int getYSpeed() {
	    		return yDelta ;
	    	}
	    	public void updateX() {
	    		this.setX(this.getX() + xDelta);
	    	}
	    	public void updateY() {
	    		this.setY(this.getY() + yDelta);
	    	}
	   	}
