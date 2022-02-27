package ukol2.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

class MyClient {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 4242);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();

            wr.write(inputString);
            wr.write('\n');
            wr.flush();

            InputStream input = clientSocket.getInputStream();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    System.out.println(line);
                }
            } catch (SocketException e) {
                break;
            }
        }

    }
}