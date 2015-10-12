import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static ServerSocket skt;

    public static void main(String[] args) {
        int serverPort = 12345; // порт
        String address = "127.0.0.1";

        try {
            skt = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Socket socket = skt.accept();
            InputStream inputStream = socket.getInputStream();

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("Введите 2 числа\n").getBytes());


            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            NOD up = new NOD(bufferedReader.readLine());

            outputStream.write(("НОД = " + up.alNOD()+"\n").getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
