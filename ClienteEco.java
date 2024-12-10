import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteEco {
    public static void main(String[] args) {
        // Definir el host y el puerto
        String host = "zoltar.redes.upv.es";
        int port = 7;

        // Intentar establecer la conexión
        try (Socket socket = new Socket(host, port)) {
            // Crear un PrintWriter para enviar datos al servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            // Enviar el mensaje "¡¡Hola, Mundo!!"
            String mensaje = "¡¡Hola, Mundo!!";
            writer.println(mensaje);
            //writer.flush(); FLUSH O autoFlush:true
            
            // Crear un Scanner para leer la respuesta del servidor
            Scanner lee = new Scanner(socket.getInputStream());
            
            // Leer la primera línea de texto del servidor y mostrarla
            String respuesta = lee.nextLine();
            System.out.println("Respuesta del servidor: " + respuesta);
            
        } catch (UnknownHostException e) {
            System.err.println("Nombre de servidor desconocido: " + host);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al intentar conectarse a " + host + ":" + port);
        }
    }
}