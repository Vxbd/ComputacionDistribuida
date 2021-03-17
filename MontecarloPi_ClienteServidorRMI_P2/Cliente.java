/*
Titulo: Pra2-CD: Cliente-Servidor RMI. Metodo Montecarlo.
Autor: Barreiro Domínguez, Víctor Xesús
Descricion: Clase cliente que se encarga de solicitar a información
            necesaria ao usuario: numero de puntos a xerar, numero de servidores
            direccion de cada un deles e porto.
            Do mesmo xeito, reparte equitativamente a carga entre eles e recupera
            a información unha vez concluida a execucíon  de todos eles.
*/

import java.io.*;
import java.rmi.*;
import java.util.*;
import java.net.*;


public class Cliente {

   public static void main(String args[]) {
      try {
         int RMIPort;         
         String hostName;
         InputStreamReader is = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(is);

         System.out.println("Numero de puntos a generar:");
         String param = br.readLine();
         long n = Integer.parseInt(param);
         //long n = 100000000;

         System.out.println("Numero de servidores a emplear:");
         param = br.readLine();
         int s = Integer.parseInt(param);
         //int s = 1;

         ArrayList<String> rexistros = new ArrayList<>();
         System.out.println("Numero de servidores a emplear:" + n);
         System.out.println("Numero de servidores a emplear:" + s);
         System.out.println("====================================");

         for (int i = 0 ; i<s ; i++ ) {
               System.out.println(">>> Informacion de serivodr: " + (i+1));
               System.out.println("Enter the RMIRegistry host namer:");
               hostName = br.readLine();
               System.out.println("Enter the RMIregistry port number:");
               String portNum = br.readLine();
               RMIPort = Integer.parseInt(portNum);
               rexistros.add(new String("rmi://" + hostName+ ":" + portNum + "/pi"));  
         }

         /*Creamos os obxectos de tipo Fio que encapsularán conexion
         y se encargarán de conversar con el servidor.*/
         ArrayList<Fio> calculos = new ArrayList<>();
         for (int i = 0 ; i<s ; i++ ) {
            calculos.add(new Fio(rexistros.get(i), n/s));
         }
         
         /*Creamos os fios e comezamos a execución*/
         ArrayList<Thread> fios = new ArrayList<>();
         for (int i = 0 ; i<s ; i++ ) {
            fios.add(new Thread(calculos.get(i)));
            fios.get(i).start();
         }

         /*Esperamos a que todos os fios rematen para continaur*/
         for (int i = 0 ; i<s ; i++ ) {
            fios.get(i).join();
         }

         //Sumamos os puntos que caen dentro obtidos dos distintos fios
         long res = 0;
         for (int i = 0 ; i<s ; i++ ) {
            res = res + calculos.get(i).getM();
         }

         //Calculamos pi cos resultados
         double resTotal = (double)(4*res)/(double)n;

         System.out.println("Resultado: " + resTotal);
      } 
      catch (Exception e) {
         System.out.println("Exception in PiCLient: " + e);
      } 
   } 
}
