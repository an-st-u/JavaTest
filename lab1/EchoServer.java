import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static ServerSocket sockets;

    public static void main(String[] args) {
        int serverPort = 12345; // порт

        try {
            sockets = new ServerSocket(serverPort); /*Сокета  сервера ожидает
                                                      ожидает запрос от клиента*/
        } catch (IOException e) {
            e.printStackTrace(); //Если порт занят
        }


        try {
            System.out.println("Сервер ожидает подключения");
            Socket socket = sockets.accept(); /*Экземпляр класса
                                                Ожидает подключения к sockets*/

            System.out.println("Клиент установил подключение");

            InputStream inputStream = socket.getInputStream();    //Входящий поток данных
            OutputStream outputStream = socket.getOutputStream(); //Исходящий
            //Возвращают поток байт

            outputStream.write(("Ввведите любой символ\n").getBytes());

            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            //Работа с входными потоком данных как со строками
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //Буферизирует символы и позволяет извлекать как строки, так и символы


            while (true) {
                String get=bufferedReader.readLine();
                System.out.println("input:" + get);

                if (get.isEmpty()) {
                    break;
                }
                outputStream.write(("Получено с сервера = " + get+"\n").getBytes());
            }
            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
