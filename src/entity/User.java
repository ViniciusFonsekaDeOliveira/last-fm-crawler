package entity;

public class User
{
	private String userLastFmId;
	private String userLogin;
	private String userRealName;
	private int userAge;
	private String userCountry;
	private String userGender;
	private int userPlaycount;
	
	public User(String userLastFmId, String userLogin, String userRealName, 
		int userAge, String userCountry, String userGender, int userPlaycount)
	{
		setUserLastFmId(userLastFmId);
		setUserLogin(userLogin);
		setUserRealName(userRealName);
		setUserAge(userAge);
		setUserCountry(userCountry);
		setUserGender(userGender);
		setUserPlaycount(userPlaycount);
	}
	public void setUserLastFmId(String userLastFmId)
	{
		this.userLastFmId = userLastFmId;
	}
	public void setUserLogin(String userLogin)
	{
		userLogin = userLogin.replaceAll("'", "''");
		userLogin = userLogin.trim();
		this.userLogin = userLogin;
	}
	public void setUserRealName(String userRealName)
	{
		userRealName = userRealName.replaceAll("'", "''");
		userRealName = userRealName.trim();
		this.userRealName = userRealName;
	}
	public void setUserAge(int userAge)
	{
		this.userAge = userAge;
	}
	public void setUserCountry(String userCountry)
	{
		this.userCountry = userCountry;
	}
	public void setUserGender(String userGender)
	{
		this.userGender = userGender;
	}
	public void setUserPlaycount(int userPlaycount)
	{
		this.userPlaycount = userPlaycount;
	}
	public String getUserLastFmId()
	{
		return userLastFmId;
	}
	public String getUserLogin()
	{
		return userLogin;
	}
	public String getUserRealName()
	{
		return userRealName;
	}
	public int getUserAge()
	{
		return userAge;
	}
	public String getUserCountry()
	{
		return userCountry;
	}
	public String getUserGender()
	{
		return userGender;
	}
	public int getUserPlaycount()
	{
		return userPlaycount;
	}
}
