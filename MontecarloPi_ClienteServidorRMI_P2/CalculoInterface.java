/*
Titulo: Pra2-CD: Cliente-Servidor RMI. Metodo Montecarlo.
Autor: Barreiro Domínguez, Víctor Xesús
Descricion: Interface obxecto servidor.
*/

import java.rmi.*;

public interface CalculoInterface extends Remote {

   public long realizarCalculo(long n) 
      throws java.rmi.RemoteException;

}