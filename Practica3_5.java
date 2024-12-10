import java.io.*;
import java.net.*;
import java.util.*;

public class Practica3_5 {

    private static String host;
    private static String objeto;
    private static Socket socketCliente = new Socket();
    private static Scanner recepcion;
    private static PrintWriter transmision;

    public static boolean validaURL(String url) {
        try {
            URL urlEntrada = new URL(url);
            host = urlEntrada.getHost();
            objeto = urlEntrada.getPath();
            if (objeto.isEmpty()) { objeto = "/"; }
            InetAddress.getByName(host);
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

    public static void construyePeticion() {
        StringBuilder sb = new StringBuilder();
        sb.append("GET ").append(objeto).append(" HTTP/1.1\n");
        sb.append("Host: ").append(host).append("\n");
        sb.append("Connection: close\n\n");
        System.out.println(sb.toString());
    }

    public static boolean enviarPeticion(String peticion) {
        try {
            socketCliente.connect(new InetSocketAddress(host, 80));
            recepcion = new Scanner(socketCliente.getInputStream());
            transmision = new PrintWriter(socketCliente.getOutputStream(), true);
            transmision.println(peticion);
            System.out.println("Petición enviada con éxito.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al enviar la petición: " + e.getMessage());
            return false;
        }
    }

    public static String obtenerPagina() {
        try {
            String lineaEstado = recepcion.nextLine();
            System.out.println("Estado: " + lineaEstado);
            int codigoEstado = Integer.parseInt(lineaEstado.split(" ")[1]);

            switch (codigoEstado) {
                case 200: System.out.println("200 OK"); break;
                case 301: System.out.println("301 MOVED PERMANENTLY"); break;
                case 400: System.out.println("400 BAD REQUEST"); break;
                case 404: System.out.println("404 NOT FOUND"); break;
                default: System.out.println("Código no manejado: " + codigoEstado); break;
            }

            System.out.println("Cabeceras:");
            String linea;
            while (!(linea = recepcion.nextLine()).isEmpty()) {
                System.out.println(linea);
            }

            System.out.println("Cuerpo del recurso:");
            StringBuilder cuerpo = new StringBuilder();
            while (recepcion.hasNextLine()) {
                cuerpo.append(recepcion.nextLine()).append("\n");
            }

            return cuerpo.toString();
        } catch (Exception e) {
            System.err.println("Error al procesar la respuesta: " + e.getMessage());
            return null;
        }
    }

    public static void extraerEnlaces(String archivo) {
        String patron = "<a href=\"";
        int indice = 0;
        System.out.println("Enlaces encontrados:");
        while ((indice = archivo.indexOf(patron, indice)) != -1) {
            int inicio = indice + patron.length();
            int fin = archivo.indexOf("\"", inicio);
            if (fin != -1) {
                String enlace = archivo.substring(inicio, fin);
                System.out.println(enlace);
            }
            indice = fin + 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!validaURL(input)) {
            System.out.println("Introduce URL: ");
            input = scanner.nextLine();
        }

        System.out.println("Variables globales: " + host + objeto);

        String peticion = "GET " + objeto + " HTTP/1.1\nHost: " + host + "\nConnection: close\n\n";
        if (enviarPeticion(peticion)) {
            System.out.println("La petición se ha enviado con éxito.");
            String pagina = obtenerPagina();
            if (pagina != null) {
                System.out.println("Contenido de la página:\n" + pagina);
                extraerEnlaces(pagina);
            }
        } else {
            System.out.println("Hubo un error al enviar la petición.");
        }

        try {
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }

        scanner.close();
    }
}
