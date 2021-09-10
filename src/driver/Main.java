package driver;


import java.util.ArrayList;

import database.DAOIncubator;

public class Main 
{
	public static void main(String args[])
	{			
		String apiKey = "< coloque sua chave de API aqui >";
		ArrayList<String> mySeeds = new ArrayList<String>();
		//Para mais usuários apenas repita o comando abaixo :).
		mySeeds.add("NOMEDEUSUARIO");
	
		
		
		Debuger.printMemory();
		for( int user = 0; user < mySeeds.size(); user++ )
		{
			String userLogin = mySeeds.get(user);
			mySeeds = Driver.Collector(userLogin, mySeeds, apiKey);
			System.gc();
			Debuger.printMemory();
		}
		DAOIncubator incubator = new DAOIncubator();
		incubator.CloseConnection();
	}
}
