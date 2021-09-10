package entity;

public class UserSongPlaylist 
{
	private int userFk;
	private int songFk;
	private int periodFk;
	private int frequency;
	
	public UserSongPlaylist(int userFk, int songFk, int periodFk, int frequency)
	{
		setUserFk(userFk);
		setSongFk(songFk);
		setPeriodFk(periodFk);
		setFrequency(frequency);
	}
	public void setUserFk(int userFk)
	{
		this.userFk = userFk;
	}
	public void setSongFk(int songFk)
	{
		this.songFk = songFk;
	}
	public void setPeriodFk(int periodFk)
	{
		this.periodFk = periodFk;
	}
	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}
	public int getUserFk()
	{
		return userFk;
	}
	public int getSongFk()
	{
		return songFk;
	}
	public int getPeriodFk()
	{
		return periodFk;
	}
	public int getFrequency()
	{
		return frequency;
	}
}
