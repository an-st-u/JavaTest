import java.io.*;
import java.net.*;

public class SocketProcessor implements Runnable{

    private Socket sockets;
    private InputStream is;
    private OutputStream os;
    private FileReader fr;
    private FileInputStream fis;
    String pathD = "C:\\JavaServer\\";
    private String type="text/html";;


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
                get = afterGET(get); //Обработка текста после GET в байтах/

            } else {
                get = "Был получен не GET/ запрос";
            }


            String answer = "HTTP/1.1 200 OK\r\n" +
                    "Server: Brig207\r\n" +
                    "Content-Type: "+type+"; charset=UTF-8\r\n" +
                    "Content-Length: " + (get.getBytes().length) + "\r\n" +
                    "Connection: close\r\n\r\n";

            System.out.println("Отправлено:\n"+answer);

            os.write(answer.getBytes());
            os.write(get.getBytes());
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

    private String index(String nameOfFile){


        File file = new File(pathD+nameOfFile);
        String index ="";

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            BufferedReader br = new BufferedReader(fr);
            while (true) {

                String buf = br.readLine();
                if (buf==null) {
                    break;
                }
                index+=buf;

            }
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return index;
    }

    private String afterGET (String get) {

        String typeOfFile = get.substring(get.indexOf(".") + 1, get.lastIndexOf(""));

        if (typeOfFile.equals("jpg") || typeOfFile.equals("ico") || typeOfFile.equals("png")) {
            picture(get,typeOfFile);
            return null;
        } else {
            if (get.trim().length() == 0) {
                get = index("index.html");
            }
        }
        return get;
    }

    private void picture(String nameOfFile,String typeOfFile) {

            type = "image/"+typeOfFile;

        try {
            File file = new File(pathD+nameOfFile);

            String answer = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: "+type+"\r\n" +
                    "Content-Length: " + (file.length()) + "\r\n" +
                    "Connection: close\r\n\r\n";
            System.out.println("Picture Отправлено:\n"+answer);

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println();
        }




    }
}
