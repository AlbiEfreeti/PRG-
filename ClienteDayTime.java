
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClienteDayTime{
    public static void main(String args[]){
        String host = "zoltar.redes.upv.es";
        int port = 13;
        
        try(Socket s = new Socket(host, port)){
            System.out.println("Se ha conectado!");

            Scanner primLinea = new Scanner(s.getInputStream());
            while(primLinea.hasNext()){
                System.out.println(primLinea.nextLine());
            }
            InetAddress dirIP = InetAddress.getByName(host);
            System.out.println(dirIP);
        } catch (IOException e) {System.err.println("No se pude conectar.");
        //} catch (UnknownHostException e) {System.err.println("Host desconocido.");
        //} catch (NoRouteToHostException e) {System.err.println("No hay ruta al host.");}
    }
}
}