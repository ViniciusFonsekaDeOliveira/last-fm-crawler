package collector;

import database.DAOIncubator;
import entity.Artist;
import entity.ArtistTag;
import entity.Song;
import entity.SongTag;
import entity.Tag;
import entity.User;
import entity.UserArtistPlaylist;
import entity.UserSongPlaylist;

public class RelationshipBuilder 
{
	public static final int OVERALL = 1;
	public static final int TWELVE_MONTHS = 2;
	public static final int SIX_MONTHS = 3;
	public static final int THREE_MONTHS = 4;
	public static final int WEEK = 5;
	public static final int LOVED_SONGS = 6;
	
	/** Constrói a relação entre os artistas e suas tags. */
	public static ArtistTag ARTIST_TAG(Artist myArtist, Tag myTag)
	{
		if(myArtist.getArtistName() == null || myTag.getTagName() == null)
			return null;
		DAOIncubator incubator = new DAOIncubator();
		int tagId = incubator.getTagId(myTag.getTagName());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		ArtistTag artistTag = new ArtistTag(artistId, tagId);
		return artistTag;
	}
	/** Contrói a relação entre as canções e suas tags */
	public static SongTag SONG_TAG(Song mySong, Tag myTag)
	{
		if(mySong.getSongName() == null || myTag.getTagName() == null)
			return null;
		DAOIncubator incubator = new DAOIncubator();
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		int tagId = incubator.getTagId(myTag.getTagName());
		SongTag songTag = new SongTag(songId, tagId);
		return songTag;
	}
	/** Constrói a relação entre os usuários e seus artistas */
	public static UserArtistPlaylist USERARTIST_OVERALL(User myUser, Artist myArtist)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, OVERALL, myArtist.getArtistUserPlaycount());
		return userArtist;
	}
	/** Constrói a relação entre os usuários e seus artistas */
	public static UserArtistPlaylist USERARTIST_TWELVEMONTHS(User myUser, Artist myArtist)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, TWELVE_MONTHS, myArtist.getArtistUserPlaycount());
		return userArtist;
	}
	/** Constrói a relação entre os usuários e seus artistas */
	public static UserArtistPlaylist USERARTIST_SIXMONTHS(User myUser, Artist myArtist)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, SIX_MONTHS, myArtist.getArtistUserPlaycount());
		return userArtist;
	}
	/** Constrói a relação entre os usuários e seus artistas */
	public static UserArtistPlaylist USERARTIST_THREEMONTHS(User myUser, Artist myArtist)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, THREE_MONTHS, myArtist.getArtistUserPlaycount());
		return userArtist;
	}
	/** Constrói a relação entre os usuários e seus artistas */
	public static UserArtistPlaylist USERARTIST_WEEK(User myUser, Artist myArtist)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int artistId = incubator.getArtistId(myArtist.getArtistName(), myArtist.getArtistMbId());
		UserArtistPlaylist userArtist = new UserArtistPlaylist(userId, artistId, WEEK, myArtist.getArtistUserPlaycount());
		return userArtist;
	}
	/** Constrói a relação entre usuários e canções */
	public static UserSongPlaylist USERSONG_OVERALL(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, OVERALL, mySong.getSongUserPlaycount());
		return userSong;
	}
	/** Constrói a relação entre usuários e canções */
	public static UserSongPlaylist USERSONG_TWELVEMONTHS(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, TWELVE_MONTHS, mySong.getSongUserPlaycount());
		return userSong;
	}
	/** Constrói a relação entre usuários e canções */
	public static UserSongPlaylist USERSONG_SIXMONTHS(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, SIX_MONTHS, mySong.getSongUserPlaycount());
		return userSong;
	}
	/** Constrói a relação entre usuários e canções */
	public static UserSongPlaylist USERSONG_THREEMONTHS(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, THREE_MONTHS, mySong.getSongUserPlaycount());
		return userSong;
	}
	/** Constrói a relação entre usuários e canções */
	public static UserSongPlaylist USERSONG_WEEK(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, WEEK, mySong.getSongUserPlaycount());
		return userSong;
	}
	/** Constrói a relação entre usuários e canções favoritadas*/
	public static UserSongPlaylist USERSONG_LOVEDSONGS(User myUser, Song mySong)
	{
		DAOIncubator incubator = new DAOIncubator();
		int userId = incubator.getUserId(myUser.getUserLogin());
		int songId = incubator.getSongId(mySong.getSongName(), mySong.getArtistFk());
		UserSongPlaylist userSong = new UserSongPlaylist(userId, songId, LOVED_SONGS, mySong.getSongUserPlaycount());
		return userSong;
	}
}
