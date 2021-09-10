package collector;

import java.util.ArrayList;
import java.util.Collection;

import entity.User;

public class UserCollector
{
	/** Coleta login, nome verdadeiro, idade, sexo, país, id e total de musicas ouvidas, de um usuário. */
	public static User myUserInfo(String userLogin, String apiKey)
	{
		de.umass.lastfm.User lastfmUser = de.umass.lastfm.User.getInfo(userLogin, apiKey);
		String userLastFmId = lastfmUser.getId();
		String userRealName = lastfmUser.getRealname();
		int userAge = lastfmUser.getAge();
		String userCountry = lastfmUser.getCountry();
		String userGender = lastfmUser.getGender();
		int userPlaycount = lastfmUser.getPlaycount();
		User myUser = new User(userLastFmId, userLogin, userRealName, userAge, userCountry, userGender, userPlaycount);
		return myUser;	
	}
	/** Coleta vizinhos de um usuário */
	public static Collection<User> neighbours(String userLogin, String apiKey)
	{
		Collection<de.umass.lastfm.User> lastfmUserNeighboursList = null;
		Collection<User> myUserNeighboursList = new ArrayList<User>();
		try
		{
			lastfmUserNeighboursList = de.umass.lastfm.User.getNeighbours(userLogin, apiKey);
		}
		catch(Exception e)
		{
			System.out.println("Error." +e);
			e.printStackTrace();
			if(lastfmUserNeighboursList == null || lastfmUserNeighboursList.size() == 0)
				return null;
		}
		User myUser;
		for(de.umass.lastfm.User lastfmUser : lastfmUserNeighboursList)
		{
			//lastfmUser.getname() --> userLogin.
			myUser = myUserInfo(lastfmUser.getName(), apiKey);
			myUserNeighboursList.add(myUser);
		}
		return myUserNeighboursList;
	}
}
