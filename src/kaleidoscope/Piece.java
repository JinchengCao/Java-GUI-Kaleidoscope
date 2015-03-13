package kaleidoscope;

import java.awt.Color;

public class Piece extends Object{
	private int xPos ;
	private int yPos ;
	private Color color ;
	
	public Piece(int xPosIn, int yPosIn, Color colorIn)
	{
		this.xPos = xPosIn ;
		this.yPos = yPosIn ;
		this.color = colorIn ;
	}
	
	public Color getColor() {
		return color;
	}

	public int getX() {
		return xPos ;
	}
	
	public int getY() {
		return yPos ;
	}
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	public void setY(int yPos) {
		this.yPos = yPos;
	}
}

