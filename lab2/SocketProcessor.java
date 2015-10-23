import java.io.*;
import java.net.Socket;

public class SocketProcessor implements Runnable{

    private Socket sockets;
    private InputStream is;
    private OutputStream os;

public SocketProcessor(Socket s) throws Throwable {
        sockets = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
}

public void run() {

    try {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        //Работа с входными потоком данных как со строками

        String get = request(inputStreamReader);
        if (get.startsWith("GET")) {
            get = get.substring(get.indexOf("/") + 1, get.lastIndexOf(" "));
            get = java.net.URLDecoder.decode(get, "utf-8");
        } else {
            get = "Wrong!";
        }

         String head = "<head><link rel=\"shortcut icon\" href=\"http://www.iconj.com/ico/h/9/h9arpg5dsi.ico\" type=\"image/x-icon\" /></head>\n";
         String body = "<html>" + head + "<body><h1>" + "Вы ввели: " + get + "</h1></body></html>\n";
         String answer = "HTTP/1.1 200 OK\r\n" +
         "Server: Brig207\r\n" +
         "Content-Type: text/html; charset=utf-8\r\n" +
         "Content-Length: " + body.length() + "\r\n" + "Connection: close\r\n\r\n";

         answer += body;

         os.write((answer).getBytes());
         os.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

}


private static String request(InputStreamReader inputStreamReader) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //Буферизирует символы и позволяет извлекать как строки, так и символы

        String first=null;
        while (true) {

            String get = bufferedReader.readLine();
            if (get.isEmpty()) {
                break;
            }
            System.out.println("Было получено: " + get);

            if (first==null) {
                first=get;
            }

        }
        return first;
    }

}
