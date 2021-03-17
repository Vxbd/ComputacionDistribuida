/*
Titulo: Pra2-CD: Cliente-Servidor RMI. Metodo Montecarlo.
Autor: Barreiro Domínguez, Víctor Xesús
Descricion: Obxecto servidor encargado de xerar números
			aleatorios e verificar que cumplen a función.
*/
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class Calculo extends UnicastRemoteObject
     implements CalculoInterface
{

	public Calculo() throws RemoteException {
      super( );
    }

	public long realizarCalculo(long n)
	{
		Funcion funcion = new Funcion();

		Random r1 = new Random(System.currentTimeMillis());

		long m = 0;
		double res;

		for (int i = 0; i < n ;i++) {
			if (funcion.evaluate(r1.nextDouble(), r1.nextDouble())) {
				m++;	
			}
		}

		
		return m;
	}

}