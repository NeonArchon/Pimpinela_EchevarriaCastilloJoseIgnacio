import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {

        String HOST ="127.0.0.1"; //definor dereccion IP para conectarr
        int PUERTO = 5000; //puerto del servidor
        //variables para manejo de datos
        DataInputStream in; //leer datos del cocket
        DataOutputStream out; //enviar datos del socker

        try{
            //creacion de ub socket
            Socket sc = new Socket(HOST,PUERTO);

            //inicielizacion de flujos de entrada y salida del socker
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //array con todas las respuestas posibles
            String[] mensajes = {"¿Quién es?",
                                 "¿Qué vienes a buscar?",
                                 "Ya es tarde",
                                 "Porque ahora soy yo la que quiere estar sin ti"};

            //bucle para el envio de las respuestas
            for (String mensaje : mensajes) {
                out.writeUTF(mensaje);
                String respuesta = in.readUTF();
                System.out.println("Servidor: " + respuesta);

                if ("Error".equals(respuesta)) {
                    System.out.println("Ocurrio un error de en la conexion");
                    break;
                }
            }

            //cerrar socket
            sc. close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
