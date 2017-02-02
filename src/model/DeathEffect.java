package model;

public class DeathEffect {

	
	private Position[][] deathEffect;
	private int x;
	private int y;
	int incPosition = 5;
	int countAnimation = 0;
	
	public DeathEffect(int x,int y)
	{
		this.x = x;
		this.y = y;
		
		init();
	}
	
	public void setPosition(int x,int y)
	{
		this.x = x;
		this.y = y;
		
		init();
	}
	
	public void init()
	{
		this.deathEffect = new Position[3][3];	
		
		for(int i=0;i<3;i++)
		{		
			for(int j=0;j<3;j++)
			{
				deathEffect[i][j] = new Position(x + ( incPosition * j ), y + (incPosition * i));			
			}
			
		}
	}
	
	public void animate()
	{

			deathEffect[0][0].x -= 1;
			deathEffect[0][0].y -= 1;
			
			deathEffect[0][1].y -= 1;
			
			deathEffect[0][2].x += 1;
			deathEffect[0][2].y -= 1;
			
			deathEffect[1][0].x -= 1;
			
			deathEffect[1][2].x += 1;
			
			deathEffect[2][0].x -= 1;
			deathEffect[2][0].y += 1;
			
			deathEffect[2][1].y += 1;
			
			deathEffect[2][2].x += 1;
			deathEffect[2][2].y += 1;
			
			incAnimation();
	
	}
	
	public void incAnimation()
	{
		this.countAnimation++;
	}
	
	public boolean collision(Player player)
	{
		
		int x = player.getP2().x;
		int y = player.getP1().y;
		int width = player.getP3().x;
		int height = player.getP2().y;
				
		for(int i=0;i<3;i++)
		{		
			for(int j=0;j<3;j++)
			{
				if( deathEffect[i][j].x > x && deathEffect[i][j].x < width && deathEffect[i][j].y > y && deathEffect[i][j].y < height )
				{
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public int getCountAnimation()
	{
		return this.countAnimation;
	}
	
	public Position[][] getDeathEffect()
	{
		return this.deathEffect;
	}
	
	public void setDeathEffect(Position pos,int x,int y)
	{
		deathEffect[pos.x][pos.y] = new Position(x, y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
