/*ALBA GRACIA PRATICA 4 SMTP cliente*/

import java.net.*;
import java.io.*;
import java.util.Scanner;

//Scanner 220 String: conexion correcta
//Scanner 250 String: comando exitoso
//Scanner 354 String: servidor puede recibir contenido del correo
//Scanner 221 String: servidor cierra conexion

public class ClienteSMTP {

        static void error(String cadena) {
		System.out.println(cadena);
		System.exit(0);
	}

	public static void main(String args[]) {
	try{
		Socket s=new Socket("serveis-rdc.redes.upv.es", 25);
		
		System.out.println("Conectado al servidor SMTP de serveis-rdc");
		PrintWriter salida = new PrintWriter(s.getOutputStream());
		Scanner entrada=new Scanner(s.getInputStream());
		String respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("220")) {error(respuesta);}
                
                //conversacion con servidor HELO
		salida.print("HELO redes.upv.es\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

                //primera cabecera FROM
		salida.print("MAIL FROM:<redes11@redes.upv.es>\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

                //segunda cabecera TO:
		salida.print("RCPT TO:<redes11@redes.upv.es>\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}
                
                //contenido del correo
		salida.print("DATA\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("354")) {error(respuesta);}

		// LOS PRINTS CON EL CORREO
		// Aqui van varios print con todo el correo 
		salida.print("Correo FROM: <albazoel.gracia@gmail.com>.\r\n");
		salida.print("Correo TO: <redes11@redes.upv.es>.\r\n");
		salida.print("Prueba cliente SMTP.\r\n");
		//ahora el cuerpo despues de las 3 cabeceras
		
		salida.print("\r\n");
		salida.print("El cuerpo del mensaje.\r\n");
		salida.print(".empieza por un punto y no es la ultima.\r\n");
		salida.print("\r\n.\r\n"); //punto ultima linea
		
		// electronico incluidas las cabeceras

		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("250")) {error(respuesta);}

		salida.print("QUIT\r\n");
		salida.flush();
		respuesta = entrada.nextLine();
		System.out.println(respuesta);
		if (!respuesta.startsWith("221")) {error(respuesta);}

		s.close();
		System.out.println("Desconectado");

	} catch (UnknownHostException e) {
		System.out.println("Host desconocido");
		System.out.println(e);
	} catch (IOException e) {
		System.out.println("No se puede conectar");
		System.out.println(e);
	}
	}
}
