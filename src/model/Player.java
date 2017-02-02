package model;

public class Player {

	private Position p1;
	private Position p2;
	private Position p3;
	
	public Player(int x,int y)
	{
		setPosition(x, y);
	}
	
	public void setPosition(int x,int y)
	{
		setP1(new Position(x, y));
		setP2(new Position(x-25,y+25));
		setP3(new Position(x+25, y+25));
	}
	
	public int getCenter()
	{
		return p1.x - 2 ;
	}
	
	public Position getPosition()
	{
		return p1;
	}
	
	public void moveLeft()
	{
		if(getP2().x-4 > 0)
		setPosition(getP1().x-4, getP1().y);
	}
	
	public void moveRight()
	{
		if(getP3().x-4 < 600 )
		setPosition(getP1().x+4, getP1().y);
	}
	
	public void moveUp()
	{
		if( getP1().y > 0 )
		setPosition(getP1().x, getP1().y-4);
	}
	
	public void moveDown()
	{
		if( getP2().y < 500 )
		setPosition(getP1().x, getP1().y+4);
	}
	
	public void rotateLeft(int angle)
	{
		
		int x = (int) ((Math.cos(angle) * getP1().x) + 300);
		int y = (int) ((Math.sin(angle) * getP1().y) + 300);
		
		setPosition(x, y);
 		
	}
	
	public Position getP1() {
		return p1;
	}

	public void setP1(Position p1) {
		this.p1 = p1;
	}

	public Position getP2() {
		return p2;
	}

	public void setP2(Position p2) {
		this.p2 = p2;
	}

	public Position getP3() {
		return p3;
	}

	public void setP3(Position p3) {
		this.p3 = p3;
	}
	
}
