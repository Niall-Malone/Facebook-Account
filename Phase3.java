
package sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.net.ssl.SSLSocket;


public class Phase3 {

    private SSLSocket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private boolean found = false;
    
    public Phase3(SSLSocket s, BufferedWriter o, BufferedReader n) {
        this.socket = s;
        this.out = o;
        this.in = n;
    }
    
    public boolean returnFoundFlag() {
        return found;
    }
    
    public String receive() {
        String message = "";
        //Wait for a message to arrive.  
        //Wouldn't want to miss any messages delayed by network connection
        try {
            System.out.println("Waiting for message");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!in.ready()) {
                message += in.readLine() + "\n\r";
            }
            while (in.ready()) {
                 message += in.readLine() + "\n\r";
            }

            if ( message.contains("Set-Cookie: c_user=")) {
                return (message);
            }
            
            return "Incorrect Entry";

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public void sendHTTPPOST(String email,String pass) throws UnsupportedEncodingException, IOException{
            String chk = "lsd=AVpD2t1f&display=&enable_profile_selector=&legacy_return=1&next=&profile_selector_ids=&trynum=1&timezone=300&lgnrnd=031110_Euoh&lgnjs=1366193470&email=" + email + "&pass=" + pass + "&default_persistent=0&login=Log+In";
            int ct = chk.length();
            out.write("POST /login.php HTTP/1.1\r\n");
            out.write("Host: www.facebook.com\r\n");
            out.write("Cache-Control: max-age=0\r\n");
            out.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
            out.write("Origin: https://www.facebook.com\r\n");
            out.write("Content-Type: application/x-www-form-urlencoded\r\n");
            out.write("Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n");
            out.write("Accept-Language: en-US,en;q=0.8\r\n");
            out.write("cookie: datr=80ZzUfKqDOjwL8pauwqMjHTa\r\n");
            out.write("user-agent:Mozilla/5.0 (Linux; Android 4.2.2; GT-I1000 Build/JDQ39) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.59 Mobile Safari/537.36\r\n");
            out.write("Accept-Encoding: gzip,deflate,sdch\r\n");
            out.write("Content-Length: " + ct + "\r\n");
            out.write("\r\n");
            out.write(chk);
            out.flush();
    }
    
}
