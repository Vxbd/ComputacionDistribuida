/*
Autor: Barreiro Dominguez, Victor Xesus
Titulo: Clase FIo de Pra1: Mensaxeria
Descripcion: Fio de recepcion y envio.
*/

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Fio implements Runnable
{
	private String tipo;
	private MulticastSocket s;
	private InetAddress group;

	public Fio(String tipo, InetAddress group, MulticastSocket s)
	{
		this.tipo = tipo;
		this.s = s;
		this.group = group;
	}
	public void run()
	{
		try {
		
			if (tipo.equals("recepcion")) {
				
			byte[] buffer1;
				while(true)
				{
					buffer1 = new byte[1000];
					DatagramPacket messageIn = new DatagramPacket(buffer1, buffer1.length);
					s.receive(messageIn);
					System.out.print(">>>"); //Indicador de recepcion.
					System.out.println(new String(messageIn.getData()));
				}

			}
			else if(tipo.equals("envio"))
			{

				Scanner in = new Scanner(System.in);
				byte[] m;

				while(true)
				{
					String text = in.nextLine();

					if (text.equals("sair")) {
						System.out.println("Saindo");
						s.leaveGroup(group);
						s.close(); 
						
					}

					m = text.getBytes();
					DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6703);
					System.out.print("--"); //Indicador de mensaxe enviada ORIENTATIVO.
					s.send(messageOut);
				}

			}

		}
		
		catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e){System.out.println("IO: " + e.getMessage());}
	}
}