package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBPostgres extends DAOFactory
{
	public static final String DRIVER = "org.postgresql.Driver";
	public static final String URL = "jdbc:postgresql://localhost:5432/ocean";
	private static DBPostgres postgresInstance = null;
	private static Connection connection = null;
	
	private DBPostgres()
	{
		try
		{
			Class.forName(DRIVER);
		    setConnection(DriverManager.getConnection(URL, "postgres", "***************"));                
		}
		catch(Exception e)
		{
			System.out.println("Error. "+e);
			e.printStackTrace();
		}
	}
	
	public static DBPostgres getMyInstance()
	{
		if(postgresInstance == null)
		{
			postgresInstance =  new DBPostgres();
		}

		return postgresInstance;
	}
	
	
	public static Connection getConnection()
	{
		getMyInstance();
		return connection;
	}
	
	private void setConnection(Connection conn)
	{
		DBPostgres.connection = conn;
	}
	
	public boolean isClosed()
	{
		if(connection == null)
			return true;
		else
			return false;
	}
}
