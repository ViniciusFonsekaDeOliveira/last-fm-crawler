package entity;

public class Song 
{
	private String songMbId;
	private String songName = null;
	private int songListeners;
	private int artistFk;
	private int songUserPlaycount;
	
	public Song(String songMbId, String songName, int songListeners, int artistFk, int songUserPlaycount)
	{
		setSongMbId(songMbId);
		setSongName(songName);
		setSongListeners(songListeners);
		setArtistFk(artistFk);
		setSongUserPlaycount(songUserPlaycount);
	}
	public void setSongMbId(String songMbId)
	{
		this.songMbId = songMbId;
	}
	public void setSongName(String songName)
	{
		if(songName.length() >= 200)
		{
			System.out.println("Error: Nome da canção ultrapassa 200 caracteres.");
			return;
		}
		songName = songName.replaceAll("'", "''");
		songName = songName.trim();
		this.songName = songName;
	}
	public void setSongListeners(int songListeners)
	{
		this.songListeners = songListeners;
	}
	public void setArtistFk(int artistFk)
	{
		this.artistFk = artistFk;
	}
	public void setSongUserPlaycount(int songUserPlaycount)
	{
		this.songUserPlaycount = songUserPlaycount;
	}
	public String getSongMbId()
	{
		return songMbId;
	}
	public String getSongName()
	{
		return songName;
	}
	public int getSongListeners()
	{
		return songListeners;
	}
	public int getArtistFk()
	{
		return artistFk;
	}
	public int getSongUserPlaycount()
	{
		return songUserPlaycount;
	}
}
