import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP3 {
    public static void main(String[] args) {
        // Definir el host y el puerto
        String host = "www.upv.es";
        int port = 80;

        // Intentar establecer la conexión
        try (Socket socket = new Socket(host, port)) {
            // Si la conexión se establece, se imprime el mensaje
            System.out.println("¡Conectado de nuevo!");



            System.out.println("Puerto remoto: " + socket.getPort());
            System.out.println("Dirección IP remota: " + socket.getInetAddress());
            System.out.println("Puerto local: " + socket.getLocalPort());
            System.out.println("Dirección IP local: " + socket.getLocalAddress());
            System.out.println("-------------------------------");
        } catch (UnknownHostException e) {
            System.err.println("No se puede determinar la dirección IP del host: " + host);
        } catch (NoRouteToHostException e) {
            System.err.println("No es posible realizar conexión a " + host + ":" + port);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al intentar conectarse a " + host + ":" + port);
        }
    }
}
