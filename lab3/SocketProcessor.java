import java.io.*;
import java.net.*;

public class SocketProcessor implements Runnable{

    private Socket sockets;
    private InputStream is;
    private OutputStream os;
    FileInputStream fis;
    FileOutputStream fos;
    String pathD = "C:\\JavaServer\\";


    public SocketProcessor(Socket s) throws Throwable {

        sockets = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();

    }


    public void run() {

        try {

            String numbers = "200 OK";

            byte[] buf = new byte[64*1024];
            int n = is.read(buf);
            String str = new String(buf,0,n);
            System.out.println("Было получено:\n"+str);
            System.out.println("_____________");

            String get = firstLine(str);

            if (get.startsWith("GET")) {
                get = afterGet(get);
            } else if (get.startsWith("POST")) {
                get = afterGet(get);
                afterPost(str,get);
            } else {
                get = "Был получен не GET или POST запрос";
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
            buf = new byte[32*1024];

            while (true){

                n=fis.read(buf);
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
        }   catch (StringIndexOutOfBoundsException e) {
            System.out.println("Боже мой");
        }finally {

            try {
                if (sockets.isClosed()) {
                    sockets.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String firstLine(String str) throws IOException {

        String first;
        if (!str.isEmpty()) {
        first = str.substring(0,str.indexOf("\n"));
        } else {
            return str;
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
    private void afterPost(String str,String post){

        try {
        String value_buf= str.substring(str.indexOf("elem=") + 5, str.length());
        System.err.println("Вы прислали: "+value_buf);
        NOD nod = new NOD(value_buf);
        int a = nod.getResult();
            
        String value = value_buf + " = " + a;
            
        File file = new File(pathD+post);

            fos = new FileOutputStream(file);
            fos.write(value.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("");
        } catch (NumberFormatException e) {
            System.out.println(" ");
        }
    }

    private String afterGet(String get){

            get = get.substring(get.indexOf("/") + 1, get.lastIndexOf(" "));
            if (get.endsWith("/")) {
                get = get.substring(0,get.length()-1);
            }
            try {
                get = URLDecoder.decode(get, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        return get;
    }
}
