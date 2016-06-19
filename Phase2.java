package sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class Phase2 {
    
    static int port = 443;
    static String addressString = "www.facebook.com/";
    static InetAddress address;
    
    SSLSocket socket;

    BufferedWriter out;
    BufferedReader in;

    Connection con;
    
    public void connect() {
    try {
    
        //Resolve IP address
        address = InetAddress.getByName(addressString);
        
        //Connect using a secure SSL conenction
        SSLSocketFactory socketf = HttpsURLConnection.getDefaultSSLSocketFactory();
        socket = (SSLSocket) socketf.createSocket(addressString, port);
        
        socket.setSoTimeout(10000);
        
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        con = new Connection(socket,out,in);
        
    } catch (UnknownHostException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    
public Connection passSuccessfulConnection() {
        return con;
    }
}
