package collector;

import java.util.ArrayList;
import java.util.Collection;

import entity.Artist;
import de.umass.lastfm.Period;
import de.umass.lastfm.User;


public class ArtistsCollector 
{	
	/** Obtém MbId, nome e quantidade de ouvintes referentes a um artista específico da Last.Fm. Constrói a entidade Artist. */
	public static Artist myArtistInfo(de.umass.lastfm.Artist lastfmArtist, String apiKey)
	{
		String artistMbId = lastfmArtist.getMbid();
		String artistName = lastfmArtist.getName();
		int artistListeners = 1;
		try
		{
			artistListeners = de.umass.lastfm.Artist.getInfo(artistName, apiKey).getListeners();
		}
		catch (Exception e)
		{
			System.out.println("Exceção lançada ao tentar pegar quantidade de fãs para o artista: " +artistName+"\n"+e);
		}
		int artistUserPlaycount = lastfmArtist.getPlaycount();
		Artist artist = new Artist(artistMbId, artistName, artistListeners, artistUserPlaycount);
		return artist;
	}
	/** Obtém a lista de artistas ouvida por um usuário específico no período indicado. */
	public static Collection<Artist> OVERALL(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.Artist> lastfmUserTopArtists = null;
		Collection<Artist> myUserTopArtists = new ArrayList<Artist>();
		try
		{	
			lastfmUserTopArtists = User.getTopArtists(userLogin, Period.OVERALL, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
		if(lastfmUserTopArtists != null)
		{
			for(de.umass.lastfm.Artist lastfmArtist : lastfmUserTopArtists)
			{
				Artist artist = myArtistInfo(lastfmArtist, apiKey);
				myUserTopArtists.add(artist);
			}
			lastfmUserTopArtists.clear();
		}
		return myUserTopArtists;
	}
	/** Obtém a lista de artistas ouvida por um usuário específico no período indicado. */
	public static Collection<Artist> TWELVE_MONTHS(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.Artist> lastfmUserTopArtists = null;
		Collection<Artist> myUserTopArtists = new ArrayList<Artist>();
		try
		{	
			lastfmUserTopArtists = User.getTopArtists(userLogin, Period.TWELVE_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
		if(lastfmUserTopArtists != null)
		{
			for(de.umass.lastfm.Artist lastfmArtist : lastfmUserTopArtists)
			{
				Artist artist = myArtistInfo(lastfmArtist, apiKey);
				myUserTopArtists.add(artist);
			}
			lastfmUserTopArtists.clear();
		}
		return myUserTopArtists;
	}
	/** Obtém a lista de artistas ouvida por um usuário específico no período indicado. */
	public static Collection<Artist> SIX_MONTHS(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.Artist> lastfmUserTopArtists = null;
		Collection<Artist> myUserTopArtists = new ArrayList<Artist>();
		try
		{	
			lastfmUserTopArtists = User.getTopArtists(userLogin, Period.SIX_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
		if(lastfmUserTopArtists != null)
		{
			for(de.umass.lastfm.Artist lastfmArtist : lastfmUserTopArtists)
			{
				Artist artist = myArtistInfo(lastfmArtist, apiKey);
				myUserTopArtists.add(artist);
			}
			lastfmUserTopArtists.clear();
		}
		return myUserTopArtists;
	}
	/** Obtém a lista de artistas ouvida por um usuário específico no período indicado. */
	public static Collection<Artist> THREE_MONTHS(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.Artist> lastfmUserTopArtists = null;
		Collection<Artist> myUserTopArtists = new ArrayList<Artist>();
		try
		{	
			lastfmUserTopArtists = User.getTopArtists(userLogin, Period.THREE_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
		if(lastfmUserTopArtists != null)
		{
			for(de.umass.lastfm.Artist lastfmArtist : lastfmUserTopArtists)
			{
				Artist artist = myArtistInfo(lastfmArtist, apiKey);
				myUserTopArtists.add(artist);
			}
			lastfmUserTopArtists.clear();
		}
		return myUserTopArtists;
	}
	/** Obtém a lista de artistas ouvida por um usuário específico no período indicado. */
	public static Collection<Artist> WEEK(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.Artist> lastfmUserTopArtists = null;
		Collection<Artist> myUserTopArtists = new ArrayList<Artist>();
		try
		{	
			lastfmUserTopArtists = User.getTopArtists(userLogin, Period.WEEK, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error.");
			e.printStackTrace();
		}
		if(lastfmUserTopArtists != null)
		{
			for(de.umass.lastfm.Artist lastfmArtist : lastfmUserTopArtists)
			{
				Artist artist = myArtistInfo(lastfmArtist, apiKey);
				myUserTopArtists.add(artist);
			}
			lastfmUserTopArtists.clear();
		}
		return myUserTopArtists;
	}
}
