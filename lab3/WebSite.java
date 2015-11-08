import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebSite {

    public static List<Cinema> DataBase = new ArrayList<>();

    public static void main(String[] args) throws Throwable{

        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            System.out.println("Сервер ожидает подключения");
            Socket s = ss.accept();
            System.out.println("Клиент установил соединение");
            Thread T1 = new Thread(new SocketProcessor(s));
            T1.start();
        }
    }

}

