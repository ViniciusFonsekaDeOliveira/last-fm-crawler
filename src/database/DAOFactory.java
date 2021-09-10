package database;


public abstract class DAOFactory
{
	public static final int POSTGRES = 1;

	public static DAOFactory createFactory(int factory)
    {
		switch (factory)
        {
		    case POSTGRES:
			return DBPostgres.getMyInstance();
		    default:
			return null;
		}
	}
}
