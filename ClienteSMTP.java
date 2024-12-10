import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClienteSMTP {
    public static void main(String[] args) {
        // Definir el host y el puerto
        String host = "smtp.upv.es";
        int port = 25;

        // Intentar establecer la conexión
        try (Socket socket = new Socket(host, port)) {
            // Crear un Scanner para leer la respuesta del servidor
            Scanner lee = new Scanner(socket.getInputStream());
            
            // Leer y mostrar la primera línea de texto del servidor
            String bienvenida = lee.nextLine();
            System.out.println("Respuesta del servidor: " + bienvenida);
            
            // Crear un PrintWriter para enviar datos al servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), false);
            
            // Enviar la petición HELO
            String comandoHELO = "HELO rdcXX.redes.upv.es"; // Reemplaza rdcXX por tu identificador
            writer.println(comandoHELO);
            writer.flush(); // Asegurarse de que el comando se envíe inmediatamente
            
            // Leer la respuesta del servidor y mostrarla
            String respuestaHELO = lee.nextLine();
            System.out.println("Respuesta después de HELO: " + respuestaHELO);
            
        } catch (UnknownHostException e) {
            System.err.println("Nombre de servidor desconocido: " + host);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al intentar conectarse a " + host + ":" + port);
        }
    }
}