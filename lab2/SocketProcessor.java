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

        if(get == null)
            throw new NullPointerException("get is null");

        if (get.startsWith("GET")) {
            get = get.substring(get.indexOf("/") + 1, get.lastIndexOf(" "));
            get = java.net.URLDecoder.decode(get, "utf-8");
        } else {
            get = "Wrong!";
        }


        if (get.startsWith("index.html") || get.trim().length()==0) {
            get = index();
        } else if(get.startsWith("NOD") && !get.startsWith("index.html")) {
            String str;
            str = get.substring(get.indexOf("/") + 1, get.lastIndexOf(""));
            System.out.println(str);
            NOD Up = new NOD(str);
            int a = Up.getResult();
            get = "<br>Нод чисел " + str + " равен: "+a;
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
         os.write(body.trim().getBytes());
         os.close();

    }  catch (NullPointerException e) {
        System.out.print(" ");
    }  catch (IOException e) {
        e.printStackTrace();
    }

}


private static String request(InputStreamReader inputStreamReader) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //Буферизирует символы и позволяет извлекать как строки, так и символы

        String first=null;
        while (true ) {

            String get = bufferedReader.readLine();
            if (get.isEmpty() || get.trim().length()==0) {
                break;
            }
            System.out.println("Было получено: " + get);

            if (first==null ) {
                first=get;
            }

        }
        return first;
    }

    private String index() {

        String index = "Работу выполняли:<br> Бергер Юлия<br> Сапронов Ярослав <br>Степакшин Андрей<br>\n" +
                "Номер группы: РИ-330207 <br>\n" +
                "Номер индивидуального задания: 10<br>\n" +
                "Текст индивидуального задания:<br>\n" +
                "Наибольший общий делитель(НОД) чисел<br>\n" +
                "Числа должны поступать в виде строки<br> с некоторым разделителем.<br>\n";

        return index;
    }

}
