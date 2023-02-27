
import java.io.*;
import java.net.*;

public class EchoServer {

    private ServerSocket server;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public EchoServer(int portnum) {
        try {
            server = new ServerSocket(portnum);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void serve() {
        try {
            while (true) {
                Socket client = server.accept();
                BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter w = new PrintWriter(client.getOutputStream(), true);
                System.out.println("Server IS Analyzing.");
                String st;
                do {
                    st = r.readLine();
                    if (st.equals("exit") == true) {
                        System.exit(1);
                    } else if (st.equals("facebook.com") == true || st.equals("youtube.com") == true) {
                        System.out.println(ANSI_RED + "\033[3mIllegal Website Accessed" + ANSI_RESET);
                    } else if (st.equals(".") == true) {
                        System.out.println("Analyzing");
                    } else if (st.isEmpty()) {
                        System.out.println(ANSI_BLUE + "Server cant process Empty Request" + ANSI_RESET);
                    } else if (st.equals("Drive Connected") == true) {
                        System.out.println(ANSI_PURPLE + "\033[3mClient Attempted to connect a pen drive" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_GREEN + "Client Says :" + st + " Access Granted" + ANSI_RESET);
                    }
                } while (!st.trim().equals("exit"));
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    public static void main(String[] args) {
        EchoServer s = new EchoServer(6666);
        s.serve();
    }
}
