import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String hostname = "192.168.0.101";
        int port = 5000;


        try (Socket socket = new Socket(hostname, port)) {
            // Opret input/output streams
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Læs input fra brugeren
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String text;

            // Send beskeder til serveren og modtag svar
            do {
                System.out.print("Indtast besked: ");
                text = consoleReader.readLine();
                writer.println(text);

                if (!text.equals("bye")) {
                    String response = reader.readLine();
                    System.out.println("Server: " + response);
                }

            } while (!text.equals("bye"));

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}