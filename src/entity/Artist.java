package entity;

public class Artist 
{
	private String artistMbId;
	private String artistName = null;
	private int artistListeners;
	private int artistUserPlaycount = 0;
	

	public Artist(String artistMbId, String artistName, int artistListeners, int artistUserPlaycount)
	{
		setArtistMbId(artistMbId);
		setArtistName(artistName);
		setArtistListeners(artistListeners);
		setArtistUserPlaycount(artistUserPlaycount);
	}
	public void setArtistMbId(String artistMbId)
	{
		this.artistMbId = artistMbId;
	}
	public void setArtistName(String artistName)
	{
		if(artistName.length() >= 200)
		{
			System.out.println("Error: Nome do artista ultrapassa 200 caracteres.");
			return;
		}
		artistName = artistName.replaceAll("'", "''");
		artistName = artistName.trim();
		this.artistName = artistName;
	}
	public void setArtistListeners(int artistListeners)
	{
		this.artistListeners = artistListeners;
	}
	public void setArtistUserPlaycount(int artistUserPlaycount)
	{
		this.artistUserPlaycount = artistUserPlaycount;
	}
	public String getArtistMbId()
	{
		return artistMbId;
	}
	public String getArtistName()
	{
		return artistName;
	}
	public int getArtistListeners()
	{
		return artistListeners;
	}
	public int getArtistUserPlaycount()
	{
		return artistUserPlaycount;
	}
}
