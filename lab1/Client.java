import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int serverPort = 12345; // порт
        String address = "127.0.0.1"; // IP-адрес компьютера, исполняется серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {

            Socket socket = new Socket(address, serverPort); // Cоздаём  сокету с портом 12345

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            InputStreamReader  inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            System.out.println(bufferedReader.readLine());

            Scanner cin = new Scanner(System.in);
            outputStream.write((cin.nextLine()+"\n").getBytes());

            System.out.println(bufferedReader.readLine());
            


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
