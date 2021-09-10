package database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Artist;
import entity.ArtistTag;
import entity.Song;
import entity.SongTag;
import entity.Tag;
import entity.User;
import entity.UserArtistPlaylist;
import entity.UserSongPlaylist;


public class DAOIncubator 
{
	private Connection connection = null;
    ResultSet rs = null;
    
    /** Obtém conexão com o banco */
    public DAOIncubator()
    {
    	connection = DBPostgres.getConnection();
    }
    /** Fecha conexão com o banco */
    public void CloseConnection()
    {
    	try 
    	{
			this.connection.close();
		} 
    	catch (SQLException e)
    	{
    		System.out.println("Error. "+e);
			e.printStackTrace();
		}
    }
    /** Insere artista no banco */
    public void insertArtist(Artist artist)
    {
    	//Objeto artista não pode ser construído por completo por causa de dado sujo.
    	if(artist.getArtistName() == null)
    		return;
    	//Artista já existente no banco
    	if( getArtistId(artist.getArtistName(), artist.getArtistMbId()) != 0 )
    		return;
    	String sql = "Insert into Artist(ArtistMbId, ArtistName, ArtistListeners) values('"+artist.getArtistMbId()+"','"+artist.getArtistName()+"',"+artist.getArtistListeners()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }    
    /** Pesquisa e retorna o Id do artista no banco. */
    public int getArtistId(String artistName, String artistMbId)
    {
    	int artistId = 0;
    
    	String sql = "Select ArtistId from Artist where ArtistName = '"+artistName+"' and ArtistMbId = '"+artistMbId+"'";
    	try
    	{
			Statement stm = this.connection.createStatement();
			//System.out.println("Artista: " + artistName);
			rs = stm.executeQuery(sql);
			if(rs.next())
				artistId = rs.getInt("ArtistId");
			rs.close();
			
		}
    	catch (SQLException e) 
    	{
    		System.out.println("Error." +e);
			e.printStackTrace();
		}
    	return artistId;
    }
    /** Pesquisa e retorna o nome do artista através de seu Id */
    public String getArtistName(int artistId) 
    {
    	String artistName = null;
    	String sql = "Select ArtistName from Artist where ArtistId = "+artistId+"";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			artistName = rs.getString("ArtistName");
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return artistName;
    }
    /** Insere canção no banco */
    public void insertSong(Song song)
    {
    	if(song.getSongName() == null)
    		return;
    	//Canção já existente no banco.
    	if( getSongId(song.getSongName(), song.getArtistFk()) != 0 )
    		return;
    	if( song.getArtistFk() == 0 )
    		return;
    	String sql = "Insert into Song (SongMbId, SongName, SongListeners, ArtistFk) values ('"+song.getSongMbId()+"', '"+song.getSongName()+"', "+song.getSongListeners()+", "+song.getArtistFk()+" )";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Pesquisa e retorna o Id da canção através do nome e artista dela */
    public int getSongId(String songName, int artistId)
    {
    	if(songName == null)
    		return 0;
    	int songId = 0;
    	String sql = "Select SongId from Song where SongName = '"+songName+"' and ArtistFk = "+artistId+"";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			songId = rs.getInt("SongId");
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return songId;
    }
    /** Insere tag no banco */
    public void insertTag(Tag tag)
    {
    	if(tag.getTagName() == null)
    		return;
    	//Tag já existente no banco
    	if( getTagId(tag.getTagName()) != 0 )
    		return;
    	
    	String sql = "Insert into Tag (TagName) values ('"+tag.getTagName()+"')";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Pesquisa e retorna o Id de uma tag a partir do nome dela */
    public int getTagId(String TagName) //Fechar conexão com o banco??
    {
    	int tagId = 0;
    	String sql = "Select TagId from Tag where TagName = '"+TagName+"'";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			tagId = rs.getInt("TagId");
    		rs.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return tagId;
    }
    /** Insere um relacionamento entre usuário e artista no banco num período de tempo específico */
    public void insertUserArtistPlaylist(UserArtistPlaylist userArtist)
    {
    	if(userArtist.getArtistFk() == 0 || userArtist.getUserFk() == 0)
    		return;
    	if(containsRelationshipUserArtist(userArtist)) //Se já existe, teria que alterar o valor da frequência antiga, para o valor atual;
    		return;
    	String sql = "Insert into UserArtistPlaylist (UserFk, ArtistFk, PeriodFk, Frequency) values ("+userArtist.getUserFk()+","+userArtist.getArtistFk()+","+userArtist.getPeriodFk()+","+userArtist.getFrequency()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Verifica existência de relacionamento entre usuário, artista e período */
    public boolean containsRelationshipUserArtist(UserArtistPlaylist userArtist)
    {
    	boolean found = false;
    	String sql = "Select * from UserArtistPlaylist where UserFk = "+userArtist.getUserFk()+" and ArtistFk = "+userArtist.getArtistFk()+" and PeriodFk = "+userArtist.getPeriodFk()+"";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			found = true;
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return found;
    }
    /** Insere um relacionamento entre usuário e canção no banco num período de tempo específico */
    public void insertUserSongPlaylist(UserSongPlaylist userSong)
    {
    	if(userSong.getUserFk() == 0 || userSong.getSongFk() == 0)
    		return;
    	if(containsRelationshipUserSong(userSong))
    		return;
    	String sql = "Insert into UserSongPlaylist (UserFk, SongFk, PeriodFk, Frequency) values ("+userSong.getUserFk()+","+userSong.getSongFk()+","+userSong.getPeriodFk()+","+userSong.getFrequency()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Verifica existência de relacionamento entre usuário, música e período */
    public boolean containsRelationshipUserSong(UserSongPlaylist userSong)
    {
    	boolean found = false;
    	
    	/*Pesquisar com a frequencia - a relação ja existe com frequencia diferente
    	 * 							 - a relação ainda nao existe 
    	 * Caso 1 - alterar a frequencia que existe pela nova!! 
    	 * Caso 2 - inserir 
    	 * 
    	 * Obs.: Se eu pesquisar com a frequencia vai sempre dar que não existe relação, dado que a freq se atualiza muito rapido.
    	 * Solução. Pesquisar sem a frequencia mesmo. Porém levando em conta a frequencia caso a relação já exista.
    	 * */
    	String sql = "Select * from UserSongPlaylist where UserFk = "+userSong.getUserFk()+" and SongFk = "+userSong.getSongFk()+" and PeriodFk = "+userSong.getPeriodFk()+" ";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			found = true;
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return found;
    }
    /** Insere relacionamentos entre artista e suas tags no banco */
    public void insertArtistTag(ArtistTag artistTag)
    {
    	if(artistTag == null)
    		return;
    	if(artistTag.getArtistFk() == 0 || artistTag.getTagFk() == 0 )
    		return;
    	if(containsRelationshipArtistTag(artistTag))
    		return;
    	String sql = "Insert into ArtistTag(ArtistFk, TagFk) values("+artistTag.getArtistFk()+","+artistTag.getTagFk()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Insere relacionamentos entre musica e suas tags no banco */
    public void insertSongTag(SongTag songTag)
    {
    	if(songTag == null)
    		return;
    	if(songTag.getSongFk() == 0 || songTag.getTagFk() == 0)
    		return;
    	if(containsRelationshipSongTag(songTag))
    		return;
    	String sql = "Insert into SongTag (SongFk, TagFk) values ("+songTag.getSongFk()+","+songTag.getTagFk()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		stm.executeUpdate(sql);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    }
    /** Verifica existência de relacionamento entre música e tag */
    public boolean containsRelationshipSongTag(SongTag songTag)
    {
    	boolean found = false;
    	String sql = "Select * from SongTag where songfk = "+songTag.getSongFk()+" and tagfk = "+songTag.getTagFk()+"";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			found = true;
    		rs.close();
    	}	
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return found;
    }
    /** Verifica existência de relacionamento entre artista e tag */
    public boolean containsRelationshipArtistTag(ArtistTag artistTag)
    {
    	boolean found = false;
    
    	String sql = "Select * from ArtistTag where artistfk = "+artistTag.getArtistFk()+" and tagfk = "+artistTag.getTagFk()+"";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			found = true;
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return found;
    }
    /** Insere usuário no banco */
    public boolean insertUser(User user)
    {
    	if( getUserId(user.getUserLogin()) != 0 )
    	{
    		System.out.println("\nUsuário redundante impedido!");
    		return false;
    	}
    	String sql = "Insert into Users (UserLastFmId, UserLogin, UserRealName, UserAge, UserCountry, UserGender, UserPlaycount)"
    			   + "values ('"+user.getUserLastFmId()+"', '"+user.getUserLogin()+"', '"+user.getUserRealName()+"', "+user.getUserAge()+", '"+user.getUserCountry()+"', '"+user.getUserGender()+"', "+user.getUserPlaycount()+")";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		System.out.println(sql);
    		stm.executeUpdate(sql);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return true;
    }
    /** Pesquisa e retorna o Id de um usuário através de seu login */
    public int getUserId(String userLogin)
    {
    	int userId = 0;
    	String sql = "Select UserId from Users where UserLogin = '"+userLogin+"'";
    	try
    	{
    		Statement stm = this.connection.createStatement();
    		rs = stm.executeQuery(sql);
    		if(rs.next())
    			userId = rs.getInt("UserId");
    		rs.close();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error. "+e);
    		e.printStackTrace();
    	}
    	return userId;
    }
}
