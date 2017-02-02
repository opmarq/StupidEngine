import javax.swing.JFrame;

public class GameScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int HEIGHT = 500;
	public static int WIDTH = 600;
	public static int SURFACE = (HEIGHT * WIDTH) / 50;
	
	public static void main(String [] args )
	{

		GameAdapter gameAdapter = new GameAdapter("LosTInSpace V1.0", HEIGHT, WIDTH);
		
		Thread thread = new Thread(gameAdapter);
		thread.start();
	}
	

}
