import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        ServerSocket server = null; // Para escuchar conexiones del cliente
        Socket sc; // Conexión del cliente
        DataInputStream in;
        DataOutputStream out;
        int PUERTO = 5000;

        try {
            // Inicialización del socket del servidor
            server = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado, esperando conexiones...");

            // Canal siempre abierto
            while (true) {
                // Acepta la conexión del cliente
                sc = server.accept(); // Se queda escuchando
                System.out.println("Conectando con Cliente...");

                // Inicialización de los flujos de entrada y salida
                in = new DataInputStream(sc.getInputStream()); // Recibe mensajes
                out = new DataOutputStream(sc.getOutputStream()); // Envía mensajes

                boolean conversacion = true;
                String estado = "inicio";

                while (conversacion) {
                    try {
                        // Leer mensaje del cliente
                        String mensajeCliente = in.readUTF();
                        System.out.println("Cliente: " + mensajeCliente);

                        switch (estado) {
                            case "inicio":
                                if ("¿Quién es?".equals(mensajeCliente)) {
                                    out.writeUTF("Soy yo");
                                    estado = "pregunta1";
                                } else {
                                    out.writeUTF("Error");
                                }
                                break;

                            case "pregunta1":
                                if ("¿Qué vienes a buscar?".equals(mensajeCliente)) {
                                    out.writeUTF("A ti");
                                    estado = "pregunta2";
                                } else {
                                    out.writeUTF("Error");
                                }
                                break;

                            case "pregunta2":
                                if ("Ya es tarde".equals(mensajeCliente)) {
                                    out.writeUTF("¿Por qué?");
                                    estado = "pregunta3";
                                } else {
                                    out.writeUTF("Error");
                                }
                                break;

                            case "pregunta3":
                                if ("Porque ahora soy yo la que quiere estar sin ti".equals(mensajeCliente)) {
                                    out.writeUTF("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                                    conversacion = false;
                                } else {
                                    out.writeUTF("Error");
                                }
                                break;

                            default:
                                out.writeUTF("Error");
                                conversacion = false;
                                break;
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer mensaje del cliente.");
                        break;
                    }
                }

                // Mensaje final y cierre de conexión
                out.writeUTF("Cliente desconectado");
                System.out.println("Conexion cerrada con el cliente.");
                sc.close();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}