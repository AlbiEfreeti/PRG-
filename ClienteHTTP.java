import java.net.*;
import java.io.*;
import java.util.*;

/*Practica 3 en REDES */

class ClienteHTTP {

  static String nombre_servidor;
  static Socket s;
  static ScannerRedes entrada;
  static PrintWriter salida;
  

  public static void envia_peticion(String objeto) {
     salida.printf("GET " + objeto + " HTTP/1.1\r\n");
     salida.printf("Host: "+ nombre_servidor + "\r\n");
     salida.printf("Connection: close\r\n");
     salida.printf("\r\n");
     salida.flush();
  }

  public static void lee_linea_estado() {
    System.out.println(">>>>>>>>>>>>>>> LINEA DE ESTADO <<<<<<<<<<<<<<<");
    String lineaEstado = entrada.nextLine();
    System.out.println(lineaEstado);

  }

  public static void lee_cabeceras() {
    System.out.println(">>>>>>>>>>>>>>>    CABECERAS    <<<<<<<<<<<<<<<");
    String cabecera = entrada.nextLine();
    while(!cabecera.isEmpty()){
    System.out.println(cabecera);
    cabecera = entrada.nextLine();}

  }

  public static void lee_cuerpo_texto() {
    System.out.println(">>>>>>>>>>>>>>>   CUERPO TEXTO  <<<<<<<<<<<<<<<");
    while(entrada.hasNext()){
    String linea = entrada.nextLine();
    System.out.println(linea);}

  }

  public static void lee_cuerpo_binario(String nombre_fichero) {
    System.out.println(">>>>>>>>>>>>>>>  CUERPO BINARIO <<<<<<<<<<<<<<<");
    try{
    FileOutputStream fichero = new FileOutputStream(nombre_fichero);
    int a = s.getInputStream().read();
    int contador = 0;
    
    while(a != -1){
    fichero.write(a);
    a = s.getInputStream().read();
    contador++;
    }
    fichero.close();
    } catch(IOException e) {
      System.out.println("Ha ocurrido un error.");
    } 
    

  }



  public static void main(String args[]) throws Exception {
    nombre_servidor = "zoltar.redes.upv.es";
    
    //Peticion 1: HTML principal
    s = new Socket(nombre_servidor, 80);
    entrada = new ScannerRedes(s.getInputStream());
    salida = new PrintWriter(s.getOutputStream());
    
    envia_peticion("/");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_texto();

    
    s.close();
    
    //Peticion 2: oto1.jpg
    s = new Socket(nombre_servidor, 80);
    entrada = new ScannerRedes(s.getInputStream());
    salida = new PrintWriter(s.getOutputStream());
    
    
    
    envia_peticion("/");
    envia_peticion("/oto1.jpg");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_texto();
    lee_cuerpo_binario("oto1.jpg");
    
    s.close();


    //Peticion 3: oto2.jpg
    s = new Socket(nombre_servidor, 80);
    entrada = new ScannerRedes(s.getInputStream());
    salida = new PrintWriter(s.getOutputStream());
    
    
    
    envia_peticion("/");
    envia_peticion("/oto2.jpg");
    lee_linea_estado();
    lee_cabeceras();
    lee_cuerpo_texto();
    lee_cuerpo_binario("oto2.jpg");
    
    s.close();

    s.close();
  }

}
