import java.io.*;
import java.net.*;
import java.util.*;


public class TCPreto{
    public static void main(String args[]){
       

       try{
        Socket s1 = new Socket("localhost", 12345);
        Scanner entrada = new Scanner(s1.getInputStream());
        PrintWriter salida = new PrintWriter(s1.getOutputStream());
        
        System.out.println("Se ha conectado con exito.");
        
        int p = s1.getLocalPort();
        String port = Integer.toString(p);
        salida.println(port + "\r\n");
        salida.flush();
        
        //s1.close();
        
        String reto3p = entrada.nextLine();
        int puerto2 = Integer.parseInt(reto3p);

        Socket s2 = new Socket("localhost", puerto2);
        
        String reto4 = entrada.nextLine();
        PrintWriter salida2 = new PrintWriter(s2.getOutputStream());
        
        salida2.println(reto4);
        salida2.flush();
        
        //reto 5
        Scanner entrada2 = new Scanner(s2.getInputStream());
        String env = "Juego";
        salida.println(env);
        salida.flush();
        String reto5 = entrada2.nextLine();
        
        while(reto5 != env){
        
        if(reto5 == env) {
        salida.println("200");}
        else{ 
        salida.println("400");}
        
        salida.flush();
        }
        
        s1.close();
        s2.close();


    } catch (UnknownHostException e) {System.err.println("Host error.");}
    catch (IOException e) {System.err.println("IO error.");}
    
}
}
