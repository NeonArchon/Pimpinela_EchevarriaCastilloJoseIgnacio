import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket sc;
        DataInputStream in;
        DataOutputStream out;
        int PUERTO = 5000;

        try{
            server = new ServerSocket(PUERTO);
            System.out.println("servidor iniciado");

            //canal siempre abierto
            while (true) {
                //socket del cliente

                sc = server.accept(); //se queda escuchando, esperoando respuesta

                System.out.println("Conectando con Cliente");

                in = new DataInputStream(sc.getInputStream()); //recibe mensajes
                out = new DataOutputStream(sc.getOutputStream());//envia mensajes

                out.writeUTF("Soy yo");

                sc.close();

                out.writeUTF("Cliente desconectado");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}