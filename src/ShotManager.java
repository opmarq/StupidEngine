import java.util.ArrayList;

import model.Shot;

public class ShotManager {

	ArrayList<Shot> shots;
	
	public ShotManager()
	{
		shots = new ArrayList<>();
	}
	
	public void addShot(Shot shot)
	{
		shots.add(shot);
	}
	
	public ArrayList<Shot> getShots()
	{
		return shots;
	}
	
	public void removeShot(int pos)
	{
		shots.remove(pos);
	}
	
	public int indexOf(Shot shot)
	{
		return shots.indexOf(shot);
	}
}
