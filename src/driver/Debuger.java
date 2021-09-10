package driver;

public class Debuger 
{
	/** Informa status da memória do programa enquanto a coleta de dados é executada. */
	public static void printMemory()
	{
		System.out.println("\n\nMemória max "+Runtime.getRuntime().maxMemory()/1024);
		System.out.println("Memória Total "+Runtime.getRuntime().totalMemory()/1024);
		System.out.println("Memória livre "+Runtime.getRuntime().freeMemory()/1024);
		System.out.println("Memória usada "+((Runtime.getRuntime().maxMemory()/1024)- Runtime.getRuntime().freeMemory()/1024));
	}
}
