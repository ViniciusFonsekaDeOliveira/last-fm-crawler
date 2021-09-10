package collector;

import java.util.ArrayList;
import java.util.Collection;

import database.DAOIncubator;
import entity.Artist;
import entity.Song;
import de.umass.lastfm.Period;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;

public class SongsCollector 
{
	/** Coleta MbId, Nome, quantidade de ouvintes, vezes ouvidas, e artista, da canção de um usuário. Constrói a entidate Song. */
	public static Song mySongInfo(Track lastfmSong, String apiKey)
	{
		String songMbId = lastfmSong.getMbid();
		String songName = lastfmSong.getName();
		int artistFk = 0;
		String artistName = lastfmSong.getArtist();
		String artistMbId = lastfmSong.getArtistMbid();
		int songUserPlaycount = lastfmSong.getPlaycount();
		int songListeners = 1; //Alguém teve que ouvir aquela canção para ter isso como dado nesse banco
		try
		{
			if(Track.getInfo(artistName, songName, apiKey) != null)
			{
				songListeners = Track.getInfo(artistName, songName, apiKey).getListeners();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exceção disparada pela canção " + songName + " ao acessar Track.getInfo. " +e);
		}
		DAOIncubator incubator = new DAOIncubator();
		
		artistName = artistName.replaceAll("'", "''");
		artistFk = incubator.getArtistId(artistName, artistMbId);
		if(artistFk != 0)
		{
			Song song = new Song(songMbId, songName, songListeners, artistFk, songUserPlaycount);
			return song;
		}
		else
		{
			//Algumas vezes os top artists podem ser diferentes das top músicas. 
			//Caso não tenha achado o índice do artista da música no banco, deve-se inserí-lo.
			int artistListeners = 1;
			int artistUserPlaycount = 1;
			try
			{
				 artistListeners = de.umass.lastfm.Artist.getInfo(artistName, apiKey).getListeners();
				 artistUserPlaycount = de.umass.lastfm.Artist.getInfo(artistName, apiKey).getUserPlaycount();
				 Artist artist = new Artist(artistMbId, artistName, artistListeners, artistUserPlaycount);
				 incubator.insertArtist(artist);
				 artistFk = incubator.getArtistId(artistName, artistMbId);
				 Song song = new Song(songMbId, songName, songListeners, artistFk, songUserPlaycount);
				 return song;
			}
			catch(Exception e)
			{
				
				System.out.println("Error. " + e + " Exceção disparada pelo artista: " + artistName);
				Artist artist = new Artist(artistMbId, artistName, artistListeners, artistUserPlaycount);
				incubator.insertArtist(artist);
				artistFk = incubator.getArtistId(artistName, artistMbId);
				Song song = new Song(songMbId, songName, songListeners, artistFk, songUserPlaycount);
				return song;
			}
		}
	}
	/** Coleta canções de um usuário no periodo indicado. */
	public static Collection<Song> OVERALL(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserTopTracks = null;
		Collection<Song> myUserTopTracks = null;
		
		try
		{
			lastfmUserTopTracks = User.getTopTracks(userLogin, Period.OVERALL, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserTopTracks = new ArrayList<Song>();
		for(Track lastfmSong : lastfmUserTopTracks)
			myUserTopTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserTopTracks.clear();
		return myUserTopTracks;
	}
	/** Coleta canções de um usuário no periodo indicado. */
	public static Collection<Song> TWELVE_MONTHS(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserTopTracks = null;
		Collection<Song> myUserTopTracks = null;
		
		try
		{
			lastfmUserTopTracks = User.getTopTracks(userLogin, Period.TWELVE_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserTopTracks = new ArrayList<Song>();
		for(Track lastfmSong : lastfmUserTopTracks)
			myUserTopTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserTopTracks.clear();
		return myUserTopTracks;
	}
	/** Coleta canções de um usuário no periodo indicado. */
	public static Collection<Song> SIX_MONTHS(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserTopTracks = null;
		Collection<Song> myUserTopTracks = null;
		
		try
		{
			lastfmUserTopTracks = User.getTopTracks(userLogin, Period.SIX_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserTopTracks = new ArrayList<Song>();
		for(Track lastfmSong : lastfmUserTopTracks)
			myUserTopTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserTopTracks.clear();
		return myUserTopTracks;
	}
	/** Coleta canções de um usuário no periodo indicado. */
	public static Collection<Song> THREE_MONTHS(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserTopTracks = null;
		Collection<Song> myUserTopTracks = null;

		try
		{
			lastfmUserTopTracks = User.getTopTracks(userLogin, Period.THREE_MONTHS, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserTopTracks = new ArrayList<Song>();
		for(Track lastfmSong : lastfmUserTopTracks)
			myUserTopTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserTopTracks.clear();
		return myUserTopTracks;
	}
	/** Coleta canções de um usuário no periodo indicado. */
	public static Collection<Song> WEEK(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserTopTracks = null;
		Collection<Song> myUserTopTracks = null;
		
		try
		{
			lastfmUserTopTracks = User.getTopTracks(userLogin, Period.WEEK, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserTopTracks = new ArrayList<Song>();
		for(Track lastfmSong : lastfmUserTopTracks)
			myUserTopTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserTopTracks.clear();
		return myUserTopTracks;
	}
	/** Coleta canções favoritas de um usuário. */
	public static Collection<Song> LOVED_SONGS(String userLogin, String apiKey)
	{
		Collection<Track> lastfmUserLovedTracks = new ArrayList<Track>();
		Collection<Track> lovedTracksFromPageX = null;
		Collection<Song> myUserLovedTracks = null;
		int totalPages = 0;
		try
		{
			totalPages = User.getLovedTracks(userLogin, 0, apiKey).getTotalPages();
			for(int page = 1; page <= totalPages; page++)
			{
				lovedTracksFromPageX = User.getLovedTracks(userLogin, page, apiKey).getPageResults();
				if(lovedTracksFromPageX == null || lovedTracksFromPageX.size() == 0)
					break;
				lastfmUserLovedTracks.addAll(lovedTracksFromPageX);
				lovedTracksFromPageX.clear();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
			return null;
		}
		myUserLovedTracks = new ArrayList<Song>();
		
		if(lastfmUserLovedTracks == null ||lastfmUserLovedTracks.isEmpty())
			return null;
			
		for(Track lastfmSong : lastfmUserLovedTracks)
			myUserLovedTracks.add(mySongInfo(lastfmSong, apiKey));
		lastfmUserLovedTracks.clear();
		return myUserLovedTracks;
	}
}
