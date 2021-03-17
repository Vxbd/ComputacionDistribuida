/*
Autor: Barreiro Dominguez, Victor Xesus
Titulo: CD: Pra1: Aplicaciones Colaborativos
Descripcion: Aplicacion multicast que usa direccion 224.0.0 y puerto 6703
*/


import java.net.*;
import java.io.*;

public class Mensaxeria
{
	public static void main(String[] args)
	{
		String direccion = "224.0.0.0";
		int puerto = 6703;

		MulticastSocket s =null;

		try {

			InetAddress group = InetAddress.getByName(direccion);
			s = new MulticastSocket(puerto);
			s.joinGroup(group);
			
				//Creamos as clases dos fillos para envio e recpecion
				Fio f1 = new Fio("envio",group ,s);
				Fio f2 = new Fio("recepcion",group ,s);

				//Creamos os fillos
				Thread t1 = new Thread(f1);
				Thread t2 = new Thread(f2);

				//Iniciamos a execución dos dios.
				t1.start();
				t2.start();

				//Esperamos a que rematen os fios.
				try{
					t1.join();
					t2.join();
				}
				catch (InterruptedException e) { System.out.println("Thread: " + e.getMessage()); }

			s.leaveGroup(group); 

		} 
		catch (SocketException e){System.out.println("Socket: " + e.getMessage());}
		catch (IOException e){System.out.println("IO: " + e.getMessage());}
			
			finally {if(s != null) s.close();}  //En calquera caso pechamos o socket.

	}

}


