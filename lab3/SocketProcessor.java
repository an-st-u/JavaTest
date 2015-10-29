import java.io.*;
import java.net.*;

public class SocketProcessor implements Runnable{

    private Socket sockets;
    private InputStream is;
    private OutputStream os;
    private FileReader fis;

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
                get = URLDecoder.decode(get, "UTF-8");
                get = afterGET(get); //Обработка текста после GET/

            } else {
                get = "Был получен не GET/ запрос";
            }

            String head = "<head><link rel=\"shortcut icon\" href=\"http://www.iconj.com/ico/h/9/h9arpg5dsi.ico\" type=\"image/x-icon\" /></head>\n";
            String body = "<html>" + head + "<body><h1>" + "Вы ввели: </h1><h2>" + get + "</h2></body></html>";
            String answer = "HTTP/1.1 200 OK\r\n" +
                    "Server: Brig207\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + (body.getBytes().length) + "\r\n" +
                    "Connection: close\r\n\r\n";

            System.out.println(answer + body);

            os.write(answer.getBytes());
            os.write(body.getBytes());
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

            if (get.isEmpty() && !get.equals("\r\n")) {
                break;
            }
            System.out.println("Было получено: " + get);

            if (first==null) {
                first=get;
            }

        }
        return first;
    }

    private String index(){

        String index = "";
        
        try {
            fis = new FileReader("C:\\FileServer\\index.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            
            BufferedReader br = new BufferedReader(fis);
            while (true) {
                String buf = br.readLine();
                if (buf.isEmpty()){
                    break;
                }
                index+=buf;
            }
            fis.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return index;
    }

    private String afterGET (String get) {

        if (get.startsWith("index.html") || get.trim().length()==0) {
            get = index();
        } else if(get.startsWith("NOD") || get.startsWith("index.html/NOD")) {
            String str;
            str = get.substring(get.indexOf("/") + 1, get.lastIndexOf(""));
            try {
                NOD Up = new NOD(str);
                int a = Up.getResult();
                get = "Нод чисел " + str + " равен: " + a;
            } catch (NumberFormatException e) {
                get = "Введите числа в адресную строку через запятую. Например: NOD/12,54";
                System.err.println("NumberFormatException");
            }
        }
        return get;
    }
}
