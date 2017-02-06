import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameAdapter extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int MENU_STATE = 0;
	public static int PLAY_STATE = 1;
	public static int PAUSE_STATE = 3;
	public static int GAME_OVER_STATE = 2;
	
	protected int heigh;
	protected int width;
	protected String title;	
	protected Graphics batch;
	private UIHelper uiHelper;
	private InputHandler input;
	private long previousTime;
	public static int gameStats = MENU_STATE;
	
	private int difficulty = 2000;
	int rotation = 0;
	
	
	public GameAdapter(String title,int width,int heigh)
	{
		this.title = title;
		this.width = width;
		this.heigh = heigh;
		
		
		
		init();
	}
	
	public void init()
	{
		this.setTitle(title);
		this.setSize(new Dimension(heigh,width));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
			
		input = new InputHandler(this);
		
		uiHelper = new UIHelper(getGraphics());
		uiHelper.genStars(100);
		uiHelper.addEnemy(50, 50);
		
		previousTime = System.currentTimeMillis();
		
	}
	
	public void run()
	{		
		long time = 0;
		
		while(true)
		{
			time = System.currentTimeMillis();
			
			draw();
			
			time = 1000/100 - (System.currentTimeMillis() - time);
			
			if(time > 0)
			{				
				try {
					
					Thread.sleep(time);
					
				} catch (InterruptedException e) {
					
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void update()
	{
		handleInput();
		
		clearScreen(getGraphics());
		
		
	}
	
	public void draw()
	{
		
		update();
		
		this.batch = getGraphics();	
		
		uiHelper.DrawStar();
		
		if(gameStats == PLAY_STATE)
		{
			uiHelper.drawEnemy();
			uiHelper.drawPlayer();
			uiHelper.drawShots();
			uiHelper.drawPlayerEffect();
			uiHelper.drawBoard();
			uiHelper.drawBlow();
			
			System.out.println(uiHelper.shotmanager.getShots().size());
			
			if( System.currentTimeMillis() - previousTime > difficulty && UIHelper.ENEMIES_NBR < 6 )
			{
				previousTime = System.currentTimeMillis();
				
				uiHelper.genEnemy();
				uiHelper.moveEnemy();
		
			}
			
		}else if(gameStats == MENU_STATE){
			
			uiHelper.drawText(title, GameScreen.WIDTH/2 - 120, 200,25,Color.WHITE);
			uiHelper.drawText("Press ENTER to Play", GameScreen.WIDTH/2 - 100, 250, 18,Color.YELLOW);
			
		}else if(gameStats == PAUSE_STATE){
			
			uiHelper.drawText("Score: "+uiHelper.playerScore, (GameScreen.WIDTH / 2) - 40 , 65, 20,Color.WHITE);
			uiHelper.drawText(title, GameScreen.WIDTH/2 - 120, 200,25,Color.WHITE);
			uiHelper.drawText("Press ENTER to Play", GameScreen.WIDTH/2 - 100, 250, 18,Color.YELLOW);
			
		}else if(gameStats == GAME_OVER_STATE){
			
			uiHelper.drawText("Score: "+uiHelper.playerScore, (GameScreen.WIDTH / 2) - 50 , 65, 20,Color.WHITE);
			uiHelper.drawText("Game Over", GameScreen.WIDTH/2 - 90, 200,25,Color.RED);
			uiHelper.drawText("Press ENTER to Play Again", GameScreen.WIDTH/2 - 130, 250, 18,Color.YELLOW);
			
		}
		
	}
	
	public void clearScreen(Graphics batch)
	{
		batch.setColor(Color.BLACK);
		batch.fillRect(0, 0, heigh, width);
	}
	
	
	public void handleInput()
	{
		
		if(input.isKeyDown(KeyEvent.VK_LEFT)) {
			
			uiHelper.hero.moveLeft();
			
		}else if(input.isKeyDown(KeyEvent.VK_RIGHT))
		{
			uiHelper.hero.moveRight();
			
		}else if(input.isKeyDown(KeyEvent.VK_UP))
		{
			uiHelper.hero.moveUp();
			System.out.println("ok");
			
		}else if(input.isKeyDown(KeyEvent.VK_DOWN))
		{
			uiHelper.hero.moveDown();
			System.out.println("ok");
			
		}else if(input.isKeyDown(KeyEvent.VK_SPACE))
		{
			uiHelper.addShot();
			
		}else if(input.isKeyDown(KeyEvent.VK_ENTER))
		{
			
			if(gameStats == GAME_OVER_STATE)
			{
				uiHelper.playerScore = 0;
			}
			
			gameStats = PLAY_STATE;
			
		}else if(input.isKeyDown(KeyEvent.VK_ESCAPE))
		{
			gameStats = PAUSE_STATE;
		}
		
		
	}
	
	
}
