import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {

        String HOST = "127.0.0.1"; // Dirección IP del servidor
        int PUERTO = 5000; // Puerto del servidor
        DataInputStream in; // Leer datos del socket
        DataOutputStream out; // Enviar datos al socket

        try {
            // Creación del socket y conexión al servidor
            System.out.println("Conectando al servidor...");
            Socket sc = new Socket(HOST, PUERTO);
            System.out.println("Conexión establecida.");

            // Inicialización de flujos de entrada y salida del socket
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            // Array con todas las respuestas posibles
            String[] mensajes = {
                    "¿Quién es?",
                    "¿Qué vienes a buscar?",
                    "Ya es tarde",
                    "Porque ahora soy yo la que quiere estar sin ti"
            };

            // Bucle para el envío de las respuestas
            for (String mensaje : mensajes) {
                System.out.println("Cliente: " + mensaje); // Mensaje enviado al servidor
                out.writeUTF(mensaje); // Envía el mensaje al servidor
                String respuesta = in.readUTF(); // Espera la respuesta del servidor
                System.out.println("Servidor: " + respuesta); // Imprime la respuesta

                // Si hay un error, termina la conversación
                if ("Error".equals(respuesta)) {
                    System.out.println("Error en la comunicacion. Terminando cliente.");
                    break;
                }
            }

            // Cerrar socket
            System.out.println("Cerrando conexion");
            sc.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}