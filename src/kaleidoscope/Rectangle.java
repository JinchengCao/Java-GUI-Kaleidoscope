package kaleidoscope;

import java.awt.Color;

public class Rectangle extends Piece {
	int width ;
	int height ;
	int xDelta ;
	int yDelta ;

	public Rectangle(int xPosIn, int yPosIn, int widthIn, int heightIn, Color colorIn ) {
		super(xPosIn, yPosIn, colorIn);
		
		width = widthIn ;
		height = heightIn ;
		
	}
	public int getWidth() {
		return width ;
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

