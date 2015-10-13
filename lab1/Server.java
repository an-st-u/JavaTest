import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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

            outputStream.write(("Vvedite 2 chisla\n").getBytes());

            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            //Работа с входными потоком данных как со строками
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //Буферизирует символы и позволяет извлекать как строки, так и символы

            String get=bufferedReader.readLine();
            System.out.println("Было получено: "+ get);
            NOD up = new NOD(get);

            outputStream.write(("NOD = " + up.getResults() + "\n").getBytes());
            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
