package entity;

public class ArtistTag 
{
	private int artistFk;
	private int tagFk;
	
	public ArtistTag(int artistFk, int tagFk)
	{
		setArtistFk(artistFk);
		setTagFk(tagFk);
	}
	public void setArtistFk(int artistFk)
	{
		this.artistFk = artistFk;
	}
	public void setTagFk(int tagFk)
	{
		this.tagFk = tagFk;
	}
	public int getArtistFk()
	{
		return artistFk;
	}
	public int getTagFk()
	{
		return tagFk;
	}
}
