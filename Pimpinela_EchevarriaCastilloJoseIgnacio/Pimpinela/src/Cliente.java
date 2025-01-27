import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {

        String HOST ="127.0.0.1";
        int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try{

            Socket sc = new Socket(HOST,PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF("¿Quien es?");

            String msg = in.readUTF();

            System.out.println(msg);

            sc. close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
