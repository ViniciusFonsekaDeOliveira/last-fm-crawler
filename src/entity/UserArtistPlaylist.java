package entity;

public class UserArtistPlaylist
{
	private int userFk;
	private int artistFk;
	private int periodFk;
	private int frequency;
	
	public UserArtistPlaylist(int userFk, int artistFk, int periodFk, int frequency)
	{
		setUserFk(userFk);
		setArtistFk(artistFk);
		setPeriodFk(periodFk);
		setFrequency(frequency);
	}
	public void setUserFk(int userFk)
	{
		this.userFk = userFk;
	}
	public void setArtistFk(int artistFk)
	{
		this.artistFk = artistFk;
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
	public int getArtistFk()
	{
		return artistFk;
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
