package model;

public class Enemy {
	
	private Position face;
	private Position eye1;
	private Position eye2;
	private Position mouth1;
	private Position mouth2;
	private Position velocity;
	private int direction;
	
	public Enemy(int x,int y)
	{
		setPosition(x, y);
		velocity = new Position(1, 1);
	}
	
	public void setPosition(int x,int y)
	{
		setFace(new Position(x, y));
		setEye1(new Position(x+5, y+5));
		setEye2(new Position(x+15, y+5));
		setMouth1(new Position(x+5, y+15));
		setMouth2(new Position(x+20, y+15));
		
	}
	
	public void moveXRight()
	{
		
		if(face.x + 25 < 600)
		{
			face.x += velocity.x;
			eye1.x += velocity.x;
			eye2.x += velocity.x;
			mouth1.x += velocity.x;
			mouth2.x += velocity.x;
		}else{
			
			direction = 0;
		}


	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int dir)
	{
		this.direction = dir;
	}
	
	public void moveXLeft()
	{
		if( face.x > 0 )
		{
			face.x -= velocity.x;
			eye1.x -= velocity.x;
			eye2.x -= velocity.x;
			mouth1.x -= velocity.x;
			mouth2.x -= velocity.x;
		}else{
			
			direction = 1;
			
		}
	}
	
	public void moveYUp()
	{
		
		if( face.y > 0 )
		{
			face.y -= velocity.y;
			eye1.y -= velocity.y;
			eye2.y -= velocity.y;
			mouth1.y -= velocity.y;
			mouth2.y -= velocity.y;
		}
	}
	
	public void moveYDown()
	{
		
		if( face.y + 25 < 500 )
		{
			face.y += velocity.y;
			eye1.y += velocity.y;
			eye2.y += velocity.y;
			mouth1.y += velocity.y;
			mouth2.y += velocity.y;
		}

	}
	
	public Position getVelocity() {
		return velocity;
	}

	public void setVelocity(Position velocity) {
		this.velocity = velocity;
	}

	public Position getFace() {
		return face;
	}
	public void setFace(Position face) {
		this.face = face;
	}
	public Position getEye1() {
		return eye1;
	}
	public void setEye1(Position eye1) {
		this.eye1 = eye1;
	}
	public Position getEye2() {
		return eye2;
	}
	public void setEye2(Position eye2) {
		this.eye2 = eye2;
	}
	public Position getMouth1() {
		return mouth1;
	}
	public void setMouth1(Position mouth1) {
		this.mouth1 = mouth1;
	}
	public Position getMouth2() {
		return mouth2;
	}
	public void setMouth2(Position mouth2) {
		this.mouth2 = mouth2;
	}
}
