package driver;

import java.util.ArrayList;
import java.util.Collection;

import collector.ArtistsCollector;
import collector.RelationshipBuilder;
import collector.SongsCollector;
import collector.TagCollector;
import collector.UserCollector;
import database.DAOIncubator;
import entity.Artist;
import entity.ArtistTag;
import entity.Song;
import entity.SongTag;
import entity.Tag;
import entity.User;
import entity.UserArtistPlaylist;
import entity.UserSongPlaylist;

public class Driver 
{
	/** Obtém dados relativos ao comportamento musical de um usuário da Last.Fm e os armazena num banco local. Retorna a fila de coleta atualizada. */
	public static ArrayList<String> Collector(String userLogin, ArrayList<String> mySeeds, String apiKey)
	{
		//Povoamento do banco local com os usuários coletados
		System.out.println("Coleta de usuário: " + userLogin );
		User  myUser = UserCollector.myUserInfo(userLogin, apiKey);
		DAOIncubator incubator = new DAOIncubator();
		incubator.insertUser(myUser);
		int userId = incubator.getUserId(userLogin);
		
		//Povoamento do banco local com os artistas top, coletados de um usuário durante os períodos indicados
		System.out.println("Coleta de artistas top Overall...");
		Collection<Artist> myUserTopArtists_1 = ArtistsCollector.OVERALL(userLogin, apiKey);
		System.out.println("Coleta de artistas top TwelveMonths...");
		Collection<Artist> myUserTopArtists_2 = ArtistsCollector.TWELVE_MONTHS(userLogin, apiKey);
		System.out.println("Coleta de artistas top SixMonths...");
		Collection<Artist> myUserTopArtists_3 = ArtistsCollector.SIX_MONTHS(userLogin, apiKey);
		System.out.println("Coleta de artistas top ThreeMonths...");
		Collection<Artist> myUserTopArtists_4 = ArtistsCollector.THREE_MONTHS(userLogin, apiKey);
		System.out.println("Coleta de artistas top Week...");
		Collection<Artist> myUserTopArtists_5 = ArtistsCollector.WEEK(userLogin, apiKey);
		Collection<Tag> myArtistTopTags = null;
		ArtistTag artistTag;
		if( myUserTopArtists_1 != null )
		{
			System.out.println("Coleta das top tags dos artistas Overall...");
			for(Artist artist : myUserTopArtists_1)
			{
				incubator.insertArtist(artist);
				//Povoamento do banco com relações entre fãs e artistas.
				int artistId = incubator.getArtistId(artist.getArtistName(), artist.getArtistMbId());
				UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, RelationshipBuilder.OVERALL, artist.getArtistUserPlaycount());
				incubator.insertUserArtistPlaylist(userArtist);
				//Povoamento do banco local com as tags dos artistas e com os relacionamentos entre eles
				myArtistTopTags = TagCollector.myArtistTopTags(artist, apiKey);
				if(myArtistTopTags == null || myArtistTopTags.size() == 0)
					continue;
				for(Tag tag : myArtistTopTags)
				{
					incubator.insertTag(tag);
					artistTag = RelationshipBuilder.ARTIST_TAG(artist, tag);
					incubator.insertArtistTag(artistTag);
				}
				myArtistTopTags.clear();
			}
		}
		if( myUserTopArtists_2 != null )
		{
			System.out.println("Coleta das top tags dos artistas TwelveMonths...");
			for(Artist artist : myUserTopArtists_2)
			{
				incubator.insertArtist(artist);
				//Povoamento do banco com relações entre fãs e artistas.
				int artistId = incubator.getArtistId(artist.getArtistName(), artist.getArtistMbId());
				UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, RelationshipBuilder.TWELVE_MONTHS, artist.getArtistUserPlaycount());
				incubator.insertUserArtistPlaylist(userArtist);
				//Povoamento do banco local com as tags dos artistas e com os relacionamentos entre eles
				myArtistTopTags = TagCollector.myArtistTopTags(artist, apiKey);
				if(myArtistTopTags == null || myArtistTopTags.size() == 0)
					continue;
				for(Tag tag : myArtistTopTags)
				{
					incubator.insertTag(tag);
					artistTag = RelationshipBuilder.ARTIST_TAG(artist, tag);
					incubator.insertArtistTag(artistTag);
				}
				myArtistTopTags.clear();
			}
		}
		if( myUserTopArtists_3 != null )
		{
			System.out.println("Coleta das top tags dos artistas SixMonths...");
			for(Artist artist : myUserTopArtists_3)
			{
				incubator.insertArtist(artist);
				//Povoamento do banco com relações entre fãs e artistas.
				int artistId = incubator.getArtistId(artist.getArtistName(), artist.getArtistMbId());
				UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, RelationshipBuilder.SIX_MONTHS, artist.getArtistUserPlaycount());
				incubator.insertUserArtistPlaylist(userArtist);
				//Povoamento do banco local com as tags dos artistas e com os relacionamentos entre eles
				myArtistTopTags = TagCollector.myArtistTopTags(artist, apiKey);
				if(myArtistTopTags == null || myArtistTopTags.size() == 0)
					continue;
				for(Tag tag : myArtistTopTags)
				{
					incubator.insertTag(tag);
					artistTag = RelationshipBuilder.ARTIST_TAG(artist, tag);
					incubator.insertArtistTag(artistTag);
				}
				myArtistTopTags.clear();
			}
		}
		if( myUserTopArtists_4 != null )
		{
			System.out.println("Coleta das top tags dos artistas ThreeMonths...");
			for(Artist artist : myUserTopArtists_4)
			{
				incubator.insertArtist(artist);
				//Povoamento do banco com relações entre fãs e artistas.
				int artistId = incubator.getArtistId(artist.getArtistName(), artist.getArtistMbId());
				UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, RelationshipBuilder.THREE_MONTHS, artist.getArtistUserPlaycount());
				/*Recoleta pode dar erro na frequencia. Verificar se relação já existe considerando tbm a frequencia de escuta.
				 * Se a relação de usuário + artista + periodo e frequencia já existirem retorna sem fazer nada. Atualmente a frequencia nao esta sendo considerada
				 * Isso faz com que uma NOVA coleta não mapeie corretamente o comportamento de escuta do usuário, uma vez que este se refere ao passado. */
				incubator.insertUserArtistPlaylist(userArtist);
				//Povoamento do banco local com as tags dos artistas e com os relacionamentos entre eles
				myArtistTopTags = TagCollector.myArtistTopTags(artist, apiKey);
				if(myArtistTopTags == null || myArtistTopTags.size() == 0)
					continue;
				for(Tag tag : myArtistTopTags)
				{
					incubator.insertTag(tag);
					artistTag = RelationshipBuilder.ARTIST_TAG(artist, tag);
					incubator.insertArtistTag(artistTag);
				}
				myArtistTopTags.clear();
			}
		}
		if( myUserTopArtists_5 != null )
		{
			System.out.println("Coleta das top tags dos artistas Week...");
			for(Artist artist : myUserTopArtists_5)
			{
				incubator.insertArtist(artist);
				//Povoamento do banco com relações entre fãs e artistas.
				int artistId = incubator.getArtistId(artist.getArtistName(), artist.getArtistMbId());
				UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, RelationshipBuilder.WEEK, artist.getArtistUserPlaycount());
				incubator.insertUserArtistPlaylist(userArtist);
				//Povoamento do banco local com as tags dos artistas e com os relacionamentos entre eles
				myArtistTopTags = TagCollector.myArtistTopTags(artist, apiKey);
				if(myArtistTopTags == null || myArtistTopTags.size() == 0)
					continue;
				for(Tag tag : myArtistTopTags)
				{
					incubator.insertTag(tag);
					artistTag = RelationshipBuilder.ARTIST_TAG(artist, tag);
					incubator.insertArtistTag(artistTag);
				}
				myArtistTopTags.clear();
			}
		}

		//Povoamento do banco local com canções, tags e suas relações coletadas de um usuário em um período
		System.out.println("Coleta das cancoes top Overall...");
		Collection<Song> myUserTopSongs_1 = SongsCollector.OVERALL(userLogin, apiKey);
		System.out.println("Coleta das cancoes top TwelveMonths...");
		Collection<Song> myUserTopSongs_2 = SongsCollector.TWELVE_MONTHS(userLogin, apiKey);
		System.out.println("Coleta das cancoes top SixMonths...");
		Collection<Song> myUserTopSongs_3 = SongsCollector.SIX_MONTHS(userLogin, apiKey);
		System.out.println("Coleta das cancoes top ThreeMonths...");
		Collection<Song> myUserTopSongs_4 = SongsCollector.THREE_MONTHS(userLogin, apiKey);
		System.out.println("Coleta das cancoes top Week...");
		Collection<Song> myUserTopSongs_5 = SongsCollector.WEEK(userLogin, apiKey);
		System.out.println("Coleta das cancoes Favoritas...");
		Collection<Song> myUserLovedSongs = SongsCollector.LOVED_SONGS(userLogin, apiKey);
		Collection<Tag> mySongTopTags = null;
		SongTag songTag;
		if(myUserTopSongs_1 != null)
		{
			System.out.println("Coleta das top tags das cancoes Overall...");
			for(Song song : myUserTopSongs_1)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.OVERALL, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		if(myUserTopSongs_2 != null)
		{
			System.out.println("Coleta das top tags das cancoes TwelveMonths...");
			for(Song song : myUserTopSongs_2)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.TWELVE_MONTHS, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		if(myUserTopSongs_3 != null)
		{
			System.out.println("Coleta das top tags das cancoes SixMonths...");
			for(Song song : myUserTopSongs_3)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.SIX_MONTHS, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		if(myUserTopSongs_4 != null)
		{
			System.out.println("Coleta das top tags das cancoes ThreeMonths...");
			for(Song song : myUserTopSongs_4)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.THREE_MONTHS, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		if(myUserTopSongs_5 != null)
		{
			System.out.println("Coleta das top tags das cancoes Week...");
			for(Song song : myUserTopSongs_5)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.WEEK, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		if(myUserLovedSongs != null)
		{
			System.out.println("Coleta das top tags das cancoes Favoritas...");
			for(Song song : myUserLovedSongs)
			{
				incubator.insertSong(song);
				//Povoamento do banco com relações entre usuário e canção
				int songId = incubator.getSongId(song.getSongName(), song.getArtistFk());
				UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, RelationshipBuilder.LOVED_SONGS, song.getSongUserPlaycount());
				incubator.insertUserSongPlaylist(userSong);
				//Tags
				mySongTopTags = TagCollector.mySongTopTags(song, apiKey);
				if(mySongTopTags == null || mySongTopTags.size() == 0)
					continue;
				for(Tag tag : mySongTopTags)
				{
					incubator.insertTag(tag);
					songTag = RelationshipBuilder.SONG_TAG(song, tag);
					incubator.insertSongTag(songTag);
				}
				mySongTopTags.clear();
			}
		}
		
		//mySeeds = CrawlerUserNeighbours(userLogin, apiKey, mySeeds);
		return mySeeds;
	}
	
	/** Coloca no final da fila de coleta os vizinhos do utilitário semente. */
	public static ArrayList<String> CrawlerUserNeighbours(String userLogin, String apiKey, ArrayList<String> mySeeds)
	{
		Collection<User> myUserNeighboursList = null;
		try
		{
			System.out.println("Coleta de vizinhos do usuário " + userLogin + "...");
			myUserNeighboursList = UserCollector.neighbours(userLogin, apiKey);
			if(myUserNeighboursList != null)
			{
				for(User user : myUserNeighboursList)
					mySeeds.add(user.getUserLogin());
				myUserNeighboursList.clear();
			}
		}
		catch(Exception e)
		{
			System.out.println("Error. " +e);
			e.printStackTrace();
		}
		return mySeeds;
	}
}
