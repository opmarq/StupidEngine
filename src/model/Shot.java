package model;

public class Shot {

	private Position p1;
	private Position p2;
	
	public Shot(int x,int y)
	{
		setPosition(x, y);
	}
	
	public void setPosition(int x,int y)
	{
		p1 = new Position(x, y);
		p2 = new Position(x, y-10);
	}
	
	public void moveShot()
	{
		setPosition(getP1().x, getP1().y-5);
	}
	
	public boolean collision(Enemy enemy)
	{
		if( enemy.getFace().y > p2.y && enemy.getFace().x < p2.x && enemy.getFace().x + 25 > p2.x )
		{
			return true;
		}
		
		return false;
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
	
	
	
}
