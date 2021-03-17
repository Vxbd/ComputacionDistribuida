/*
Titulo: Pra2-CD: Cliente-Servidor RMI. Metodo Montecarlo.
Autor: Barreiro Domínguez, Víctor Xesús
Descricion: Clase para a implementación do cliente que permitira
			a execución paralela dos calculos. Recibirá Un String e un long
			adicados o primeiro a realizar a conexión co servidor e o segundo
			a establecer o número de cálculos que debe realizar o fio (solicita
			ao servidor.)
			Incorporase un método para recuperar o resultado da execución.
*/



import java.io.*;
import java.rmi.*;

public class Fio implements Runnable
{
	private String conexion;
	private long n;
	private long m;

	public Fio(String conexion, long n)
	{
		this.conexion = conexion;
		this.n = n;
	}

	public void run()
	{
		try {
			CalculoInterface calc = (CalculoInterface)Naming.lookup(conexion);
			this.m = calc.realizarCalculo(this.n);
      	} 
      	catch (Exception e) {
        	System.out.println("Exception in PiCLient {Fio} : " + e);
      	}
		
	}

	public long getM()
	{
		return this.m;
	}
}