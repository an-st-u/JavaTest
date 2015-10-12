import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int serverPort = 12345; // порт
        String address = "127.0.0.1"; // IP-адрес компьютера, исполняется серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {
            Socket socket = new Socket(address, serverPort);

            InputStream inputStream = socket.getInputStream();
            InputStreamReader  inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            System.err.println(bufferedReader.readLine());


            Scanner cin = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write((cin.nextLine()+"\n").getBytes());

            System.err.println(bufferedReader.readLine());
            


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
