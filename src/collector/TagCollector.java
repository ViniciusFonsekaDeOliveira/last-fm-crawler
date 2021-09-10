package collector;

import java.util.ArrayList;
import java.util.Collection;

import database.DAOIncubator;
import entity.Artist;
import entity.Song;
import entity.Tag;
import de.umass.lastfm.Track;

public class TagCollector 
{
	/** Coleta as top tags de um artista */
	public static Collection<Tag> myArtistTopTags(Artist artist, String apiKey)
	{
		if(artist.getArtistName() == null)
			return null;
		Collection<de.umass.lastfm.Tag> lastfmArtistTopTags = null;
		try
		{
			lastfmArtistTopTags = de.umass.lastfm.Artist.getTopTags(artist.getArtistName(), apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			if(lastfmArtistTopTags == null || lastfmArtistTopTags.size() == 0)
				return null;
		}
		
		Collection<Tag> myArtistTopTags = new ArrayList<Tag>();
		int qtdeTags = 0;
		for(de.umass.lastfm.Tag lastfmTag : lastfmArtistTopTags)
		{
			if(qtdeTags == 5)
				break;
			Tag myTag = new Tag();
			myTag.setTagName(lastfmTag.getName());
			myArtistTopTags.add(myTag);
			qtdeTags++;
		}
		return myArtistTopTags;
	}
	/** Coleta as top tags de uma canção */
	public static Collection<Tag> mySongTopTags(Song song, String apiKey)
	{
		if(song.getSongName() == null)
			return null;
		Collection<de.umass.lastfm.Tag> lastfmSongTopTags = null;
		DAOIncubator incubator = new DAOIncubator();
		String artistName = incubator.getArtistName(song.getArtistFk());
		try
		{
			lastfmSongTopTags = Track.getTopTags(artistName, song.getSongName(), apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e);
			e.printStackTrace();
			if(lastfmSongTopTags == null || lastfmSongTopTags.size() == 0)
				return null;
		}
		Collection<Tag> mySongTopTags = new ArrayList<Tag>();
		int qtdeTags = 0;
		for(de.umass.lastfm.Tag lastfmTag : lastfmSongTopTags)
		{
			if(qtdeTags == 5)
				break;
			Tag myTag = new Tag();
			myTag.setTagName(lastfmTag.getName());
			mySongTopTags.add(myTag);
			qtdeTags++;
		}
		return mySongTopTags;
	}
}
