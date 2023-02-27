
import java.io.*;
import java.net.*;

public class EchoClient {

    private Socket s;

    public EchoClient(String Server, int portnum) {
        try {
            s = new Socket(Server, portnum);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public String DriveCheck(){
        {
            String[] letters = new String[]{ "A","B","C","D","E","F","G","H","I"};
            File[] drives = new File[letters.length];
            boolean[] isDrive = new boolean[letters.length];
            for ( int i = 0; i < letters.length; ++i )
            {
                drives[i] = new File(letters[i]+":/");
                isDrive[i] = drives[i].canRead();
            }
            while(true)
            {
                for ( int i = 0; i < letters.length; ++i )
                {
                    boolean pluggedIn = drives[i].canRead();
                    if ( pluggedIn != isDrive[i] )
                    {
                        if ( pluggedIn )
                        {
                            return "Drive Connected";
                        }
                        isDrive[i] = false;
                    }
                }
            }
        }
    }

    public void getServed() {
        try {

            PrintWriter w = new PrintWriter(s.getOutputStream(), true);

            BufferedReader con = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Client is Requesting");

            String line;
            do {
                line = con.readLine();
                w.println(line);

                if ((DriveCheck()).equals("Drive Connected")) {
                    w.println("Drive Connected");
                }
                else
                {
                    w.println(".");
                }

            } while (!line.trim().equals("exit"));
            s.close();
        } catch (Exception err) {
            System.err.println("Cant connect to server"+err);
        }

    }

    public static void main(String[] args) {
        EchoClient Client = new EchoClient("127.0.0.1", 6666);
        Client.getServed();
    }
}
