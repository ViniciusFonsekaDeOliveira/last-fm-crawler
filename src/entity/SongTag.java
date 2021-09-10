package entity;

public class SongTag 
{
	private int songFk;
	private int tagFk;
	
	public SongTag(int songFk, int tagFk)
	{
		setSongFk(songFk);
		setTagFk(tagFk);
	}
	public void setSongFk(int songFk)
	{
		this.songFk = songFk;
	}
	public void setTagFk(int tagFk)
	{
		this.tagFk = tagFk;
	}
	public int getSongFk()
	{
		return songFk;
	}
	public int getTagFk()
	{
		return tagFk;
	}
}
