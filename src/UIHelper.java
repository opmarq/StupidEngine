import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import model.DeathEffect;
import model.Enemy;
import model.Player;
import model.Position;
import model.Shot;


public class UIHelper {

	public ArrayList<Position> stars;
	
	private ArrayList<Enemy> enemies;
	
	public Player hero;
	
	ShotManager shotmanager;
	
	Graphics batch;
	
	Boolean[] effect;
	
	public int playerScore = 0;

	public ArrayList<DeathEffect> deathEffects;
	
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	
	public static int ENEMIES_NBR = 0;

	
	public UIHelper(Graphics batch)
	{
		stars = new ArrayList<>();
		
		enemies = new ArrayList<>();	
		
		hero = new Player(GameScreen.WIDTH/2, GameScreen.HEIGHT - 50);
		
		shotmanager = new ShotManager();
		
		this.batch = batch;
		
		effect = new Boolean[2];
		
		deathEffects = new ArrayList<>();

		for(int i=0;i<2;i++)
		{
			if(i % 2 == 0)
			{
				effect[i] = false;
				
			}else{
				
				effect[i] = true;
			}
		}
			
	}
	
	public void addShot()
	{
		shotmanager.addShot(new Shot(hero.getPosition().x, hero.getPosition().y));
	}
	
	public void genStars(int nbr)
	{
		
		for (int i = 0; i < nbr; i++) {
			
			int x = (int) (Math.random() * GameScreen.WIDTH);
			int y = (int) (Math.random() * GameScreen.HEIGHT);
			
			stars.add(new Position(x, y));		
		}

	}
	
	
	
	public void drawShots()
	{
		Iterator<Shot> it = shotmanager.getShots().iterator();
				
		while (it.hasNext())
		{
			
			Shot shot = (Shot) it.next();
			
			batch.drawLine(shot.getP1().x, shot.getP1().y, shot.getP2().x, shot.getP2().y);
			
			shot.moveShot();
			
			if(shot.getP1().y < 0)
			{
				it.remove();
			}
			
			// check collision between enemies and shots 
			
			for(int i=0;i < enemies.size();i++) {
				
				if(shot.collision(enemies.get(i)))
				{
					deathEffects.add(new DeathEffect(enemies.get(i).getFace().x, enemies.get(i).getFace().y));
					
					enemies.remove(i);
					
					playerScore++;
					
					ENEMIES_NBR--;
	
				}
				
			}
			
		}
		
	//	System.out.println(playerScore);
		
	}
	
	public void drawText(String text,int x,int y,int size,Color color)
	{
		batch.setColor(color);
		
		Font font = new Font("Minecraft", Font.PLAIN, size);
		batch.setFont(font);
		batch.drawString(text, x, y);
	}
	
	public  void DrawStar()
	{
		batch.setColor(Color.WHITE);
		
		for (Position pos : stars) {

			batch.drawLine(pos.x, pos.y, pos.x, pos.y);
		}
				
	}
	
	public void addEnemy(int x,int y)
	{

		
		enemies.add(new Enemy(x,y));
	}
	
	public void genEnemy()
	{
		
		int x = (int) (Math.random() * 400) + 50;
		int y = (int) (Math.random() * GameScreen.HEIGHT / 2) + 70 ;
		
		addEnemy(x, y);
	}
	
	
	public void drawEnemy()
	{
		
		for (Enemy enemy : enemies) {

			switch(enemy.getDirection())
			{
				case LEFT:
					enemy.moveXLeft();;
					break;
				case RIGHT:
					enemy.moveXRight();;
					break;
				default:
					break;	
			}
			
			
			batch.drawRect(enemy.getFace().x, enemy.getFace().y, 25, 25);
			batch.drawRect(enemy.getEye1().x, enemy.getEye1().y, 5, 5);
			batch.drawRect(enemy.getEye2().x, enemy.getEye2().y, 5, 5);
			batch.drawLine(enemy.getMouth1().x, enemy.getMouth1().y,enemy.getMouth2().x,enemy.getMouth2().y);	
		}
		
	}
	
	public void moveEnemy()
	{
		int direction = 0;
		
		for (Enemy enemy : enemies) {
		
			direction = (int) (Math.random() * 2);
			
			System.out.println(direction);
			
			switch(direction)
			{
				case LEFT:
					enemy.setDirection(LEFT);
					break;
				case RIGHT:
					enemy.setDirection(RIGHT);
					break;
				default:
					break;	
			}
			
		}
		
	}
	
	public void drawPlayer()
	{
		batch.drawLine(hero.getP2().x, hero.getP2().y, hero.getP1().x, hero.getP1().y);
		batch.drawLine(hero.getP3().x, hero.getP3().y, hero.getP1().x, hero.getP1().y);
		batch.drawLine(hero.getP3().x, hero.getP3().y,hero.getP2().x, hero.getP2().y);
		
	}
	
	public void drawPlayerEffect()
	{
		if(effect[0])
		{
			batch.drawLine(hero.getCenter(), hero.getP2().y + 3,hero.getCenter(), hero.getP2().y + 3);
			batch.drawLine(hero.getCenter()+3, hero.getP2().y + 3+3,hero.getCenter()+3, hero.getP2().y + 3+3);
			batch.drawLine(hero.getCenter()+4, hero.getP2().y + 3,hero.getCenter()+4, hero.getP2().y + 3);
			batch.drawLine(hero.getCenter()+4, hero.getP2().y + 3+3,hero.getCenter()+4, hero.getP2().y + 3+3);
			
		}else if(effect[1])
		{
			batch.drawLine(hero.getCenter()+4, hero.getP2().y + 3+4, hero.getCenter()+4, hero.getP2().y + 3+4);
			batch.drawLine(hero.getCenter()+6, hero.getP2().y + 3+6, hero.getCenter()+6, hero.getP2().y + 3+6);
			batch.drawLine(hero.getCenter(), hero.getP2().y + 3+4, hero.getCenter(), hero.getP2().y + 3+4);
			batch.drawLine(hero.getCenter()+4, hero.getP2().y + 3+6,hero.getCenter()+4, hero.getP2().y + 3+6);
			
		}
		
		for(int i=0;i<2;i++)
		{
			effect[i] = !effect[i];
		}


	}

	public void drawBoard() {
		
		drawText("Score: "+playerScore, (GameScreen.WIDTH / 2) - 40 , 65, 20,Color.WHITE);
		
	}

	public void drawBlow() {
		
		for (int k=0;k<deathEffects.size();k++) {
			
			for(int i=0;i<3;i++)
			{		
				for(int j=0;j<3;j++)
				{
					batch.drawRect(deathEffects.get(k).getDeathEffect()[i][j].x, deathEffects.get(k).getDeathEffect()[i][j].y, 5, 5);
				}
			}
			deathEffects.get(k).animate();
			
			if(deathEffects.get(k).collision(hero))
			{
				
				gameOver();
				
				break;
				
			}
			
			if(deathEffects.get(k).getCountAnimation() > 400)
			{
				deathEffects.remove(k);
			}	

		}
	}

	private void gameOver() {
		
		GameAdapter.gameStats = GameAdapter.GAME_OVER_STATE;
		enemies.clear();
		deathEffects.clear();
	}
	 
}
