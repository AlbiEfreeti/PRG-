import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP1 {
    public static void main(String[] args) {
        // Definir el host y el puerto
        String host = "www.upv.es";
        int port = 80;

        // Intentar establecer la conexión
        try (Socket socket = new Socket(host, port)) {
            // Si la conexión se establece, se imprime el mensaje
            System.out.println("¡Conectado!");
        } catch (UnknownHostException e) {
            System.err.println("No se puede determinar la dirección IP del host: " + host);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al intentar conectarse a " + host + ":" + port);
        }
    }
}