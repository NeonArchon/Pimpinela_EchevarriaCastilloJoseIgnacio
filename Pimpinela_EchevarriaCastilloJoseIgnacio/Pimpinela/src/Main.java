import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        ServerSocket server = null; //para escuchar las conexiones con el cliente
        Socket sc; //conexion del cliene
        DataInputStream in;
        DataOutputStream out;
        int PUERTO = 5000;

        try{
            //inicializacion del socket
            server = new ServerSocket(PUERTO);
            System.out.println("servidor iniciado");

            //canal siempre abierto
            while (true) {
                //socket del cliente
                sc = server.accept(); //se queda escuchando, esperoando respuesta
                System.out.println("Conectando con Cliente");

                //inicializacion de los flujos de entrada y salida de datos
                in = new DataInputStream(sc.getInputStream()); //recibe mensajes
                out = new DataOutputStream(sc.getOutputStream());//envia mensajesç

                String msg = in.readUTF();
                System.out.println(msg);

                String estado = "inicio";
                boolean Conversacion = true;

                //bucle para responder al cliente
                while (Conversacion) {
                    String MensajeCliente = in.readUTF();
                    System.out.println("Cliente" + MensajeCliente);

                    switch(estado) {
                        case "inicio":
                            if ("¿Quien es?".equals(MensajeCliente)){
                                //enviar mensaje al cliente
                                out.writeUTF("Soy Yo");
                                estado = "Pregunta1";
                            } else {
                                out.writeUTF("ERROR");
                            }
                            break;

                        case "pregunta1":
                            if ("¿Qué vienes a buscar?".equals(MensajeCliente)) {
                                out.writeUTF("A ti");
                                estado = "pregunta2";
                            } else {
                                out.writeUTF("Error");
                            }
                            break;

                        case "pregunta2":
                            if ("Ya es tarde".equals(MensajeCliente)) {
                                out.writeUTF("¿Por qué?");
                                estado = "pregunta3";
                            } else {
                                out.writeUTF("Error");
                            }
                            break;

                        case "pregunta3":
                            if ("Porque ahora soy yo la que quiere estar sin ti".equals(MensajeCliente)) {
                                out.writeUTF("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                                Conversacion = false;
                            } else {
                                out.writeUTF("Error");
                            }
                            break;

                    }
                }

                //cerrar el socket
                sc.close();
                out.writeUTF("Cliente desconectado");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}