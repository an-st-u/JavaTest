import java.io.*;
import java.net.*;

public class SocketProcessor implements Runnable{

    private Socket sockets;
    private InputStream is;
    private OutputStream os;
    FileInputStream fis;
    String pathD = "C:\\JavaServer\\";


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
            String numbers = "200 OK";

            if (get.startsWith("GET")) {
                get = get.substring(get.indexOf("/") + 1, get.lastIndexOf(" "));
                if (get.endsWith("/")) {
                    get = get.substring(0,get.length()-1);
                }
                get = URLDecoder.decode(get, "UTF-8");

            } else {
                get = "Был получен не GET/ запрос";
            }

            if (get.trim().length()==0)
                get = "index.html";

            File file = new File(pathD+get);

            if (!file.exists()) {
                get = "404.html";
                numbers="404 Not Found";
            }

            file = new File(pathD+get);

            String answer = "HTTP/1.1 "+numbers+"\r\n" +
                    "Server: Brig207\r\n" +
                    "Content-Type: "+ typeFunction(get) +"; charset=UTF-8\r\n" +
                    "Content-Length: " + file.length() + "\r\n" +
                    "Connection: close\r\n\r\n";

            System.out.println("Отправлено:\n"+answer);

            os.write(answer.getBytes());

            fis = new FileInputStream(file);
            byte[] buf = new byte[32*1024];

            while (true){

                int n=fis.read(buf);

                if (n!=-1)
                    os.write(buf);
                else
                    break;

            }

            fis.close();
            os.flush();

        }   catch (NullPointerException e) {
            System.out.print("");
        }    catch (IOException e) {
            e.printStackTrace();
        }   finally {

            try {
                if (sockets.isClosed()) {
                    sockets.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String request(InputStreamReader inputStreamReader) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //Буферизирует символы и позволяет извлекать как строки, так и символы

        String first=null;
        while (true ) {

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



    private String typeFunction(String get){

        String typeOfFile = get.substring(get.indexOf(".") + 1, get.lastIndexOf(""));
        switch (typeOfFile) {
            case "jpg":
            case "png":
            case "ico":
            case "jpeg":
                typeOfFile = "image/" + typeOfFile;
                break;
            case "js":
                typeOfFile = "text/JavaScript";
                break;
            default:
                typeOfFile = "text/" + typeOfFile;
                break;
        }
            return typeOfFile;
    }
}
